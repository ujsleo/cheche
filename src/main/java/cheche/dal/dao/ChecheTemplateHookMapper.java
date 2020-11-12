package cheche.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cheche.dal.entity.ChecheTemplateHook;
import tk.mybatis.mapper.common.Mapper;

public interface ChecheTemplateHookMapper extends Mapper<ChecheTemplateHook> {
    /**
     * 通过流程ID+级次+回调类型唯一获取
     * 
     * @param processId 流程ID
     * @param step 级次
     * @param type 回调类型。1-发起start 2-通过pass 4-驳回reject 8-撤回back
     * @return
     */
    ChecheTemplateHook findOne(@Param("processId") Long processId, @Param("step") Integer step,
                               @Param("type") Integer type);

    /**
     * 通过审批规则ID获取列表
     * 
     * @param approverId 审批规则ID
     * @return
     */
    List<ChecheTemplateHook> findLst(@Param("approverId") Long approverId);

    void deleteAll(@Param("templateId") Long templateId);
}
