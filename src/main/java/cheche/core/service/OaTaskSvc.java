package cheche.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cheche.core.dto.apply.ApproveOpt;
import cheche.core.dto.apply.RejectOpt;
import cheche.core.dto.apply.TransferOpt;
import cheche.core.dto.constant.ApplyEventType;
import cheche.core.dto.constant.ApplyTaskStatus;
import cheche.core.dto.constant.ApplyTaskType;
import cheche.core.dto.msg.SendMsgRequest;
import cheche.core.exception.ChecheException;
import cheche.core.exception.ChecheExceptionEnum;
import cheche.core.helper.OaEventHelper;
import cheche.core.helper.OaTaskHelper;
import cheche.core.msg.SendMsgTask;
import cheche.dal.dao.ChecheApplyProcessMapper;
import cheche.dal.dao.ChecheApplyTaskMapper;
import cheche.dal.dao.ChecheApplyTaskSpotMapper;
import cheche.dal.dao.ChecheTemplateMapper;
import cheche.dal.entity.ChecheApplyProcess;
import cheche.dal.entity.ChecheApplyTask;
import cheche.dal.entity.ChecheApplyTaskSpot;

/**
 * 业务审批节点服务
 * 
 * @author jieli
 *
 */
@Component
public class OaTaskSvc {
	@Autowired
	private ChecheApplyProcessMapper processDao;
	@Autowired
	private ChecheTemplateMapper templateDao;
	@Autowired
	private ChecheApplyTaskMapper taskDao;
	@Autowired
	private ChecheApplyTaskSpotMapper taskSpotDao;
	@Autowired
	private OaProcessSvc processSvc;

	/**
	 * （处理人）审批通过
	 * 
	 * @param approveOpt 审批动作
	 */
	@Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 60, rollbackFor = Exception.class)
	public void pass(ApproveOpt approveOpt) {
		check4Opt(approveOpt);

		Long taskId = approveOpt.getTaskId();
		ChecheApplyTask taskPojo = taskDao.selectByPrimaryKey(taskId);
		Long processId = taskPojo.getProcessId();
		switch (taskPojo.getType()) {
		case ApplyTaskType.OR:
			// 回调业务系统通过
			OaTaskHelper.pass(taskPojo.getClassName());

			// task PASS
			taskDao.pass(taskId);
			taskSpotDao.pass(taskId, approveOpt.getUser(), approveOpt.getRemark());
			break;
		case ApplyTaskType.AND:
			throw new ChecheException(ChecheExceptionEnum.E501, "NOT supported.");
		default:
			throw new ChecheException(ChecheExceptionEnum.E403, "NOT supported.");
		}

		// migration to next step
		ChecheApplyProcess processPojo = processDao.selectByPrimaryKey(processId);
		int nextStep = processPojo.getStep() + 1;
		processSvc.migration(processId, nextStep);

		// 添加审批事件
		OaEventHelper.addEvent(processId, ApplyEventType.PASS, approveOpt.getUser(), approveOpt.getRemark());
	}

	/** 校验审批动作 */
	private void check4Opt(ApproveOpt approveOpt) {
		Long taskId = approveOpt.getTaskId();
		ChecheApplyTask taskPojo = taskDao.selectByPrimaryKey(taskId);
		if (taskPojo == null)
			throw new ChecheException(ChecheExceptionEnum.E400,
					String.format("Illegal opt, task_id: %l NOT exists.", taskId));
		// 校验用户是否可以审批
		if (!ApplyTaskStatus.canOpt(taskPojo.getStatus()))
			throw new ChecheException(ChecheExceptionEnum.E403,
					String.format("Illegal opt, task_id: %l, status: %d can NOT opt.", taskId, taskPojo.getStatus()));
		List<String> users = new ArrayList<>();
		for (ChecheApplyTaskSpot taskSpotPojo : taskSpotDao.findAll(taskId))
			users.add(taskSpotPojo.getUser());
		if (!users.contains(approveOpt.getUser()))
			throw new ChecheException(ChecheExceptionEnum.E403,
					String.format("Illegal opt, task_id: %l, user: %s can NOT opt.", taskId, approveOpt.getUser()));
	}

	/**
	 * （处理人）审批驳回：默认驳回到开始节点
	 * 
	 * @param rejectOpt 驳回动作
	 */
	@Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 60, rollbackFor = Exception.class)
	public void reject(RejectOpt rejectOpt) {
		check4Opt(rejectOpt);

		Long taskId = rejectOpt.getTaskId();
		ChecheApplyTask taskPojo = taskDao.selectByPrimaryKey(taskId);
		Long processId = taskPojo.getProcessId();
		ChecheApplyProcess processPojo = processDao.selectByPrimaryKey(processId);
		Integer targetStep = rejectOpt.getStep();
		if (targetStep < 0 || targetStep >= processPojo.getStep()) { // 校验驳回级次
			throw new ChecheException(ChecheExceptionEnum.E400,
					String.format("Illegal opt, task_id: %s, step: %d can NOT opt.", taskId, targetStep));
		} else if (targetStep == 0) { // 驳回到开始节点
			// 等同于撤回
			OaTaskHelper.undo(templateDao.findOne(processPojo.getTemplateCode()) //
					.startedApprover(processPojo.getStep()));

			taskDao.withdraw(processId);
			taskSpotDao.withdraw(processId);
		} else { // 驳回到指定级别
			// 回调各业务系统撤回或驳回
			OaTaskHelper.undo(templateDao.findOne(processPojo.getTemplateCode()) //
					.subApprover(targetStep, processPojo.getStep()));

			// 删除targetStep ~ processStep区间的所有task和taskSpot
			taskDao.reject(processId, targetStep, processPojo.getStep());
			taskSpotDao.reject(processId, targetStep, processPojo.getStep());
		}
		// 流程终止
		processDao.terminated(processId, targetStep);

		// 添加审批事件
		OaEventHelper.addEvent(processId, ApplyEventType.REJECT, rejectOpt.getUser(), rejectOpt.getRemark());

		SendMsgRequest sendMsgRequest = new SendMsgRequest();
		// TODO 延时、异步消息通知申请人被驳回
		new SendMsgTask().delay(sendMsgRequest);
	}

	/**
	 * （处理人）转办
	 * 
	 * @param transferOpt 转办动作
	 */
	@Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 60, rollbackFor = Exception.class)
	public void transfer(TransferOpt transferOpt) {
		check4Opt(transferOpt);

		Long taskId = transferOpt.getTaskId();
		ChecheApplyTask taskPojo = taskDao.selectByPrimaryKey(taskId);
		Long processId = taskPojo.getProcessId();
		// 转办
		taskSpotDao.transfer(taskId, transferOpt.getUser(), transferOpt.getAgent());

		// 添加审批事件
		String remark = String.format("%s已委托%s处理。转办意见：%s", transferOpt.getUser(), transferOpt.getAgent(),
				transferOpt.getRemark());
		OaEventHelper.addEvent(processId, ApplyEventType.TRANSFER, transferOpt.getUser(), remark);

		SendMsgRequest sendMsgRequest = new SendMsgRequest();
		// TODO 延时、异步消息通知转办人
		new SendMsgTask().delay(sendMsgRequest);
	}
}
