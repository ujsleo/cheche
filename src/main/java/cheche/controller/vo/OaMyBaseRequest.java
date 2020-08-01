package cheche.controller.vo;

import javax.validation.constraints.NotNull;

/**
 * 我发起的/我审批的Request
 * 
 * @author jieli
 *
 */
public class OaMyBaseRequest extends BaseRequest {
	/** 域账号 */
	@NotNull(message = "user can NOT be null.")
	private String user;
	/** 状态 */
	private Integer status;
	/** 模板CODE */
	private String templateCode;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
}
