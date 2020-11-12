package cheche.core.builder.meta;

import java.util.ArrayList;
import java.util.List;

import cheche.common.utils.CheckUtils;
import cheche.core.builder.TemplateBuilder;
import cheche.core.dto.template.ControlItem;
import cheche.core.dto.template.control.ControlConfig;

/**
 * 模板流程元信息
 * 
 * @author jieli
 *
 */
public class ApplyContentsMeta {
	/** 持有上下文 */
	private TemplateBuilder ctx;
	private List<ControlItem> lst = new ArrayList<>();
	private ControlItem current;

	public ApplyContentsMeta(TemplateBuilder ctx) {
		this.ctx = ctx;
	}

	/** 控制权交回上下文 */
	public TemplateBuilder and() {
		return ctx;
	}

	/** 添加节点 */
	public ApplyContentsMeta add() {
		current = new ControlItem();
		lst.add(current);
		return this;
	}

	/**
	 * 控件类型：
	 * 
	 * text-文本 textarea-多行文本 select-单选 multi-多选 file-附件 image-图像 tips-说明文字 date-日期
	 */
	public ApplyContentsMeta type(String type) {
		CheckUtils.checkNotNull(type, "type can NOT be empty or null");
		current.setType(type);
		return this;
	}

	/** 控件名 */
	public ApplyContentsMeta name(String name) {
		CheckUtils.checkNotNull(name, "name can NOT be empty or null");
		current.setName(name);
		return this;
	}

	/** 控件展示名 */
	public ApplyContentsMeta label(String label) {
		CheckUtils.checkNotNull(label, "label can NOT be empty or null");
		current.setLabel(label);
		return this;
	}

	/** 控件说明，向申请者展示控件的填写说明 */
	public ApplyContentsMeta placeholder(String placeholder) {
		CheckUtils.checkNotNull(placeholder, "name can NOT be empty or null");
		current.setPlaceholder(placeholder);
		return this;
	}

	/** 是否必填：1-必填 0-选填 */
	public ApplyContentsMeta require(Integer require) {
		CheckUtils.checkNotNull(require, "require can NOT be null");
		current.setRequire(require);
		return this;
	}

	/** 控件配置 */
	public ApplyContentsMeta config(ControlConfig config) {
		CheckUtils.checkNotNull(config, "config can NOT be null");
		current.setConfig(config);
		return this;
	}

	public List<ControlItem> getLst() {
		return lst;
	}
}
