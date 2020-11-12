package cheche.core.dto.template;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 额外的参数
 * 
 * @author jieli
 */
@ApiModel(value = "额外的参数")
public class ExtraAttr {
    @ApiModelProperty(value = "Hook配置信息")
    private List<HookItem> hook;

    public List<HookItem> getHook() {
        return hook;
    }

    public void setHook(List<HookItem> hook) {
        this.hook = hook;
    }
}
