package cheche.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cheche.controller.vo.OaSearchRequest;
import cheche.core.dto.approval.MyApplySummary;
import cheche.core.dto.approval.MyApprovalSummary;
import cheche.dal.dao.ChecheApplyProcessMapper;
import cheche.dal.dao.ChecheApplyTaskSpotMapper;

/**
 * 审批搜索服务
 * 
 * @author jieli
 *
 */
@Component
public class OaSearchSvc {
	@Autowired
	private ChecheApplyProcessMapper processDao;
	@Autowired
	private ChecheApplyTaskSpotMapper taskSpotDao;

	/** （申请人）搜索'我发起的' */
	public List<MyApplySummary> apply(OaSearchRequest request) {
		return processDao.searchMyApply(request.getUser(), request.getWd(), request.offset(), request.getRows());
	}

	/** （申请人）统计搜索'我发起的' */
	public Integer totalApply(OaSearchRequest request) {
		return processDao.cntSearchMyApply(request.getUser(), request.getWd());
	}

	/** （处理人）搜索'我审批的' */
	public List<MyApprovalSummary> approval(OaSearchRequest request) {
		return taskSpotDao.searchMyApproval(request.getUser(), request.getWd(), request.offset(), request.getRows());
	}

	/** （处理人）统计搜索'我审批的' */
	public Integer totalApproval(OaSearchRequest request) {
		return taskSpotDao.cntSearchMyApproval(request.getUser(), request.getWd());
	}
}
