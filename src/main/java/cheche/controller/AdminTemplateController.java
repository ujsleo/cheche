package cheche.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cheche.common.utils.JsonUtils;
import cheche.common.utils.LogUtils;
import cheche.common.utils.RandomUtils;
import cheche.controller.constant.ApiConst;
import cheche.controller.constant.RedisLockConst;
import cheche.controller.vo.AdminTemplateOptRequest;
import cheche.controller.vo.AdminTemplateOptResponse;
import cheche.controller.vo.AdminTemplateSaveResponse;
import cheche.core.dto.constant.TemplateStatus;
import cheche.core.dto.template.TemplateContent;
import cheche.core.service.AdminTemplateSvc;
import cheche.dal.cache.IRedis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 审批模板管理Controller
 * 
 * @author jieli
 */
@Api(tags = "审批模板管理")
@RestController
@RequestMapping(ApiConst.Admin + "/template")
public class AdminTemplateController {
    private final static Logger log = LoggerFactory.getLogger(AdminTemplateController.class);
    @Autowired
    private IRedis              redis;
    @Autowired
    private AdminTemplateSvc    svc;

    @ApiOperation(value = "保存模板")
    @PostMapping(value = "/save")
    public AdminTemplateSaveResponse save(@Valid @RequestBody TemplateContent request) {
        String title = "AdminTemplateSave";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        AdminTemplateSaveResponse response = new AdminTemplateSaveResponse();
        // 防并发锁
        String key = RedisLockConst.LOCK_TEMPLATE_SAVE;
        String value = RandomUtils.nextSN();
        if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
            response.lockFailedHandler();
        } else {
            try {
                response.setValue(svc.save(request));
            } catch (Exception e) {
                log.info(LogUtils.errorPattern(title), e);
                response.exceptionHandler(e);
            } finally {
                redis.unLock(key, value);
            }
        }
        log.info(LogUtils.responsePattern(title), JsonUtils.toJSONString(response));
        return response;
    }

    @ApiOperation(value = "启用模板")
    @PostMapping(value = "/enable")
    public AdminTemplateOptResponse enable(@Valid @RequestBody AdminTemplateOptRequest request) {
        String title = "AdminTemplateEnable";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        AdminTemplateOptResponse response = new AdminTemplateOptResponse();
        // 防并发锁
        String key = String.format(RedisLockConst.LOCK_TEMPLATE_BY_CODE, request.getTemplateCode());
        String value = RandomUtils.nextSN();
        if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
            response.lockFailedHandler();
        } else {
            try {
                svc.opt(request.getTemplateCode(), TemplateStatus.ENABLE);
            } catch (Exception e) {
                log.info(LogUtils.errorPattern(title), e);
                response.exceptionHandler(e);
            } finally {
                redis.unLock(key, value);
            }
        }
        log.info(LogUtils.responsePattern(title), JsonUtils.toJSONString(response));
        return response;
    }

    @ApiOperation(value = "停用模板")
    @PostMapping(value = "/disable")
    public AdminTemplateOptResponse disable(@Valid @RequestBody AdminTemplateOptRequest request) {
        String title = "AdminTemplateDisable";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        AdminTemplateOptResponse response = new AdminTemplateOptResponse();
        // 防并发锁
        String key = String.format(RedisLockConst.LOCK_TEMPLATE_BY_CODE, request.getTemplateCode());
        String value = RandomUtils.nextSN();
        if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
            response.lockFailedHandler();
        } else {
            try {
                svc.opt(request.getTemplateCode(), TemplateStatus.DISABLE);
            } catch (Exception e) {
                log.info(LogUtils.errorPattern(title), e);
                response.exceptionHandler(e);
            } finally {
                redis.unLock(key, value);
            }
        }
        log.info(LogUtils.responsePattern(title), JsonUtils.toJSONString(response));
        return response;
    }

    @ApiOperation(value = "删除模板")
    @PostMapping(value = "/delete")
    public AdminTemplateOptResponse delete(@Valid @RequestBody AdminTemplateOptRequest request) {
        String title = "AdminTemplateDelete";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        AdminTemplateOptResponse response = new AdminTemplateOptResponse();
        // 防并发锁
        String key = String.format(RedisLockConst.LOCK_TEMPLATE_BY_CODE, request.getTemplateCode());
        String value = RandomUtils.nextSN();
        if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
            response.lockFailedHandler();
        } else {
            try {
                svc.delete(request.getTemplateCode());
            } catch (Exception e) {
                log.info(LogUtils.errorPattern(title), e);
                response.exceptionHandler(e);
            } finally {
                redis.unLock(key, value);
            }
        }
        log.info(LogUtils.responsePattern(title), JsonUtils.toJSONString(response));
        return response;
    }
}
