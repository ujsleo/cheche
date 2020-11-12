package cheche.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cheche.common.utils.JsonUtils;
import cheche.common.utils.LogUtils;
import cheche.controller.constant.ApiConst;
import cheche.controller.vo.OaEventLstResponse;
import cheche.core.service.OaEventSvc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 审批事件Controller
 * 
 * @author jieli
 *
 */
@Api(tags = "审批事件")
@RestController
@RequestMapping(ApiConst.UA + "/event")
public class OaEventController {
	private final static Logger log = LoggerFactory.getLogger(OaEventController.class);
	@Autowired
	private OaEventSvc svc;

	@ApiOperation(value = "获取事件列表")
	@GetMapping(value = "/lst")
	public OaEventLstResponse lst(@ApiParam(value = "流程ID", required = true) //
	@RequestParam(value = "processId") Long request) {
		String title = "OaEventLst";
		log.info(LogUtils.requestPattern(title), request);
		OaEventLstResponse response = new OaEventLstResponse();
		try {
			response.setValue(svc.lst(request));
		} catch (Exception e) {
			log.info(LogUtils.errorPattern(title), e);
			response.exceptionHandler(e);
		}
		log.info(LogUtils.responsePattern(title), JsonUtils.toJSONString(response));
		return response;
	}
}
