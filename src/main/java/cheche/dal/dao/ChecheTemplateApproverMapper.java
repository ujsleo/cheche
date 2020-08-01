package cheche.dal.dao;

import cheche.dal.entity.ChecheTemplateApprover;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 审批规则DAO
 * 
 * @author jieli
 *
 */
public interface ChecheTemplateApproverMapper extends Mapper<ChecheTemplateApprover> {
	/**
	 * 删除所有审批规则
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
	void batchInsert(List<ChecheTemplateApprover> lst);
}