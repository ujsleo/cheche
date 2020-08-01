package cheche.core.dto.apply;

/**
 * 驳回动作（默认驳回到开始节点）
 * 
 * @author jieli
 *
 */
public class RejectOpt extends ApproveOpt {
	/** 选择驳回级次：0-默认驳回到开始节点 */
	private Integer step;

	public Integer getStep() {
		return step == null ? 0 : step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}
}
