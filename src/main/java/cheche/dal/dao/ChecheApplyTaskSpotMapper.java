package cheche.dal.dao;

import java.util.List;

import cheche.core.dto.approval.MyApprovalSummary;
import cheche.dal.entity.ChecheApplyTaskSpot;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 审批节点的关注DAO
 * 
 * @author jieli
 *
 */
public interface ChecheApplyTaskSpotMapper extends Mapper<ChecheApplyTaskSpot> {
	/**
	 * 通过审批节点ID获取关注列表
	 * 
	 * @param taskId 审批节点ID
	 * @return 关注列表
	 */
	List<ChecheApplyTaskSpot> findAll(Long taskId);

	/**
	 * 获取“我审批的”
	 * 
	 * @param user         申请人的域账号
	 * @param status       流程状态
	 * @param templateCode 模板CODE
	 * @param offset       偏移量
	 * @param rows         行数
	 * @return
	 */
	List<MyApprovalSummary> findMyApproval(@Param("user") String user, @Param("status") Integer status,
			@Param("templateCode") String templateCode, @Param("offset") Integer offset, @Param("rows") Integer rows);

	/**
	 * 统计“我审批的”
	 * 
	 * @param user         申请人的域账号
	 * @param status       流程状态
	 * @param templateCode 模板CODE
	 * @return
	 */
	Integer cntTotalApproval(@Param("user") String user, @Param("status") Integer status,
			@Param("templateCode") String templateCode);

	/** 通过 */
	void pass(@Param("taskId") Long taskId, @Param("user") String user, @Param("remark") String remark);

	/**
	 * 驳回
	 * 
	 * @param processId 审批流程ID
	 * @param fromStep  起级次
	 * @param toStep    止级次
	 */
	void reject(@Param("processId") Long processId, @Param("fromStep") Integer fromStep,
			@Param("toStep") Integer toStep);

	/** 撤回 */
	void withdraw(Long processId);

	/**
	 * 转办
	 * 
	 * @param taskId 审批节点ID
	 * @param user   交办人
	 * @param agent  转办人
	 */
	void transfer(@Param("taskId") Long taskId, @Param("user") String user, @Param("agent") String agent);

	/**
	 * 批量插入
	 * 
	 * @param lst Sample列表
	 * @return
	 */
	void batchInsert(List<ChecheApplyTaskSpot> lst);
}