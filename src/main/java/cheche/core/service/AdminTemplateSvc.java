package cheche.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cheche.core.dto.constant.TemplateStatus;
import cheche.core.dto.template.TemplateContent;
import cheche.core.exception.ChecheException;
import cheche.core.exception.ChecheExceptionEnum;
import cheche.dal.dao.ChecheTemplateApproverMapper;
import cheche.dal.dao.ChecheTemplateControlMapper;
import cheche.dal.dao.ChecheTemplateMapper;
import cheche.dal.entity.ChecheTemplate;

/**
 * 模板管理服务
 * 
 * @author jieli
 *
 */
@Component
public class AdminTemplateSvc {
	@Autowired
	private ChecheTemplateMapper templateDao;
	@Autowired
	private ChecheTemplateControlMapper templateControlDao;
	@Autowired
	private ChecheTemplateApproverMapper templateApproverDao;

	/**
	 * 保存模板
	 * 
	 * @param templateContent 模板详情
	 */
	@Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 60, rollbackFor = Exception.class)
	public void save(TemplateContent templateContent) {
		Long templateId = null;

		ChecheTemplate templatePojo = templateDao.findByCode(templateContent.getCode());
		ChecheTemplate templateSample = templateContent.toTemplatePojo();
		if (templatePojo != null) { // 修改模板
			templateId = templatePojo.getId();
			templateSample.setId(templateId);
			templateDao.updateByPrimaryKeySelective(templateSample);

			// 删除原模板控件
			templateControlDao.deleteAll(templateId);
			// 删除原审批规则
			templateApproverDao.deleteAll(templateId);
		} else { // 新建模板
			templateSample.setStatus(TemplateStatus.ENABLE); // 默认启用
			templateDao.insertSelective(templateSample);
			templateId = templateSample.getId();
		}

		// 添加新模板控件
		templateControlDao.batchInsert(templateContent.toTemplateControlPojos(templateId));
		// 添加新审批规则
		templateApproverDao.batchInsert(templateContent.toTemplateApproverPojos(templateId));
	}

	/**
	 * 启用/停用模板
	 * 
	 * @param templateCode 模板CODE
	 * @param status       模板状态：0-停用 1-启用
	 */
	@Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 60, rollbackFor = Exception.class)
	public void opt(String templateCode, Integer status) {
		ChecheTemplate templatePojo = templateDao.findByCode(templateCode);
		if (templatePojo == null)
			throw new ChecheException(ChecheExceptionEnum.E400, //
					String.format("Illegal opt, template_code: %s NOT exists.", templateCode));
		if (templatePojo.getStatus() == status)
			throw new ChecheException(ChecheExceptionEnum.E403, //
					String.format("Illegal opt, status: %d ALREADY set.", status));

		switch (status) {
		case TemplateStatus.DISABLE:
			templateDao.disable(templateCode);
			break;
		case TemplateStatus.ENABLE:
			templateDao.enable(templateCode);
			break;
		default:
			throw new ChecheException(ChecheExceptionEnum.E403, "Illegal status.");
		}
	}

	/**
	 * 删除模板
	 * 
	 * @param templateCode 模板CODE
	 */
	@Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 60, rollbackFor = Exception.class)
	public void delete(String templateCode) {
		ChecheTemplate templatePojo = templateDao.findByCode(templateCode);
		if (templatePojo == null)
			throw new ChecheException(ChecheExceptionEnum.E400, //
					String.format("Illegal opt, template_code: %s NOT exists.", templateCode));

		// 删除模板控件
		templateControlDao.deleteAll(templatePojo.getId());
		// 删除审批规则
		templateApproverDao.deleteAll(templatePojo.getId());
		// 删除模板信息
		templateDao.deleteByCode(templateCode);

		// TODO 模板删除后，该模板的申请记录将被删除且不可恢复
	}
}
