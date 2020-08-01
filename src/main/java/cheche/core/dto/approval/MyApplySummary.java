package cheche.core.dto.approval;

/**
 * "我发起的"审批摘要
 * 
 * @author jieli
 *
 */
public class MyApplySummary extends BaseApprovalSummary {
	/** 当前节点处理人的域账号列表，以逗号,分隔 */
	private String approver;

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}
}
