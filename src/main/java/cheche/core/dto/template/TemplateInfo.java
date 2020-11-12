package cheche.core.dto.template;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import cheche.common.utils.EncryptUtils;
import cheche.core.dto.constant.TemplateStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模板信息
 * 
 * @author jieli
 */
@ApiModel(value = "模板信息")
public class TemplateInfo {
    @ApiModelProperty(value = "模板CODE，模板的唯一标识符 UNIQUE")
    private String   code;
    /**
     * {@link TemplateStatus}
     */
    @ApiModelProperty(value = "模板状态：0-已停用 1-已启用")
    private Integer  status;
    @ApiModelProperty(value = "模板名称", required = true)
    @NotBlank(message = "name can NOT be empty or null.")
    protected String name;
    @ApiModelProperty(value = "模板图标", required = true)
    @NotBlank(message = "icon can NOT be empty or null.")
    protected String icon;
    @ApiModelProperty(value = "模板分组ID：默认1-其他", required = true)
    @NotNull(message = "group_id can NOT be null.")
    protected Long   groupId = 1L;
    @ApiModelProperty(value = "模板分组名称")
    private String   groupName;

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
