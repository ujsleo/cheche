package cheche.core.dto.constant;

/**
 * 审批节点的关注状态
 * 
 * @author jieli
 *
 */
public enum ApplyTaskSpotStatus {
	/** 处理中（待处理） */
	IN_PROGRESS(1, "处理中"), //
	/** 驳回（已处理） */
	REJECT(8, "驳回"), //
	/** 通过（已处理） */
	PASS(16, "通过"), //
	;

	private int value;
	private String desc;

	ApplyTaskSpotStatus(int value, String desc) {
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
