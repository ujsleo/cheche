package cheche.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cheche.controller.constant.RedisLockConst;
import cheche.controller.vo.OaProcessApplyResponse;
import cheche.controller.vo.OaProcessWithdrawResponse;
import cheche.core.dto.apply.ApplyContent;
import cheche.core.dto.apply.WithdrawOpt;
import cheche.core.service.OaProcessSvc;
import cheche.dal.cache.IRedis;
import common.util.tools.JsonUtils;
import common.util.tools.LogUtils;
import common.util.tools.RandomUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 业务审批流程Controller
 * 
 * @author jieli
 *
 */
@Api(tags = "业务审批流程")
@RestController
@RequestMapping("/oa/process")
public class OaProcessController {
	private final static Logger log = LoggerFactory.getLogger(OaProcessController.class);
	@Autowired
	private IRedis redis;
	@Autowired
	private OaProcessSvc processSvc;

	/** （申请人）发起审批申请 */
	@ApiOperation(value = "（申请人）发起审批申请")
	@PostMapping(value = "/apply")
	public OaProcessApplyResponse apply(@RequestBody ApplyContent request) {
		String title = "OaProcessApply";
		log.info(LogUtils.requestPattern(), title, JsonUtils.convert2Json(request));
		OaProcessApplyResponse response = new OaProcessApplyResponse();
		// 防并发锁
		String key = String.format(RedisLockConst.LOCK_PROCESS_BY_CODE, request.processCode());
		String value = RandomUtils.nextSN();
		if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
			response.lockFailedHandler();
		} else {
			try {
				response.setValue(processSvc.apply(request));
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

	/** （申请人）撤回审批申请 */
	@ApiOperation(value = "（申请人）撤回审批申请")
	@PostMapping(value = "/withdraw")
	public OaProcessWithdrawResponse withdraw(@RequestBody WithdrawOpt request) {
		String title = "OaProcessWithdraw";
		log.info(LogUtils.requestPattern(), title, JsonUtils.convert2Json(request));
		OaProcessWithdrawResponse response = new OaProcessWithdrawResponse();
		// 防并发锁
		String key = String.format(RedisLockConst.LOCK_PROCESS_BY_ID, request.getProcessId());
		String value = RandomUtils.nextSN();
		if (!redis.tryLock(key, RedisLockConst.DEFAULT_EXPIRES, value)) {
			response.lockFailedHandler();
		} else {
			try {
				processSvc.withdraw(request);
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
