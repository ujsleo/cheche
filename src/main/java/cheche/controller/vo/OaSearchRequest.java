package cheche.controller.vo;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 审批搜索Request
 * 
 * @author jieli
 */
public class OaSearchRequest extends BaseRequest {
    @ApiModelProperty(value = "域账号", required = true)
    @NotEmpty(message = "user can NOT be empty or null.")
    private String user;
    @ApiModelProperty(value = "关键词（审批单号、发起人、标题）", required = true)
    @NotEmpty(message = "key_word can NOT be empty or null.")
    private String wd;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }
}
