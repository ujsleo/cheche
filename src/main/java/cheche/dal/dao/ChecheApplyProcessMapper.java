package cheche.dal.dao;

import java.util.List;

import cheche.core.dto.approval.MyApplySummary;
import cheche.dal.entity.ChecheApplyProcess;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 审批流程DAO
 * 
 * @author jieli
 *
 */
public interface ChecheApplyProcessMapper extends Mapper<ChecheApplyProcess> {
	/**
	 * 通过CODE获取流程详情
	 * 
	 * @param code 流程CODE
	 * @return 流程详情
	 */
	ChecheApplyProcess findByCode(String code);

	/**
	 * 获取“我发起的”
	 * 
	 * @param user         申请人的域账号
	 * @param status       流程状态
	 * @param templateCode 模板CODE
	 * @param offset       偏移量
	 * @param rows         行数
	 * @return
	 */
	List<MyApplySummary> findMyApply(@Param("user") String user, @Param("status") Integer status,
			@Param("templateCode") String templateCode, @Param("offset") Integer offset, @Param("rows") Integer rows);

	/**
	 * 统计“我发起的”
	 * 
	 * @param user         申请人的域账号
	 * @param status       流程状态
	 * @param templateCode 模板CODE
	 * @return
	 */
	Integer cntTotalApply(@Param("user") String user, @Param("status") Integer status,
			@Param("templateCode") String templateCode);

	/** 成功结束 */
	void completed(Long id);

	/** 被驳回而终止 */
	void terminated(@Param("id") Long id, @Param("step") Integer step);

	/** 撤回 */
	void withdraw(Long id);

	/** 更新级次 */
	void updateStep(@Param("id") Long id, @Param("step") Integer step);
}