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
import cheche.controller.vo.OaSearchRequest;
import cheche.core.service.OaSearchSvc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 审批搜索Controller
 * 
 * @author jieli
 */
@Api(tags = "审批搜索")
@RestController
@RequestMapping(ApiConst.UA + "/search")
public class OaSearchController {
    private final static Logger log = LoggerFactory.getLogger(OaSearchController.class);
    @Autowired
    private OaSearchSvc         svc;

    @ApiOperation(value = "（申请人）搜索'我发起的'")
    @PostMapping(value = "/apply")
    public OaMyApplyResponse apply(@Valid @RequestBody OaSearchRequest request) {
        String title = "OaSearchApply";
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

    @ApiOperation(value = "（处理人）搜索'我审批的'")
    @PostMapping(value = "/approval")
    public OaMyApprovalResponse approval(@Valid @RequestBody OaSearchRequest request) {
        String title = "OaSearchApproval";
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
