package cheche.core.dto.template;

import javax.validation.constraints.NotNull;

import cheche.core.dto.constant.ApplyTaskType;

/**
 * 审批节点信息
 * 
 * @author jieli
 *
 */
public class ApproverItem extends Participant {
	/** 审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统 */
	@NotNull(message = "class_name can NOT be empty or null.")
	private String className;
	/** 节点审批方式：0-或签 1-会签 */
	private Integer type = ApplyTaskType.OR;
	/** 级次 **/
	private Integer step = 0;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}
}
