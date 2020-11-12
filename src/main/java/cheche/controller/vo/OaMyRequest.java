package cheche.controller.vo;

import org.hibernate.validator.constraints.NotEmpty;

import cheche.core.dto.constant.ApplyProcessStatus;
import cheche.core.dto.constant.ApplyTaskSpotStatus;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取'我发起的'/'我审批的'Request
 * 
 * @author jieli
 */
public class OaMyRequest extends BaseRequest {
    @ApiModelProperty(value = "域账号", required = true)
    @NotEmpty(message = "user can NOT be empty or null.")
    private String  user;
    /**
     * '我发起的'为流程状态 {@link ApplyProcessStatus}
     * '我审批的'为节点的关注状态 {@link ApplyTaskSpotStatus}
     */
    @ApiModelProperty(value = "状态：null-全部")
    private Integer status;
    @ApiModelProperty(value = "模板CODE")
    private String  templateCode;

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
