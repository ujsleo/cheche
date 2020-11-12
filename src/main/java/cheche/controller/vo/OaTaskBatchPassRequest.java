package cheche.controller.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * （处理人）批量审批通过Request
 * 
 * @author jieli
 */
public class OaTaskBatchPassRequest {
    @ApiModelProperty(value = "task_ids", required = true)
    @NotEmpty(message = "task_ids can NOT be null or empty.")
    private List<Long> lst;
    @ApiModelProperty(value = "处理人的域账号", required = true)
    @NotBlank(message = "user can NOT be empty or null.")
    private String     user;

    public List<Long> getLst() {
        return lst;
    }

    public void setLst(List<Long> lst) {
        this.lst = lst;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
