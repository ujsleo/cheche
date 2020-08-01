package cheche.core.dto.apply;

import javax.validation.constraints.NotNull;

/**
 * 审批动作
 * 
 * @author jieli
 *
 */
public class ApproveOpt {
	/** task_id */
	@NotNull(message = "task_id can NOT be null.")
	private Long taskId;
	/** 处理人的域账号 */
	@NotNull(message = "user can NOT be empty or null.")
	private String user;
	/** 备注 */
	private String remark;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
