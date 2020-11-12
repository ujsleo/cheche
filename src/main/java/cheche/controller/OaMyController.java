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
import cheche.controller.constant.ApiConst;
import cheche.controller.vo.OaMyApplyResponse;
import cheche.controller.vo.OaMyApprovalResponse;
import cheche.controller.vo.OaMyRequest;
import cheche.core.service.OaMySvc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 我的审批Controller
 * 
 * @author jieli
 */
@Api(tags = "我的审批")
@RestController
@RequestMapping(ApiConst.UA + "/my")
public class OaMyController {
    private final static Logger log = LoggerFactory.getLogger(OaMyController.class);
    @Autowired
    private OaMySvc             svc;

    @ApiOperation(value = "（申请人）获取'我发起的'")
    @PostMapping(value = "/apply")
    public OaMyApplyResponse apply(@Valid @RequestBody OaMyRequest request) {
        String title = "OaMyApply";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        OaMyApplyResponse response = new OaMyApplyResponse();
        try {
            response.setValue(svc.apply(request));
            response.setTotal(svc.totalApply(request));
        } catch (Exception e) {
            log.info(LogUtils.errorPattern(title), e);
            response.exceptionHandler(e);
        }
        log.info(LogUtils.responsePattern(title), JsonUtils.toJSONString(response));
        return response;
    }

    @ApiOperation(value = "（处理人）获取'我审批的'")
    @PostMapping(value = "/approval")
    public OaMyApprovalResponse approval(@Valid @RequestBody OaMyRequest request) {
        String title = "OaMyApproval";
        log.info(LogUtils.requestPattern(title), JsonUtils.toJSONString(request));
        OaMyApprovalResponse response = new OaMyApprovalResponse();
        try {
            response.setValue(svc.approval(request));
            response.setTotal(svc.totalApproval(request));
        } catch (Exception e) {
            log.info(LogUtils.errorPattern(title), e);
            response.exceptionHandler(e);
        }
        log.info(LogUtils.responsePattern(title), JsonUtils.toJSONString(response));
        return response;
    }
}
