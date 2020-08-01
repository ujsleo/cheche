package cheche.core.dto.apply;

import javax.validation.constraints.NotNull;

/**
 * 转办动作
 * 
 * @author jieli
 *
 */
public class TransferOpt extends ApproveOpt {
	/** 代办人的域账号 */
	@NotNull(message = "agent can NOT be empty or null.")
	private String agent;

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
}
