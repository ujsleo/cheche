package cheche.core.dto.template.control;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 单选/多选控件中的选项
 * 
 * @author jieli
 */
@ApiModel(value = "单选/多选控件中的选项")
public class SelectOption {
    @ApiModelProperty(value = "选项key，选项的唯一ID。可用于发起审批时的控件赋值")
    private String key;
    @ApiModelProperty(value = "选项值")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
