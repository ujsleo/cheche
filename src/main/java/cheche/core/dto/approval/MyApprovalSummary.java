package cheche.core.dto.approval;

/**
 * "我审批的"审批摘要
 * 
 * @author jieli
 *
 */
public class MyApprovalSummary extends BaseApprovalSummary {
	/** 审批节点ID */
	private Long taskId;
	/** 审批节点状态 */
	private Integer taskStatus;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
}
