package cheche.core.dto.approval;

import java.util.Date;

import cheche.core.dto.constant.ApplyProcessStatus;
import io.swagger.annotations.ApiModelProperty;

/**
 * Base审批摘要
 * 
 * @author jieli
 */
public class BaseApprovalSummary {
    @ApiModelProperty(value = "流程ID")
    private Long    processId;
    @ApiModelProperty(value = "审批单号")
    private String  sn;
    /**
     * {@link ApplyProcessStatus}
     */
    @ApiModelProperty(value = "流程状态")
    private Integer processStatus;
    @ApiModelProperty(value = "申请人的域账号")
    private String  applyUser;
    @ApiModelProperty(value = "申请单标题")
    private String  title;
    @ApiModelProperty(value = "申请时间")
    private Date    applyDate;
    @ApiModelProperty(value = "模板名称")
    private String  templateName;

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
