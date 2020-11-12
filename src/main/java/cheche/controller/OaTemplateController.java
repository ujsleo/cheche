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
import cheche.controller.vo.OaTemplateGetResponse;
import cheche.controller.vo.OaTemplateLstResponse;
import cheche.core.service.OaTemplateSvc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 审批模板Controller
 * 
 * @author jieli
 *
 */
@Api(tags = "审批模板")
@RestController
@RequestMapping(ApiConst.UA + "/template")
public class OaTemplateController {
	private final static Logger log = LoggerFactory.getLogger(OaTemplateController.class);
	@Autowired
	private OaTemplateSvc svc;

	@ApiOperation(value = "获取模板详情")
	@GetMapping(value = "/get")
	public OaTemplateGetResponse get(@ApiParam(value = "模板CODE", required = true) //
	@RequestParam(value = "templateCode") String request) {
		String title = "OaTemplateGet";
		log.info(LogUtils.requestPattern(title), request);
		OaTemplateGetResponse response = new OaTemplateGetResponse();
		try {
			response.setValue(svc.detail(request));
		} catch (Exception e) {
			log.info(LogUtils.errorPattern(title), e);
			response.exceptionHandler(e);
		}
		log.info(LogUtils.responsePattern(title), JsonUtils.toJSONString(response));
		return response;
	}

	@ApiOperation(value = "获取模板列表")
	@GetMapping(value = "/lst")
	public OaTemplateLstResponse lst(@ApiParam(value = "模板状态：null-全部 0-已停用 1-已启用") //
	@RequestParam(value = "status", required = false) Integer request) {
		String title = "OaTemplateLst";
		log.info(LogUtils.requestPattern(title), request);
		OaTemplateLstResponse response = new OaTemplateLstResponse();
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
