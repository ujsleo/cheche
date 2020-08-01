package cheche.core.dto.constant;

/**
 * 审批角色ID
 * 
 * @author jieli
 *
 */
public enum ApproverRoleId {
	/** 抄送人 */
	CC(0, "抄送人"), //
	/** 审批人/处理人 */
	ISSUE_HANDLER(1, "审批人/处理人"), //
	/** 管理员 */
	ADMIN(16, "管理员"), //
	;

	private int value;
	private String desc;

	ApproverRoleId(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
}
