package cheche.dal.dao;

import cheche.dal.entity.ChecheTemplateControl;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 模板控件DAO
 * 
 * @author jieli
 *
 */
public interface ChecheTemplateControlMapper extends Mapper<ChecheTemplateControl> {
	/**
	 * 删除所有模板控件
	 * 
	 * @param templateId 模板ID
	 */
	void deleteAll(Long templateId);

	/**
	 * 批量插入
	 * 
	 * @param lst Sample列表
	 * @return
	 */
	void batchInsert(List<ChecheTemplateControl> lst);
}