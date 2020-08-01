package cheche.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cheche.controller.constant.RedisLockConst;
import cheche.controller.vo.OaTaskPassResponse;
import cheche.controller.vo.OaTaskRejectResponse;
import cheche.controller.vo.OaTaskTransferResponse;
import cheche.core.dto.apply.ApproveOpt;
import cheche.core.dto.apply.RejectOpt;
import cheche.core.dto.apply.TransferOpt;
import cheche.core.service.OaTaskSvc;
import cheche.dal.cache.IRedis;
import common.util.tools.JsonUtils;
import common.util.tools.LogUtils;
import common.util.tools.RandomUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 业务审批节点Controller
 * 
 * @author jieli
 *
 */
@Api(tags = "业务审批节点")
@RestController
@RequestMapping("/oa/task")
public class OaTaskController {
	private final static Logger log = LoggerFactory.getLogger(OaTaskController.class);
	@Autowired
	private IRedis redis;
	@Autowired
	private OaTaskSvc taskSvc;

	/** （处理人）审批通过 */
	@ApiOperation(value = "（处理人）审批通过")
	@PostMapping(value = "/pass")
	public OaTaskPassResponse pass(@RequestBody ApproveOpt request) {
		String title = "OaTaskPass";
		log.info(LogUtils.requestPattern(), title, JsonUtils.convert2Json(request));
		OaTaskPassResponse response = new OaTaskPassResponse();
		// 防并发锁
		String key = String.format(RedisLockConst.LOCK_TASK_BY_ID, request.getTaskId());
		String value = RandomUtils.nextSN();
		if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
			response.lockFailedHandler();
		} else {
			try {
				taskSvc.pass(request);
			} catch (Exception e) {
				log.info(LogUtils.errorPattern(title), e);
				response.exceptionHandler(e);
			} finally {
				redis.unLock(key, value);
			}
		}
		log.info(LogUtils.responsePattern(), title, JsonUtils.convert2Json(response));
		return response;
	}

	/** （处理人）审批驳回 */
	@ApiOperation(value = "（处理人）审批驳回")
	@PostMapping(value = "/reject")
	public OaTaskRejectResponse reject(@RequestBody RejectOpt request) {
		String title = "OaTaskReject";
		log.info(LogUtils.requestPattern(), title, JsonUtils.convert2Json(request));
		OaTaskRejectResponse response = new OaTaskRejectResponse();
		// 防并发锁
		String key = String.format(RedisLockConst.LOCK_TASK_BY_ID, request.getTaskId());
		String value = RandomUtils.nextSN();
		if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
			response.lockFailedHandler();
		} else {
			try {
				taskSvc.reject(request);
			} catch (Exception e) {
				log.info(LogUtils.errorPattern(title), e);
				response.exceptionHandler(e);
			} finally {
				redis.unLock(key, value);
			}
		}
		log.info(LogUtils.responsePattern(), title, JsonUtils.convert2Json(response));
		return response;
	}

	/** （处理人）转办 */
	@ApiOperation(value = "（处理人）转办")
	@PostMapping(value = "/transfer")
	public OaTaskTransferResponse transfer(@RequestBody TransferOpt request) {
		String title = "OaTaskTransfer";
		log.info(LogUtils.requestPattern(), title, JsonUtils.convert2Json(request));
		OaTaskTransferResponse response = new OaTaskTransferResponse();
		// 防并发锁
		String key = String.format(RedisLockConst.LOCK_TASK_BY_ID, request.getTaskId());
		String value = RandomUtils.nextSN();
		if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
			response.lockFailedHandler();
		} else {
			try {
				taskSvc.transfer(request);
			} catch (Exception e) {
				log.info(LogUtils.errorPattern(title), e);
				response.exceptionHandler(e);
			} finally {
				redis.unLock(key, value);
			}
		}
		log.info(LogUtils.responsePattern(), title, JsonUtils.convert2Json(response));
		return response;
	}
}
