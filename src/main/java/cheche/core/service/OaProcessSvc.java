package cheche.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cheche.core.auto.AutoApprovalTask;
import cheche.core.dto.OaVariables;
import cheche.core.dto.apply.ApplyContent;
import cheche.core.dto.apply.WithdrawOpt;
import cheche.core.dto.auto.AutoApprovalRequest;
import cheche.core.dto.constant.ApplyEventType;
import cheche.core.dto.constant.ApplyProcessStatus;
import cheche.core.dto.constant.ApplyTaskSpotStatus;
import cheche.core.dto.constant.ApplyTaskStatus;
import cheche.core.dto.constant.ApproverRoleId;
import cheche.core.dto.constant.ChecheConst;
import cheche.core.dto.msg.SendMsgRequest;
import cheche.core.dto.template.ApproverItem;
import cheche.core.dto.template.TemplateContent;
import cheche.core.exception.ChecheException;
import cheche.core.exception.ChecheExceptionEnum;
import cheche.core.helper.OaApplyDataHelper;
import cheche.core.helper.OaEventHelper;
import cheche.core.helper.OaTaskHelper;
import cheche.core.msg.SendMsgTask;
import cheche.dal.dao.ChecheApplyControlMapper;
import cheche.dal.dao.ChecheApplyProcessMapper;
import cheche.dal.dao.ChecheApplyTaskMapper;
import cheche.dal.dao.ChecheApplyTaskSpotMapper;
import cheche.dal.dao.ChecheTemplateMapper;
import cheche.dal.entity.ChecheApplyControl;
import cheche.dal.entity.ChecheApplyProcess;
import cheche.dal.entity.ChecheApplyTask;
import cheche.dal.entity.ChecheApplyTaskSpot;

/**
 * 审批流程服务
 * 
 * @author jieli
 */
@Component
public class OaProcessSvc {
    @Autowired
    private ChecheApplyProcessMapper  processDao;
    @Autowired
    private ChecheTemplateMapper      templateDao;
    @Autowired
    private ChecheApplyTaskMapper     taskDao;
    @Autowired
    private ChecheApplyTaskSpotMapper taskSpotDao;
    @Autowired
    private ChecheApplyControlMapper  controlDao;

    /**
     * （申请人）发起审批申请
     * 
     * @param applyContent 审批申请
     * @return process_id 流程ID
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 60, rollbackFor = Exception.class)
    public Long apply(ApplyContent applyContent) {
        Long processId = null;
        ChecheApplyProcess processPojo = processDao.findByCode(applyContent.processCode());
        if (processPojo != null) { // 再次发起
            if (!ApplyProcessStatus.canReApply(processPojo.getStatus()))
                throw new ChecheException(ChecheExceptionEnum.E403, //
                        String.format(
                                "Illegal opt, template_code: %s, biz_code: %s ALREADY exists, status: %d can NOT re-apply.",
                                applyContent.getTemplateCode(), applyContent.getBizCode(), processPojo.getStatus()));
            processId = processPojo.getId();
            // 删除原申请数据
            controlDao.deleteAll(processId);
        } else { // 首次发起
            // 创建审批流程实例
            ChecheApplyProcess processSample = applyContent.toProcessSample();
            processDao.insertSelective(processSample);
            processId = processSample.getId();
        }
        // 添加申请数据
        for (ChecheApplyControl control : applyContent.toControlSample(processId))
            controlDao.insertSelective(control);

        final int step = 1;
        ApproverItem approver = templateDao.findOne(applyContent.getTemplateCode()).approver(step);
        doMigration(processId, approver, step);

        // 添加审批事件
        OaEventHelper.addEvent(processId, ApplyEventType.APPLY, applyContent.getUser());
        return processId;
    }

    /**
     * （申请人）撤回审批申请
     * 
     * @param withdrawOpt 撤回动作
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 60, rollbackFor = Exception.class)
    public void withdraw(WithdrawOpt withdrawOpt) {
        check4Withdraw(withdrawOpt);

        Long processId = withdrawOpt.getProcessId();
        ChecheApplyProcess processPojo = processDao.selectByPrimaryKey(processId);
        // 回调各业务系统撤回或驳回
        OaTaskHelper.undo(templateDao.findOne(processPojo.getTemplateCode()) //
                .startedApprover(processPojo.getStep()));

        // task withdraw
        taskDao.withdraw(processId);
        taskSpotDao.withdraw(processId);
        // 流程撤回
        processDao.withdraw(processId, withdrawOpt.getUser());

        // 添加审批事件
        OaEventHelper.addEvent(processId, ApplyEventType.WITHDRAW, withdrawOpt.getUser());
    }

    /** 校验撤回动作 */
    private void check4Withdraw(WithdrawOpt withdrawOpt) {
        Long processId = withdrawOpt.getProcessId();
        ChecheApplyProcess processPojo = processDao.selectByPrimaryKey(processId);
        if (processPojo == null)
            throw new ChecheException(ChecheExceptionEnum.E400, //
                    String.format("Illegal opt, process_id: %d NOT exists.", processId));
        // 校验用户是否可以撤回
        if (!ApplyProcessStatus.canWithdraw(processPojo.getStatus()))
            throw new ChecheException(ChecheExceptionEnum.E403, //
                    String.format("Illegal opt, process_id: %d, status: %d can NOT withdraw.", processId,
                            processPojo.getStatus()));
        if (!withdrawOpt.getUser().equals(processPojo.getUser()))
            throw new ChecheException(ChecheExceptionEnum.E403, //
                    String.format("Illegal opt, process_id: %d, user: %s can NOT withdraw.", processId,
                            withdrawOpt.getUser()));
    }

