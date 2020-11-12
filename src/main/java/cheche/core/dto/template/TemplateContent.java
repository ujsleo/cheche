package cheche.core.dto.template;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.NotEmpty;

import cheche.common.utils.JsonUtils;
import cheche.core.dto.constant.ApproverRoleId;
import cheche.core.exception.ChecheException;
import cheche.core.exception.ChecheExceptionEnum;
import cheche.dal.entity.ChecheTemplate;
import cheche.dal.entity.ChecheTemplateApprover;
import cheche.dal.entity.ChecheTemplateControl;
import cheche.dal.entity.ChecheTemplateHook;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模板详情
 * 
 * @author jieli
 */
@ApiModel(value = "模板详情")
public class TemplateContent extends TemplateInfo {
    @ApiModelProperty(value = "模板控件信息，数组。用于模板设置，构成审批表单")
    private List<ControlItem>  applyContents = new ArrayList<>();
    @ApiModelProperty(value = "审批流程信息，数组。用于规则设置", required = true)
    @NotEmpty(message = "approver can NOT be null or empty.")
    private List<ApproverItem> approver;
    @ApiModelProperty(value = "抄送人列表")
    private Notifyer           notifyer;

    /**
     * 模板详情转模板POJO
     * 
     * @return 模板POJO
     */
    public ChecheTemplate toTemplateSample() {
        ChecheTemplate ret = new ChecheTemplate();
        ret.setCode(getCode());
        ret.setName(name);
        ret.setIcon(icon);
        ret.setGroupId(groupId);
        return ret;
    }

    /**
     * 模板详情转模板控件POJO列表
     * 
     * @param templateId 模板ID
     * @return 模板控件POJO列表
     */
    public List<ChecheTemplateControl> toTemplateControlSample(Long templateId) {
        List<ChecheTemplateControl> ret = new ArrayList<>();
        for (ControlItem one : applyContents) {
            ChecheTemplateControl sample = new ChecheTemplateControl();
            sample.setTemplateId(templateId);
            sample.setType(one.getType());
            sample.setName(one.getName());
            sample.setLabel(one.getLabel());
            sample.setValue(one.getValue());
            sample.setPlaceholder(one.getPlaceholder());
            sample.setRequire(one.getRequire());
            sample.setConfig(null == one.getConfig() ? null : JsonUtils.toJSONString(one.getConfig()));
            ret.add(sample);
        }
        return ret;
    }

    /**
     * 模板详情转审批规则POJO列表
     * 
     * @param templateId 模板ID
     * @return 审批规则POJO列表
     */
    public List<ChecheTemplateApprover> toTemplateApproverSample(Long templateId) {
        List<ChecheTemplateApprover> ret = new ArrayList<>();
        int step = 1;
        for (ApproverItem one : approver) {
            ChecheTemplateApprover sample = new ChecheTemplateApprover();
            sample.setTemplateId(templateId);
            sample.setClassName(one.getClassName());
            sample.setType(one.getType());
            sample.setStep(step++);
            sample.setRoleId(ApproverRoleId.ISSUE_HANDLER.getValue()); // 审批人
            sample.setUser(one.getUser());
            sample.setRole(one.getRole());
            sample.setAdmin(one.getAdmin());
            ret.add(sample);
        }
        return ret;
    }

    /**
     * 转Hook配置POJO列表
     * 
     * @param templateId 模板ID
     * @param approverId 审批节点ID
     * @param step 级次
     * @return
     */
    public List<ChecheTemplateHook> toTemplateHookSample(Long templateId, Long approverId, Integer step) {
        List<ChecheTemplateHook> ret = new ArrayList<>();
        for (ApproverItem one : approver) {
            ExtraAttr extraAttr = one.getExtraAttr();
            List<HookItem> hookLst = (extraAttr != null) ? extraAttr.getHook() : null;
            if (one.getStep() != step || CollectionUtils.isEmpty(hookLst))
                continue;
            ret = hookLst.stream().map(x -> {
                return x.toTemplateHookSample(templateId, approverId);
            }).collect(Collectors.toList());
        }
        return ret;
    }

    /** 级次总数 */
    public int totalSteps() {
        return approver.size();
    }

    /** 获取指定的审批节点信息 */
    public ApproverItem approver(int step) {
        if (step < 1 || step > totalSteps())
            throw new ChecheException(ChecheExceptionEnum.E500, "process_step Illegal.");

        return approver.get(step - 1);
    }

    /** 获取已发起的节点信息列表 */
    public List<ApproverItem> startedApprover(int step) {
        if (step < 1 || step > totalSteps())
            throw new ChecheException(ChecheExceptionEnum.E500, "process_step Illegal.");

        return approver.subList(0, step);
    }

    /**
     * 获取指定级次区间的节点信息列表
     * 
     * @param targetStep 驳回的目标级次
     * @param currentStep 当前级次
     * @return
     */
    public List<ApproverItem> subApprover(int targetStep, int currentStep) {
        if (targetStep > currentStep || targetStep < 1)
            throw new ChecheException(ChecheExceptionEnum.E500, "target_step Illegal.");

        return approver.subList(targetStep - 1, currentStep - 1);
    }

    public List<ControlItem> getApplyContents() {
        return applyContents;
    }

    public void setApplyContents(List<ControlItem> applyContents) {
        this.applyContents = applyContents;
    }

    public List<ApproverItem> getApprover() {
        return approver;
    }

    public void setApprover(List<ApproverItem> approver) {
        this.approver = approver;
    }

    public Notifyer getNotifyer() {
        return notifyer;
    }

    public void setNotifyer(Notifyer notifyer) {
        this.notifyer = notifyer;
    }
}
