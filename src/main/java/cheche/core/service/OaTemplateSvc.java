package cheche.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cheche.core.dto.template.ApproverItem;
import cheche.core.dto.template.ExtraAttr;
import cheche.core.dto.template.HookItem;
import cheche.core.dto.template.TemplateContent;
import cheche.core.dto.template.TemplateInfo;
import cheche.dal.dao.ChecheTemplateHookMapper;
import cheche.dal.dao.ChecheTemplateMapper;
import cheche.dal.entity.ChecheTemplateHook;

/**
 * 审批模板服务
 * 
 * @author jieli
 */
@Component
public class OaTemplateSvc {
    @Autowired
    private ChecheTemplateMapper     templateDao;
    @Autowired
    private ChecheTemplateHookMapper templateHookDao;

    /**
     * 获取模板详情
     * 
     * @param templateCode 模板CODE
     * @return 模板详情
     */
    public TemplateContent detail(String templateCode) {
        TemplateContent ret = templateDao.findOne(templateCode);
        if (ret == null)
            return null;

        // 模板的额外信息
        for (ApproverItem one : ret.getApprover()) {
            List<ChecheTemplateHook> hookPojos = templateHookDao.findLst(one.getId());
            if (CollectionUtils.isEmpty(hookPojos))
                continue;
            ExtraAttr extraAttr = new ExtraAttr();
            extraAttr.setHook(hookPojos.stream().map(x -> {
                return new HookItem(x);
            }).collect(Collectors.toList()));
            one.setExtraAttr(extraAttr);
        }
        return ret;
    }

    /**
     * 获取模板列表
     * 
     * @param status 模板状态：null-全部 0-已停用 1-已启用
     * @return 模板列表
     */
    public List<TemplateInfo> lst(Integer status) {
        return templateDao.findLst(status);
    }
}
