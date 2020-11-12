package cheche.core.dto.template;

import org.hibernate.validator.constraints.NotBlank;

import cheche.core.dto.constant.ApplyTaskType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 审批节点信息
 * 
 * @author jieli
 */
@ApiModel(value = "审批节点信息")
public class ApproverItem extends Participant {
    @ApiModelProperty(value = "节点审批ID")
    private Long      id;
    @ApiModelProperty(value = "审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统", required = true)
    @NotBlank(message = "class_name can NOT be empty or null.")
    private String    className;
    /**
     * {@link ApplyTaskType}
     */
    @ApiModelProperty(value = "节点审批方式：0-或签 1-会签")
    private Integer   type = ApplyTaskType.OR;
    @ApiModelProperty(value = "级次")
    private Integer   step = 0;
    @ApiModelProperty(value = "额外的参数")
    private ExtraAttr extraAttr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public ExtraAttr getExtraAttr() {
        return extraAttr;
    }

    public void setExtraAttr(ExtraAttr extraAttr) {
        this.extraAttr = extraAttr;
    }
}
