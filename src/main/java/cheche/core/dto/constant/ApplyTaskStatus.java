package cheche.core.dto.constant;

/**
 * 审批节点状态
 * 
 * @author jieli
 *
 */
public enum ApplyTaskStatus {
	/** 处理中 */
	IN_PROGRESS(1, "处理中"), //
//	/** 保存点 */
//	CONTINUED(2, "保存点"), //
//	/** 挂起 */
//	SUSPENDED(4, "挂起"), //
	/** 驳回 */
	REJECT(8, "驳回"), //
	/** 通过 */
	PASS(16, "通过"), //
	;

	private int value;
	private String desc;

	ApplyTaskStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	public static boolean canOpt(int value) {
		return value == IN_PROGRESS.getValue();
	}
}
