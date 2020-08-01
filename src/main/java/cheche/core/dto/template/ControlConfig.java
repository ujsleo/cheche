package cheche.core.dto.template;

import java.util.List;

/**
 * 控件配置
 * 
 * @author jieli
 *
 */
public class ControlConfig {
	/** 选择类型：single-单选；multi-多选 */
	private String type;
	/** 单选/多选控件中的所有选项 */
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
