package cheche.dal.dao;

import cheche.dal.entity.ChecheApplyTask;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 审批节点DAO
 * 
 * @author jieli
 *
 */
public interface ChecheApplyTaskMapper extends Mapper<ChecheApplyTask> {
	/** 通过 */
	void pass(Long id);

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
}