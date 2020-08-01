package cheche.core.dto.template;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import common.util.tools.EncryptUtils;

/**
 * 模板信息
 * 
 * @author jieli
 *
 */
public class TemplateInfo {
	/** 模板CODE，模板的唯一标识符 UNIQUE */
	private String code;
	/** 模板状态：0-已停用 1-已启用 */
	private Integer status;
	/** 模板名称 */
	@NotNull(message = "name can NOT be empty or null.")
	protected String name;
	/** 模板图标 */
	@NotNull(message = "icon can NOT be empty or null.")
	protected String icon;
	/** 模板分组ID */
	@NotNull(message = "group_id can NOT be null.")
	protected Long groupId;
	/** 模板分组名称 */
	private String groupName;

	/** 默认模板CODE = cheche_ + md5(模板名称) */
	public String getCode() {
		return StringUtils.isEmpty(code) ? "cheche_" + EncryptUtils.md5Hex(name) : code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
