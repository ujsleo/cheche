package cheche.dal.dao;

import org.apache.ibatis.annotations.Param;

import cheche.dal.entity.ChecheApplyTask;
import tk.mybatis.mapper.common.Mapper;

/**
 * 审批节点DAO
 * 
 * @author jieli
 */
public interface ChecheApplyTaskMapper extends Mapper<ChecheApplyTask> {
    /** 通过 */
    void pass(@Param("id") Long id, @Param("user") String user);

    /**
     * 驳回
     * 
     * @param processId 流程ID
     * @param fromStep 起级次
     * @param toStep 止级次
     */
    void reject(@Param("processId") Long processId, @Param("fromStep") Integer fromStep,
                @Param("toStep") Integer toStep);

    /** 撤回 */
    void withdraw(Long processId);
}
