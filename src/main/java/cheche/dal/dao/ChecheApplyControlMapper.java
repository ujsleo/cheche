package cheche.dal.dao;

import cheche.dal.entity.ChecheApplyControl;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 审批申请数据DAO
 * 
 * @author jieli
 *
 */
public interface ChecheApplyControlMapper extends Mapper<ChecheApplyControl> {
	/**
	 * 删除所有模板控件
	 * 
	 * @param processId 流程ID
	 */
	void deleteAll(Long processId);

	/**
	 * 批量插入
	 * 
	 * @param lst Sample列表
	 * @return
	 */
	int batchInsert(List<ChecheApplyControl> lst);
}