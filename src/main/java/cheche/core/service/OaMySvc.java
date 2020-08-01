package cheche.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cheche.controller.vo.OaMyBaseRequest;
import cheche.core.dto.approval.MyApplySummary;
import cheche.core.dto.approval.MyApprovalSummary;
import cheche.dal.dao.ChecheApplyProcessMapper;
import cheche.dal.dao.ChecheApplyTaskSpotMapper;

/**
 * 我的审批服务
 * 
 * @author jieli
 *
 */
@Component
public class OaMySvc {
	@Autowired
	private ChecheApplyProcessMapper processDao;
	@Autowired
	private ChecheApplyTaskSpotMapper taskSpotDao;

	/** （申请人）我发起的 */
	public List<MyApplySummary> apply(OaMyBaseRequest request) {
		return processDao.findMyApply(request.getUser(), request.getStatus(), request.getTemplateCode(),
				request.offset(), request.getRows());
	}

	/** （申请人）统计“我发起的” */
	public Integer totalApply(OaMyBaseRequest request) {
		return processDao.cntTotalApply(request.getUser(), request.getStatus(), request.getTemplateCode());
	}

	/** （处理人）我审批的 */
	public List<MyApprovalSummary> approval(OaMyBaseRequest request) {
		return taskSpotDao.findMyApproval(request.getUser(), request.getStatus(), request.getTemplateCode(),
				request.offset(), request.getRows());
	}

	/** （处理人）统计“我审批的” */
	public Integer totalApproval(OaMyBaseRequest request) {
		return taskSpotDao.cntTotalApproval(request.getUser(), request.getStatus(), request.getTemplateCode());
	}
}
