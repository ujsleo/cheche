package cheche.core.dto.template;

import javax.validation.constraints.NotNull;

/**
 * 模板控件
 * 
 * @author jieli
 *
 */
public class ControlItem {
	/** 控件ID，控件的唯一标识符。用于定位数据 */
	private Long id;
	/**
	 * 控件类型：
	 * 
	 * text-文本 textarea-多行文本 select-单选 multi-多选 file-附件 image-图像 tips-说明文字 date-日期
	 */
	@NotNull(message = "type can NOT be empty or null.")
	private String type;
	/** 控件名 */
	@NotNull(message = "name can NOT be empty or null.")
	private String name;
	/** 控件展示名 */
	@NotNull(message = "label can NOT be empty or null.")
	private String label;
	/** 控件值/默认值 */
	private String value;
	/** 控件说明，向申请者展示控件的填写说明 */
	private String placeholder;
	/** 是否必填：1-必填 0-选填 */
	private Integer require = 0;
	/** 控件配置 */
	private ControlConfig config;

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
}
