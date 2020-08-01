package cheche.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cheche.controller.vo.OaMyApplyResponse;
import cheche.controller.vo.OaMyApprovalResponse;
import cheche.controller.vo.OaMyBaseRequest;
import cheche.core.service.OaMySvc;
import common.util.tools.JsonUtils;
import common.util.tools.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 我的审批Controller
 * 
 * @author jieli
 *
 */
@Api(tags = "我的审批")
@RestController
@RequestMapping("/oa/my")
public class OaMyController {
	private final static Logger log = LoggerFactory.getLogger(OaMyController.class);
	@Autowired
	private OaMySvc mySvc;

	/** （申请人）我发起的 */
	@ApiOperation(value = "（申请人）我发起的")
	@PostMapping(value = "/apply")
	public OaMyApplyResponse apply(@RequestBody OaMyBaseRequest request) {
		String title = "OaMyApply";
		log.info(LogUtils.requestPattern(), title, request);
		OaMyApplyResponse response = new OaMyApplyResponse();
		try {
			response.setValue(mySvc.apply(request));
			response.setTotal(mySvc.totalApply(request));
		} catch (Exception e) {
			log.info(LogUtils.errorPattern(title), e);
			response.exceptionHandler(e);
		}
		log.info(LogUtils.responsePattern(), title, JsonUtils.convert2Json(response));
		return response;
	}

	/** （处理人）我审批的 */
	@ApiOperation(value = "（处理人）我审批的")
	@PostMapping(value = "/approval")
	public OaMyApprovalResponse approval(@RequestBody OaMyBaseRequest request) {
		String title = "OaMyApproval";
		log.info(LogUtils.requestPattern(), title, request);
		OaMyApprovalResponse response = new OaMyApprovalResponse();
		try {
			response.setValue(mySvc.approval(request));
			response.setTotal(mySvc.totalApproval(request));
		} catch (Exception e) {
			log.info(LogUtils.errorPattern(title), e);
			response.exceptionHandler(e);
		}
		log.info(LogUtils.responsePattern(), title, JsonUtils.convert2Json(response));
		return response;
	}
}
