package cheche.dal.dao;

import java.util.List;

import cheche.dal.entity.ChecheApplyEvent;
import tk.mybatis.mapper.common.Mapper;

/**
 * 审批事件DAO
 * 
 * @author jieli
 *
 */
public interface ChecheApplyEventMapper extends Mapper<ChecheApplyEvent> {
	/**
	 * 查找所有审批事件
	 * 
	 * @param processId 流程ID
	 * @return
	 */
	List<ChecheApplyEvent> findAll(Long processId);
}