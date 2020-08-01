package cheche.dal.dao;

import java.util.List;

import cheche.core.dto.template.TemplateContent;
import cheche.core.dto.template.TemplateInfo;
import cheche.dal.entity.ChecheTemplate;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * 模板DAO
 * 
 * @author jieli
 *
 */
public interface ChecheTemplateMapper extends Mapper<ChecheTemplate> {
	/**
	 * 通过CODE获取模板详情
	 * 
	 * @param code 模板CODE
	 * @return 模板详情
	 */
	TemplateContent findOne(String code);

	/** 获取模板列表 */
	List<TemplateInfo> findLst(@Param("status") Integer status);

	/**
	 * 通过CODE获取模板POJO
	 * 
	 * @param code 模板CODE
	 * @return 模板POJO
	 */
	ChecheTemplate findByCode(String code);

	/**
	 * 启用模板
	 * 
	 * @param code 模板CODE
	 */
	void enable(String code);

	/**
	 * 停用模板
	 * 
	 * @param code 模板CODE
	 */
	void disable(String code);

	/**
	 * 删除模板
	 * 
	 * @param code 模板CODE
	 */
	void deleteByCode(String code);
}