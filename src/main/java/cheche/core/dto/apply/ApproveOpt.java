package cheche.core.dto.apply;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 审批动作
 * 
 * @author jieli
 */
@ApiModel(value = "审批动作")
public class ApproveOpt {
    @ApiModelProperty(value = "task_id", required = true)
    @NotNull(message = "task_id can NOT be null.")
    private Long   taskId;
    @ApiModelProperty(value = "处理人的域账号", required = true)
    @NotBlank(message = "user can NOT be empty or null.")
    private String user;
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
