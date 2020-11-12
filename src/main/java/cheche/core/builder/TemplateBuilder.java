package cheche.core.builder;

import cheche.common.utils.CheckUtils;
import cheche.common.utils.JsonUtils;
import cheche.core.builder.meta.ApplyContentsMeta;
import cheche.core.builder.meta.ApproversMeta;
import cheche.core.dto.template.TemplateContent;

/**
 * 模板详情链式生成器
 * 
 * @author jieli
 *
 */
public class TemplateBuilder {
	/** 模板详情 */
	private TemplateContent template = new TemplateContent();

	/** 模板CODE。非必填，可由系统自动生成 */
	public TemplateBuilder code(String code) {
		template.setCode(code);
		return this;
	}

	/** 模板名称 */
	public TemplateBuilder name(String name) {
		CheckUtils.checkNotNull(name, "name can NOT be empty or null");
		template.setName(name);
		return this;
	}

	/** 模板图标 */
	public TemplateBuilder icon(String icon) {
		CheckUtils.checkNotNull(icon, "icon can NOT be empty or null");
		template.setIcon(icon);
		return this;
	}

	/** 模板分组ID */
	public TemplateBuilder groupId(Long groupId) {
		CheckUtils.checkNotNull(groupId, "groupId can NOT be null");
		template.setGroupId(groupId);
		return this;
	}

	/** 审批流程信息，数组。用于规则设置 */
	public ApproversMeta approvers() {
		ApproversMeta ret = new ApproversMeta(this);
		template.setApprover(ret.getLst());
		return ret;
	}

	/** 模板控件信息，数组。用于模板设置，构成审批表单 */
	public ApplyContentsMeta applyContents() {
		ApplyContentsMeta ret = new ApplyContentsMeta(this);
		template.setApplyContents(ret.getLst());
		return ret;
	}

	/** 生成模板 */
	public TemplateContent build() {
		return template;
	}

	public static void main(String[] args) {
		TemplateContent template = new TemplateBuilder() //
				.code("cheche_demo").name("demo审批流").icon("模板图标URL").groupId(1L) //
				.approvers() // 审批流程
				.add().className("CommonOaTask").user("jieli").admin("cheche") //
				.and() //
				.applyContents() // 模板控件
				.add().type("select").name("select_1").label("问题分类").placeholder("请选择").require(1) //
				.and() //
				.build();

		System.out.println(JsonUtils.toJSONString(template));
	}
}
