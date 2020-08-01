package cheche.core.dto.template;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import cheche.core.dto.constant.ApproverRoleId;
import cheche.core.exception.ChecheException;
import cheche.core.exception.ChecheExceptionEnum;
import cheche.dal.entity.ChecheTemplate;
import cheche.dal.entity.ChecheTemplateApprover;
import cheche.dal.entity.ChecheTemplateControl;
import common.util.tools.CheckUtils;
import common.util.tools.JsonUtils;

/**
 * 模板详情
 * 
 * @author jieli
 *
 */
public class TemplateContent extends TemplateInfo {
	/** 模板控件信息，数组。用于模板设置，构成审批表单 */
	private List<ControlItem> applyContents = new ArrayList<>();
	/** 审批流程信息，数组。用于规则设置 */
	@NotNull(message = "approver can NOT be null.")
	private List<ApproverItem> approver;
	/** 抄送人列表 */
	private Notifyer notifyer;

	/**
	 * 模板详情转模板POJO
	 * 
	 * @return 模板POJO
	 */
	public ChecheTemplate toTemplatePojo() {
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
	public List<ChecheTemplateControl> toTemplateControlPojos(Long templateId) {
		CheckUtils.checkNotNull(templateId, "template_id can NOT be null.");
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
			sample.setConfig(JsonUtils.convert2Json(one.getConfig()));
			ret.add(sample);
		}
		return ret;
	}

	/**
	 * 模板详情转审批规则POJO列表
	 * 
	 * @param templateId模板ID
	 * @return 审批规则POJO列表
	 */
	public List<ChecheTemplateApprover> toTemplateApproverPojos(Long templateId) {
		CheckUtils.checkNotNull(templateId, "template_id can NOT be null.");
		List<ChecheTemplateApprover> ret = new ArrayList<>();
		int step = 1;
		for (ApproverItem one : approver) {
			ChecheTemplateApprover sample = new ChecheTemplateApprover();
			sample.setTemplateId(templateId);
			sample.setClassName(one.getClassName());
			sample.setType(one.getType());
			// 默认级次
			sample.setStep(one.getStep() == 0 ? step++ : one.getStep());
			sample.setRoleId(ApproverRoleId.ISSUE_HANDLER.getValue()); // 审批人
			sample.setUser(one.getUser());
			sample.setRole(one.getRole());
			sample.setAdmin(one.getAdmin());
			ret.add(sample);
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
	 * @param targetStep  驳回的目标级次
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
