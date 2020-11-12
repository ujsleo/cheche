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
import cheche.controller.vo.OaTaskBatchPassRequest;
import cheche.controller.vo.OaTaskBatchPassResponse;
import cheche.controller.vo.OaTaskPassResponse;
import cheche.controller.vo.OaTaskRejectResponse;
import cheche.controller.vo.OaTaskTransferResponse;
import cheche.core.dto.apply.ApproveOpt;
import cheche.core.dto.apply.RejectOpt;
import cheche.core.dto.apply.TransferOpt;
import cheche.core.service.OaTaskSvc;
import cheche.dal.cache.IRedis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 审批节点Controller
 * 
 * @author jieli
 */
@Api(tags = "审批节点")
@RestController
@RequestMapping(ApiConst.UA + "/task")
public class OaTaskController {
    private final static Logger log = LoggerFactory.getLogger(OaTaskController.class);
    @Autowired
    private IRedis              redis;
    @Autowired
    private OaTaskSvc           svc;

    @ApiOperation(value = "（处理人）审批通过")
    @PostMapping(value = "/pass")
    public OaTaskPassResponse pass(@Valid @RequestBody ApproveOpt request) {
        String title = "OaTaskPass";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        OaTaskPassResponse response = new OaTaskPassResponse();
        // 防并发锁
        String key = String.format(RedisLockConst.LOCK_TASK_BY_ID, request.getTaskId());
        String value = RandomUtils.nextSN();
        if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
            response.lockFailedHandler();
        } else {
            try {
                svc.pass(request);
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

    @ApiOperation(value = "（处理人）审批驳回")
    @PostMapping(value = "/reject")
    public OaTaskRejectResponse reject(@Valid @RequestBody RejectOpt request) {
        String title = "OaTaskReject";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        OaTaskRejectResponse response = new OaTaskRejectResponse();
        // 防并发锁
        String key = String.format(RedisLockConst.LOCK_TASK_BY_ID, request.getTaskId());
        String value = RandomUtils.nextSN();
        if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
            response.lockFailedHandler();
        } else {
            try {
                svc.reject(request);
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

    @ApiOperation(value = "（处理人）转办")
    @PostMapping(value = "/transfer")
    public OaTaskTransferResponse transfer(@Valid @RequestBody TransferOpt request) {
        String title = "OaTaskTransfer";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        OaTaskTransferResponse response = new OaTaskTransferResponse();
        // 防并发锁
        String key = String.format(RedisLockConst.LOCK_TASK_BY_ID, request.getTaskId());
        String value = RandomUtils.nextSN();
        if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
            response.lockFailedHandler();
        } else {
            try {
                svc.transfer(request);
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

    @ApiOperation(value = "（处理人）批量审批通过")
    @PostMapping(value = "/batchPass")
    public OaTaskBatchPassResponse batchPass(@Valid @RequestBody OaTaskBatchPassRequest request) {
        String title = "OaTaskBatchPass";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        OaTaskBatchPassResponse response = new OaTaskBatchPassResponse();
        try {
            doBatchPass(request, response);
        } catch (Exception e) {
            log.info(LogUtils.errorPattern(title), e);
            response.exceptionHandler(e);
        }
        log.info(LogUtils.responsePattern(title), JsonUtils.toJSONString(response));
        return response;
    }

    private void doBatchPass(OaTaskBatchPassRequest request, OaTaskBatchPassResponse response) {
        int fail = 0;
        StringBuilder errorMsg = new StringBuilder();
        for (Long taskId : request.getLst()) {
            ApproveOpt req = new ApproveOpt();
            req.setTaskId(taskId);
            req.setUser(request.getUser());
            req.setRemark("批量通过");
            OaTaskPassResponse resp = pass(req);
            if (!resp.isSuccess()) {
                fail++;
                errorMsg.append(String.format("{task_id: %d - %s}", taskId, resp.getErrorMsg()));
            }
        }
        response.setValue(fail);
        if (fail > 0) {
            response.setErrorMsg(String.format("批量审批通过 %d 件失败，详情：%s", fail, errorMsg.toString()));
        }
    }
}
