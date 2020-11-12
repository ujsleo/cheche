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
	 * 获取事件列表
	 * 
	 * @param processId 流程ID
	 * @return
	 */
	List<ChecheApplyEvent> findLst(Long processId);
}