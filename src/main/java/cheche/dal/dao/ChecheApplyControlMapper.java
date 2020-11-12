package cheche.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cheche.core.dto.template.ControlItem;
import cheche.dal.entity.ChecheApplyControl;
import tk.mybatis.mapper.common.Mapper;

/**
 * 审批申请数据DAO
 * 
 * @author jieli
 */
public interface ChecheApplyControlMapper extends Mapper<ChecheApplyControl> {
    /**
     * 删除所有模板控件
     * 
     * @param processId 流程ID
     */
    void deleteAll(Long processId);

    /**
     * 获取审批申请数据列表
     * 
     * @param processId 流程ID
     * @return
     */
    List<ControlItem> findLst(Long processId);

    /**
     * 根据流程ID+控件ID唯一获取控件值
     * 
     * @param processId 流程ID
     * @param controlId 控件ID
     * @return
     */
    String findValue(@Param("processId") Long processId, @Param("controlId") Long controlId);

    /**
     * 根据流程ID+控件名唯一获取控件值
     * 
     * @param processId 流程ID
     * @param name 控件名
     * @return
     */
    String findValueByName(@Param("processId") Long processId, @Param("name") String name);
}
