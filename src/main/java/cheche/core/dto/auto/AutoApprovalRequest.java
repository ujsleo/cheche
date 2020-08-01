package cheche.core.dto.auto;

public class AutoApprovalRequest {
	private String className;
	private Long taskId;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
}
