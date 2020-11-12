package cheche.core.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cheche.core.exception.ChecheException;
import cheche.core.exception.ChecheExceptionEnum;
import cheche.dal.dao.ChecheTemplateMapper;
import cheche.dal.entity.ChecheTemplate;

/**
 * 模板查询小助手
 *
 * @author jieli
 */
@Component
public class TemplateHelper {
    @Autowired
    private ChecheTemplateMapper templateDao;

    /**
     * 校验模板是否存在
     * 
     * @param code 模板CODE
     * @return true-存在
     */
    public boolean checkExist(String code) {
        return templateDao.findByCode(code) != null;
    }

    /**
     * 校验模板是否存在（若不存在就抛异常）
     * 
     * @param code 模板CODE
     * @return 模板ID
     */
    public Long checkExistThrowable(String code) {
        ChecheTemplate templatePojo = templateDao.findByCode(code);
        if (templatePojo == null)
            throw new ChecheException(ChecheExceptionEnum.E400, //
                    String.format("Illegal opt, template_code: %s NOT exists.", code));
        return templatePojo.getId();
    }
}
