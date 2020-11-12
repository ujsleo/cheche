package cheche.controller.vo;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * 启用/停用/删除模板Request
 * 
 * @author jieli
 */
public class AdminTemplateOptRequest {
    @ApiModelProperty(value = "模板CODE", required = true)
    @NotBlank(message = "template_code can NOT be empty or null.")
    private String templateCode;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
