package cheche.controller.vo;

import java.util.List;

import cheche.core.dto.approval.MyApprovalSummary;

/**
 * 我审批的Response
 * 
 * 返回值："我审批的"审批摘要
 * 
 * @author jieli
 *
 */
public class OaMyApprovalResponse extends BaseResponse<List<MyApprovalSummary>> {
	/** 总数 */
	private Integer total;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
