package cheche.core.dto.apply;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 转办动作
 * 
 * @author jieli
 */
@ApiModel(value = "转办动作")
public class TransferOpt extends ApproveOpt {
    @ApiModelProperty(value = "代办人的域账号", required = true)
    @NotBlank(message = "agent can NOT be empty or null.")
    private String agent;

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
