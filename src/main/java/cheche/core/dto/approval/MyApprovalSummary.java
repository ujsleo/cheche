package cheche.core.dto.approval;

import cheche.core.dto.constant.ApplyTaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * '我审批的'审批摘要
 * 
 * @author jieli
 */
@ApiModel(value = "'我审批的'审批摘要")
public class MyApprovalSummary extends BaseApprovalSummary {
    @ApiModelProperty(value = "审批节点ID")
    private Long    taskId;
    /**
     * {@link ApplyTaskStatus}
     */
    @ApiModelProperty(value = "审批节点状态")
    private Integer taskStatus;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }
}
