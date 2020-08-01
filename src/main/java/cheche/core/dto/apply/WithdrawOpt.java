package cheche.core.dto.apply;

import javax.validation.constraints.NotNull;

/**
 * 撤回操作
 * 
 * @author jieli
 *
 */
public class WithdrawOpt {
	/** process_id */
	@NotNull(message = "process_id can NOT be null.")
	private Long processId;
	/** 申请人的域账号 */
	@NotNull(message = "user can NOT be empty or null.")
	private String user;

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