    /**
     * 审批流程迁移
     * 
     * @param processId 流程ID
     * @param step 目标级次
     * @param user 处理人的域账号
     */
    public void migration(Long processId, Integer step, String user) {
        ChecheApplyProcess processPojo = processDao.selectByPrimaryKey(processId);
        TemplateContent templateContent = templateDao.findOne(processPojo.getTemplateCode());
        if (step > templateContent.totalSteps()) {
            // COMPLETED
            processDao.completed(processId, user);

            SendMsgRequest sendMsgRequest = new SendMsgRequest();
            // TODO 延时、异步消息通知申请人成功
            new SendMsgTask().delay(sendMsgRequest);
        } else {
            // 流程迁移
            ApproverItem approver = templateContent.approver(step);
            doMigration(processId, approver, step);
            processDao.updateStep(processId, step, user);
        }
    }

    /**
     * 1) 回调业务系统发起 2) 创建审批节点和关注列表的实例 3) 系统自动审批或异步通知处理人
     * 
     * @param processId 流程ID
     * @param approver 审批节点的元数据
     * @param step 目标级次
     */
    private void doMigration(Long processId, ApproverItem approver, Integer step) {
        // 回调业务系统发起
        OaTaskHelper.start(approver.getClassName(), new OaVariables(processId, step));

        // 创建审批节点实例
        ChecheApplyTask taskSample = new ChecheApplyTask();
        taskSample.setProcessId(processId);
        taskSample.setStatus(ApplyTaskStatus.IN_PROGRESS.getValue());
        taskSample.setClassName(approver.getClassName());
        taskSample.setType(approver.getType());
        taskSample.setStep(step);
        taskDao.insertSelective(taskSample);
        Long taskId = taskSample.getId();

        if (OaTaskHelper.canAuto(approver.getClassName())) { // 自动审批
            // 创建审批节点的cheche关注实例
            ChecheApplyTaskSpot taskSpotSample = new ChecheApplyTaskSpot();
            taskSpotSample.setTaskId(taskId);
            taskSpotSample.setProcessId(processId);
            taskSpotSample.setUser(ChecheConst.SYS_APPROVAL);
            taskSpotSample.setStatus(ApplyTaskSpotStatus.IN_PROGRESS.getValue());
            taskSpotSample.setRoleId(ApproverRoleId.ADMIN.getValue());
            taskSpotSample.setStep(step);
            taskSpotDao.insertSelective(taskSpotSample);

            // 延迟、异步自动审批
            doAutoApproval(approver.getClassName(), taskId);
        } else { // 人工审批
            // 创建审批节点的关注实例列表
            List<ChecheApplyTaskSpot> taskSpotSamples = new ArrayList<>();
            // 处理人
            for (String user : approver.participant()) {
                ChecheApplyTaskSpot taskSpotSample = new ChecheApplyTaskSpot();
                taskSpotSample.setTaskId(taskId);
                taskSpotSample.setProcessId(processId);
                taskSpotSample.setUser(user);
                taskSpotSample.setStatus(ApplyTaskSpotStatus.IN_PROGRESS.getValue());
                taskSpotSample.setRoleId(ApproverRoleId.ISSUE_HANDLER.getValue());
                taskSpotSample.setStep(step);
                taskSpotSamples.add(taskSpotSample);
            }
            // 管理员
            for (String admin : approver.admins()) {
                ChecheApplyTaskSpot taskSpotSample = new ChecheApplyTaskSpot();
                taskSpotSample.setTaskId(taskId);
                taskSpotSample.setProcessId(processId);
                taskSpotSample.setUser(admin);
                taskSpotSample.setStatus(ApplyTaskSpotStatus.IN_PROGRESS.getValue());
                taskSpotSample.setRoleId(ApproverRoleId.ADMIN.getValue());
                taskSpotSample.setStep(step);
                taskSpotSamples.add(taskSpotSample);
            }
            taskSpotDao.batchInsert(taskSpotSamples);

            SendMsgRequest sendMsgRequest = new SendMsgRequest();
            // TODO 延时、异步消息通知处理人
            new SendMsgTask().delay(sendMsgRequest);
        }
    }

    /**
     * 延迟、异步自动审批
     * 
     * @param className
     * @param taskId
     */
    private void doAutoApproval(String className, Long taskId) {
        AutoApprovalRequest request = new AutoApprovalRequest();
        request.setClassName(className);
        request.setTaskId(taskId);
        new AutoApprovalTask().delay(request);
    }

    /**
     * 获取审批申请详情
     * 
     * @param id 流程ID
     * @return
     */
    public ApplyContent detail(Long id) {
        ChecheApplyProcess processPojo = processDao.selectByPrimaryKey(id);
        if (processPojo == null)
            return null;

        TemplateContent applyData = templateDao.findOne(processPojo.getTemplateCode());
        applyData.setApplyContents(OaApplyDataHelper.applyContents(id));
        return new ApplyContent(processPojo, applyData);
    }
}
