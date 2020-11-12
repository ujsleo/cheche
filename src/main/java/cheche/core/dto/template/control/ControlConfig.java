package cheche.core.dto.template.control;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 控件配置信息
 * 
 * @author jieli
 */
@ApiModel(value = "控件配置信息")
public class ControlConfig {
    @ApiModelProperty(value = "选择类型：single-单选；multi-多选")
    private String             type;
    @ApiModelProperty(value = "单选/多选控件中的所有选项")
    private List<SelectOption> options;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SelectOption> getOptions() {
        return options;
    }

    public void setOptions(List<SelectOption> options) {
        this.options = options;
    }
}
