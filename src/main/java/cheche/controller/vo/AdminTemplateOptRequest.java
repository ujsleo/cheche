package cheche.controller.vo;

import javax.validation.constraints.NotNull;

/**
 * 启用/停用/删除模板Request
 * 
 * @author jieli
 *
 */
public class AdminTemplateOptRequest {
	/** 模板CODE */
	@NotNull(message = "template_code can NOT be empty or null.")
	private String templateCode;

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
}
