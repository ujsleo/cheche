package cheche.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cheche.common.utils.JsonUtils;
import cheche.common.utils.LogUtils;
import cheche.common.utils.RandomUtils;
import cheche.controller.constant.ApiConst;
import cheche.controller.constant.RedisLockConst;
import cheche.controller.vo.OaProcessApplyResponse;
import cheche.controller.vo.OaProcessGetResponse;
import cheche.controller.vo.OaProcessWithdrawResponse;
import cheche.core.dto.apply.ApplyContent;
import cheche.core.dto.apply.WithdrawOpt;
import cheche.core.service.OaProcessSvc;
import cheche.dal.cache.IRedis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 审批流程Controller
 * 
 * @author jieli
 */
@Api(tags = "审批流程")
@RestController
@RequestMapping(ApiConst.UA + "/process")
public class OaProcessController {
    private final static Logger log = LoggerFactory.getLogger(OaProcessController.class);
    @Autowired
    private IRedis              redis;
    @Autowired
    private OaProcessSvc        svc;

    @ApiOperation(value = "（申请人）发起审批申请")
    @PostMapping(value = "/apply")
    public OaProcessApplyResponse apply(@Valid @RequestBody ApplyContent request) {
        String title = "OaProcessApply";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        OaProcessApplyResponse response = new OaProcessApplyResponse();
        // 防并发锁
        String key = String.format(RedisLockConst.LOCK_PROCESS_BY_CODE, request.processCode());
        String value = RandomUtils.nextSN();
        if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
            response.lockFailedHandler();
        } else {
            try {
                response.setValue(svc.apply(request));
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

    @ApiOperation(value = "（申请人）撤回审批申请")
    @PostMapping(value = "/withdraw")
    public OaProcessWithdrawResponse withdraw(@Valid @RequestBody WithdrawOpt request) {
        String title = "OaProcessWithdraw";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        OaProcessWithdrawResponse response = new OaProcessWithdrawResponse();
        // 防并发锁
        String key = String.format(RedisLockConst.LOCK_PROCESS_BY_ID, request.getProcessId());
        String value = RandomUtils.nextSN();
        if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
            response.lockFailedHandler();
        } else {
            try {
                svc.withdraw(request);
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

    @ApiOperation(value = "获取审批申请详情")
    @GetMapping(value = "/get")
    public OaProcessGetResponse get(@ApiParam(value = "流程ID", required = true) //
    @RequestParam(value = "id") Long request) {
        String title = "OaProcessGet";
        log.info(LogUtils.requestPattern(title), request);
        OaProcessGetResponse response = new OaProcessGetResponse();
        try {
            response.setValue(svc.detail(request));
        } catch (Exception e) {
            log.info(LogUtils.errorPattern(title), e);
            response.exceptionHandler(e);
        }
        log.info(LogUtils.responsePattern(title), JsonUtils.toJSONString(response));
        return response;
    }
}
