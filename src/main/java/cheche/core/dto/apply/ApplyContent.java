package cheche.core.dto.apply;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import cheche.common.utils.CheckUtils;
import cheche.common.utils.RandomUtils;
import cheche.core.dto.constant.ApplyProcessStatus;
import cheche.core.dto.constant.ChecheConst;
import cheche.core.dto.template.ControlItem;
import cheche.core.dto.template.TemplateContent;
import cheche.dal.entity.ChecheApplyControl;
import cheche.dal.entity.ChecheApplyProcess;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 审批申请详情
 * 
 * @author jieli
 */
@ApiModel(value = "审批申请详情")
public class ApplyContent {
    @ApiModelProperty(value = "模板CODE", required = true)
    @NotBlank(message = "template_code can NOT be empty or null.")
    private String          templateCode;
    @ApiModelProperty(value = "业务CODE，业务的唯一标识符 UNIQUE", required = true)
    @NotBlank(message = "biz_code can NOT be empty or null.")
    private String          bizCode;
    @ApiModelProperty(value = "申请人的域账号", required = true)
    @NotBlank(message = "user can NOT be empty or null.")
    private String          user;
    @ApiModelProperty(value = "申请单的标题", required = true)
    @NotBlank(message = "title can NOT be empty or null.")
    private String          title;
    @ApiModelProperty(value = "开始时间")
    private Date            startDate;
    @ApiModelProperty(value = "结束时间")
    private Date            endDate;
    @ApiModelProperty(value = "审批申请数据")
    private TemplateContent applyData;

    /** process_code = template_code,biz_code */
    public String processCode() {
        return templateCode + ChecheConst.SEPARATOR + bizCode;
    }

    public ApplyContent() {
    }

    public ApplyContent(ChecheApplyProcess processPojo, TemplateContent applyData) {
        templateCode = processPojo.getTemplateCode();
        bizCode = processPojo.getBizCode();
        user = processPojo.getUser();
        title = processPojo.getTitle();
        startDate = processPojo.getStartDate();
        endDate = processPojo.getEndDate();
        this.applyData = applyData;
    }

    /**
     * 审批申请转审批流程POJO
     * 
     * @return 审批流程POJO
     */
    public ChecheApplyProcess toProcessSample() {
        ChecheApplyProcess ret = new ChecheApplyProcess();
        ret.setCode(processCode());
        ret.setSn(RandomUtils.nextSN());
        ret.setStatus(ApplyProcessStatus.IN_PROGRESS.getValue());
        ret.setStep(1);
        ret.setTemplateCode(templateCode);
        ret.setBizCode(bizCode);
        ret.setUser(user);
        ret.setTitle(title);
        // 起期
        Date start = (startDate == null) ? new Date() : startDate;
        ret.setStartDate(start);
        ret.setEndDate(endDate == null ? end(start, 7) : endDate); // 默认止期=起期+7天
        ret.setCreator(user);
        ret.setModifier(user);
        return ret;
    }

    /**
     * 止期
     * 
     * @param start 起期
     * @param days 增加天数
     * @return
     */
    private Date end(Date start, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 审批申请转申请数据POJO列表
     * 
     * @param processId 流程ID
     * @return 申请数据POJO列表
     */
    public List<ChecheApplyControl> toControlSample(Long processId) {
        CheckUtils.checkNotNull(processId, "process_id can NOT be null.");
        List<ChecheApplyControl> ret = new ArrayList<>();
        if (applyData != null) {
            for (ControlItem one : applyData.getApplyContents()) {
                ChecheApplyControl tmp = new ChecheApplyControl();
                tmp.setProcessId(processId);
                tmp.setControlId(one.getId());
                tmp.setName(one.getName());
                tmp.setValue(one.getValue());
                tmp.setCreator(user);
                tmp.setModifier(user);
                ret.add(tmp);
            }
        }
        return ret;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TemplateContent getApplyData() {
        return applyData;
    }

    public void setApplyData(TemplateContent applyData) {
        this.applyData = applyData;
    }
}
