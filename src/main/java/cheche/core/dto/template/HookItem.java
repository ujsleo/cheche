package cheche.core.dto.template;

import cheche.core.dto.constant.HookCallbackType;
import cheche.dal.entity.ChecheTemplateHook;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Hook配置信息
 * 
 * @author jieli
 */
@ApiModel(value = "Hook配置信息")
public class HookItem {
    /**
     * {@link HookCallbackType}
     */
    @ApiModelProperty(value = "Hook回调类型枚举（1-发起start 2-通过pass 4-驳回reject 8-撤回back）")
    private Integer type;
    @ApiModelProperty(value = "自定义的Hook地址，支持路径占位符绑定模板变量")
    private String  url;
    @ApiModelProperty(value = "支持GET、POST、DELETE、PUT等请求方法")
    private String  method;
    @ApiModelProperty(value = "自定义请求体(JSON)。支持接口参数绑定模板变量")
    private String  bodyData;
    @ApiModelProperty(value = "自定义请求头(JSON)。默认请求头为Content-Type: application/json;charset=UTF-8")
    private String  header;

    public HookItem() {
    }

    public HookItem(ChecheTemplateHook pojo) {
        type = pojo.getType();
        url = pojo.getUrl();
        method = pojo.getMethod();
        bodyData = pojo.getBodyData();
        header = pojo.getHeader();
    }

    /**
     * 转Hook配置POJO
     * 
     * @param templateId 模板ID
     * @param approverId 审批节点ID
     * @return
     */
    public ChecheTemplateHook toTemplateHookSample(Long templateId, Long approverId) {
        ChecheTemplateHook ret = new ChecheTemplateHook();
        ret.setTemplateId(templateId);
        ret.setApproverId(approverId);
        ret.setType(type);
        ret.setUrl(url);
        ret.setMethod(method);
        ret.setBodyData(bodyData);
        ret.setHeader(header);
        return ret;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBodyData() {
        return bodyData;
    }

    public void setBodyData(String bodyData) {
        this.bodyData = bodyData;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
