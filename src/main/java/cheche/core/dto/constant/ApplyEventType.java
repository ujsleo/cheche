package cheche.core.dto.constant;

/**
 * 审批事件类型
 * 
 * @author jieli
 *
 */
public enum ApplyEventType {
	/** 已发起 */
	APPLY(1, "已发起"), //
	/** 已撤回 */
	WITHDRAW(4, "已撤回"), //
	/** 已驳回 */
	REJECT(8, "已驳回"), //
	/** 已通过 */
	PASS(16, "已通过"), //
	/** 已转办 */
	TRANSFER(32, "已转办"), //
	;

	private int value;
	private String desc;

	ApplyEventType(int value, String desc) {
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
