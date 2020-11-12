package cheche.core.dto.template;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import cheche.common.utils.JsonUtils;
import cheche.core.dto.template.control.ControlConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模板控件信息
 * 
 * @author jieli
 */
@ApiModel(value = "模板控件信息")
public class ControlItem {
    @ApiModelProperty(value = "控件ID，控件的唯一标识符。用于定位数据")
    private Long          id;
    @ApiModelProperty(value = "控件类型：text-文本 textarea-多行文本 select-单选 multi-多选 file-附件 image-图像 tips-说明文字 date-日期", required = true)
    @NotBlank(message = "type can NOT be empty or null.")
    private String        type;
    @ApiModelProperty(value = "控件名", required = true)
    @NotBlank(message = "name can NOT be empty or null.")
    private String        name;
    @ApiModelProperty(value = "控件展示名", required = true)
    @NotBlank(message = "label can NOT be empty or null.")
    private String        label;
    @ApiModelProperty(value = "控件值/默认值")
    private String        value;
    @ApiModelProperty(value = "控件说明，向申请者展示控件的填写说明")
    private String        placeholder;
    @ApiModelProperty(value = "是否必填：1-必填 0-选填")
    private Integer       require = 0;
    @ApiModelProperty(value = "控件配置")
    private ControlConfig config;
    /** 控件配置JSON */
    private String        configJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Integer getRequire() {
        return require;
    }

    public void setRequire(Integer require) {
        this.require = require;
    }

    public ControlConfig getConfig() {
        return config;
    }

    public void setConfig(ControlConfig config) {
        this.config = config;
    }

    public String getConfigJson() {
        return configJson;
    }

    public void setConfigJson(String configJson) {
        if (StringUtils.isNotEmpty(configJson))
            config = JsonUtils.parseObject(configJson, ControlConfig.class);
    }
}
