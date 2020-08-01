package cheche.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cheche.controller.vo.OaTemplateGetResponse;
import cheche.controller.vo.OaTemplateLstResponse;
import cheche.core.service.OaTemplateSvc;
import common.util.tools.JsonUtils;
import common.util.tools.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 业务审批模板Controller
 * 
 * @author jieli
 *
 */
@Api(tags = "业务审批模板")
@RestController
@RequestMapping("/oa/template")
public class OaTemplateController {
	private final static Logger log = LoggerFactory.getLogger(OaTemplateController.class);
	@Autowired
	private OaTemplateSvc templateSvc;

	/** 获取模板详情 */
	@ApiOperation(value = "获取模板详情")
	@GetMapping(value = "/get")
	public OaTemplateGetResponse get(@RequestParam(value = "templateCode") String request) {
		String title = "OaTemplateGet";
		log.info(LogUtils.requestPattern(), title, request);
		OaTemplateGetResponse response = new OaTemplateGetResponse();
		try {
			response.setValue(templateSvc.getTemplateContent(request));
		} catch (Exception e) {
			log.info(LogUtils.errorPattern(title), e);
			response.exceptionHandler(e);
		}
		log.info(LogUtils.responsePattern(), title, JsonUtils.convert2Json(response));
		return response;
	}

	/**
	 * 获取模板列表
	 * 
	 * @param request 模板状态：null-全部 0-已停用 1-已启用
	 * @return
	 */
	@ApiOperation(value = "获取模板列表")
	@GetMapping(value = "/lst")
	public OaTemplateLstResponse lst(@RequestParam(value = "status", required = false) Integer request) {
		String title = "OaTemplateLst";
		log.info(LogUtils.requestPattern(), title, request);
		OaTemplateLstResponse response = new OaTemplateLstResponse();
		try {
			response.setValue(templateSvc.getTemplateLst(request));
		} catch (Exception e) {
			log.info(LogUtils.errorPattern(title), e);
			response.exceptionHandler(e);
		}
		log.info(LogUtils.responsePattern(), title, JsonUtils.convert2Json(response));
		return response;
	}
}
