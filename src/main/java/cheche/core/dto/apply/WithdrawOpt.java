package cheche.core.dto.apply;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 撤回操作
 * 
 * @author jieli
 */
@ApiModel(value = "撤回操作")
public class WithdrawOpt {
    @ApiModelProperty(value = "流程ID")
    @NotNull(message = "process_id can NOT be null.")
    private Long   processId;
    @ApiModelProperty(value = "申请人的域账号")
    @NotBlank(message = "user can NOT be empty or null.")
    private String user;

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
