package cheche.core.dto.auto;

/**
 * 自动审批任务Request
 * 
 * @author jieli
 *
 */
public class AutoApprovalRequest {
	/** 审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统 */
	private String className;
	/** task_id */
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
