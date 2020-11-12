package cheche.core.dto.apply;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 驳回动作（默认驳回到开始节点）
 * 
 * @author jieli
 *
 */
@ApiModel(value = "驳回动作（默认驳回到开始节点）")
public class RejectOpt extends ApproveOpt {
	@ApiModelProperty(value = "选择驳回级次：0-默认驳回到开始节点")
	private Integer step;

	public Integer getStep() {
		return step == null ? 0 : step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}
}
