package cheche.core.dto.apply;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import cheche.core.dto.constant.ApplyProcessStatus;
import cheche.core.dto.template.ControlItem;
import cheche.core.dto.template.TemplateContent;
import cheche.dal.entity.ChecheApplyControl;
import cheche.dal.entity.ChecheApplyProcess;
import common.util.tools.CheckUtils;
import common.util.tools.RandomUtils;

/**
 * 审批申请
 * 
 * @author jieli
 *
 */
public class ApplyContent {
	/** 模板CODE */
	@NotNull(message = "template_code can NOT be empty or null.")
	private String templateCode;
	/** 业务CODE */
	@NotNull(message = "biz_code can NOT be empty or null.")
	private String bizCode;
	/** 申请人的域账号 */
	@NotNull(message = "user can NOT be empty or null.")
	private String user;
	/** 申请单的标题 */
	@NotNull(message = "title can NOT be empty or null.")
	private String title;
	/** 开始时间 */
	private Date startDate;
	/** 结束时间 */
	private Date endDate;
	/** 审批申请数据 */
	private TemplateContent applyData;

	/** process_code = templateCode + bizCode */
	public String processCode() {
		return templateCode + bizCode;
	}

	/**
	 * 审批申请转审批流程POJO
	 * 
	 * @return 审批流程POJO
	 */
	public ChecheApplyProcess toProcessPojo() {
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
		Date start = startDate == null ? new Date() : startDate;
		ret.setStartDate(start);
		ret.setEndDate(endDate == null ? end(start, 7) : endDate); // 默认止期=起期+7天
		ret.setCreator(user);
		return ret;
	}

	/**
	 * 止期
	 * 
	 * @param start 起期
	 * @param days  增加天数
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
	public List<ChecheApplyControl> toControlPojo(Long processId) {
		CheckUtils.checkNotNull(processId, "process_id can NOT be null.");
		List<ChecheApplyControl> ret = new ArrayList<>();
		if (applyData != null) {
			for (ControlItem one : applyData.getApplyContents()) {
				ChecheApplyControl tmp = new ChecheApplyControl();
				tmp.setProcessId(processId);
				tmp.setControlId(one.getId());
				tmp.setName(one.getName());
				tmp.setValue(one.getValue());
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
