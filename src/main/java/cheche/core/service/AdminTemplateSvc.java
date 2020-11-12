package cheche.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cheche.core.dto.constant.TemplateStatus;
import cheche.core.dto.template.TemplateContent;
import cheche.core.exception.ChecheException;
import cheche.core.exception.ChecheExceptionEnum;
import cheche.core.helper.TemplateHelper;
import cheche.dal.dao.ChecheTemplateApproverMapper;
import cheche.dal.dao.ChecheTemplateControlMapper;
import cheche.dal.dao.ChecheTemplateHookMapper;
import cheche.dal.dao.ChecheTemplateMapper;
import cheche.dal.entity.ChecheTemplate;
import cheche.dal.entity.ChecheTemplateApprover;
import cheche.dal.entity.ChecheTemplateControl;
import cheche.dal.entity.ChecheTemplateHook;

/**
 * 模板管理服务
 * 
 * @author jieli
 */
@Component
public class AdminTemplateSvc {
    @Autowired
    private TemplateHelper               templateHelper;
    @Autowired
    private ChecheTemplateMapper         templateDao;
    @Autowired
    private ChecheTemplateControlMapper  templateControlDao;
    @Autowired
    private ChecheTemplateApproverMapper templateApproverDao;
    @Autowired
    private ChecheTemplateHookMapper     templateHookDao;

    /**
     * 保存模板
     * 
     * @param templateContent 模板详情
     * @return 模板ID
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 60, rollbackFor = Exception.class)
    public Long save(TemplateContent templateContent) {
        Long templateId = null;
        ChecheTemplate templatePojo = templateDao.findByCode(templateContent.getCode());
        ChecheTemplate templateSample = templateContent.toTemplateSample();
        if (templatePojo != null) { // 修改模板
            templateId = templatePojo.getId();
            templateSample.setId(templateId);
            templateDao.updateByPrimaryKeySelective(templateSample);
            // 删除模板的额外信息
            doDeleteExtra(templateId);
        } else { // 新建模板
            templateSample.setStatus(TemplateStatus.ENABLE); // 默认启用
            templateDao.insertSelective(templateSample);
            templateId = templateSample.getId();
        }
        // 保存模板的额外信息
        doSaveExtra(templateId, templateContent);
        return templateId;
    }

    /**
     * 保存模板的额外信息
     * 
     * @param templateId 模板ID
     * @param templateContent 模板详情
     */
    private void doSaveExtra(Long templateId, TemplateContent templateContent) {
        // 添加模板控件
        for (ChecheTemplateControl control : templateContent.toTemplateControlSample(templateId))
            templateControlDao.insertSelective(control);
        // 添加审批规则
        for (ChecheTemplateApprover approver : templateContent.toTemplateApproverSample(templateId)) {
            templateApproverDao.insertSelective(approver);
            // 添加Hook配置信息
            for (ChecheTemplateHook hook : templateContent.toTemplateHookSample(templateId, approver.getId(),
                    approver.getStep()))
                templateHookDao.insertSelective(hook);
        }
    }

    /**
     * 启用/停用模板
     * 
     * @param templateCode 模板CODE
     * @param status 模板状态：0-停用 1-启用
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 60, rollbackFor = Exception.class)
    public void opt(String templateCode, Integer status) {
        // 校验
        templateHelper.checkExistThrowable(templateCode);

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
        // 校验
        Long templateId = templateHelper.checkExistThrowable(templateCode);

        // 删除模板的额外信息
        doDeleteExtra(templateId);
        // 删除模板信息
        templateDao.deleteByPrimaryKey(templateId);
        // TODO 模板删除后，该模板的申请记录将被删除且不可恢复
    }

    /**
     * 删除模板的额外信息
     * 
     * @param templateId 模板ID
     */
    private void doDeleteExtra(Long templateId) {
        // 删除模板控件
        templateControlDao.deleteAll(templateId);
        // 删除审批规则
        templateApproverDao.deleteAll(templateId);
        // 删除Hook配置信息
        templateHookDao.deleteAll(templateId);
    }
}
