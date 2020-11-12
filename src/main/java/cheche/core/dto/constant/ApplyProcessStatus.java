package cheche.core.dto.constant;

/**
 * 审批流程状态
 * 
 * @author jieli
 *
 */
public enum ApplyProcessStatus {
	/** 撤回（已撤销） */
	WITHDRAWED(-1, "已撤销"), //
	/** 草稿 */
	DRAFT(0, "草稿"), //
	/** 处理中（审批中） */
	IN_PROGRESS(1, "处理中"), //
	/** 挂起 */
	SUSPENDED(4, "挂起"), //
	/** 终止（已驳回） */
	TERMINATED(8, "终止"), //
	/** 成功（已通过） */
	COMPLETED(16, "成功"), //
	;

	private int value;
	private String desc;

	ApplyProcessStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	/** 是否可以再次发起 */
	public static boolean canReApply(int value) {
		return value == WITHDRAWED.getValue() || value == TERMINATED.getValue();
	}

	/** 是否可以撤回 */
	public static boolean canWithdraw(int value) {
		return value == IN_PROGRESS.getValue() || value == SUSPENDED.getValue();
	}
}
