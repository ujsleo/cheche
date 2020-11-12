package cheche.core.dto.event;

import java.util.Date;

import cheche.core.dto.constant.ApplyEventType;
import cheche.dal.entity.ChecheApplyEvent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 审批事件
 * 
 * @author jieli
 */
@ApiModel(value = "审批事件")
public class EventItem {
    /**
     * {@link ApplyEventType}
     */
    @ApiModelProperty(value = "事件类型")
    private Integer type;
    @ApiModelProperty(value = "处理人的域账号")
    private String  user;
    @ApiModelProperty(value = "备注/处理意见")
    private String  remark;
    @ApiModelProperty(value = "操作时间")
    private Date    opDate;

    public EventItem() {
    }

    public EventItem(ChecheApplyEvent pojo) {
        type = pojo.getType();
        user = pojo.getUser();
        remark = pojo.getRemark();
        opDate = pojo.getGmtCreated();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }
}
