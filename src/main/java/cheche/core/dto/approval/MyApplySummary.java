package cheche.core.dto.approval;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * '我发起的'审批摘要
 * 
 * @author jieli
 */
@ApiModel(value = "'我发起的'审批摘要")
public class MyApplySummary extends BaseApprovalSummary {
    @ApiModelProperty(value = "当前节点处理人的域账号列表，以逗号,分隔")
    private String approver;

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }
}
