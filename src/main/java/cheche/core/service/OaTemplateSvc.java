package cheche.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cheche.core.dto.template.TemplateContent;
import cheche.core.dto.template.TemplateInfo;
import cheche.dal.dao.ChecheTemplateMapper;

/**
 * 业务审批模板服务
 * 
 * @author jieli
 *
 */
@Component
public class OaTemplateSvc {
	@Autowired
	private ChecheTemplateMapper templateDao;

	/**
	 * 获取模板详情
	 * 
	 * @param templateCode 模板CODE
	 * @return 模板详情
	 */
	public TemplateContent getTemplateContent(String templateCode) {
		return templateDao.findOne(templateCode);
	}

	/**
	 * 获取模板列表
	 * 
	 * @param status 模板状态：null-全部 0-已停用 1-已启用
	 * @return
	 */
	public List<TemplateInfo> getTemplateLst(Integer status) {
		return templateDao.findLst(status);
	}
}
