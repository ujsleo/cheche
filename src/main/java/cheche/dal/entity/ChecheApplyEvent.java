package cheche.dal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cheche_apply_event")
public class ChecheApplyEvent {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 流程ID
     */
    @Column(name = "process_id")
    private Long processId;

    /**
     * 审批事件类型
     */
    private Integer type;

    /**
     * 处理人的域账号
     */
    private String user;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除N-未删除Y-已删除
     */
    @Column(name = "is_deleted")
    private String isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "gmt_created")
    private Date gmtCreated;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取流程ID
     *
     * @return process_id - 流程ID
     */
    public Long getProcessId() {
        return processId;
    }

    /**
     * 设置流程ID
     *
     * @param processId 流程ID
     */
    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    /**
     * 获取审批事件类型
     *
     * @return type - 审批事件类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置审批事件类型
     *
     * @param type 审批事件类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取处理人的域账号
     *
     * @return user - 处理人的域账号
     */
    public String getUser() {
        return user;
    }

    /**
     * 设置处理人的域账号
     *
     * @param user 处理人的域账号
     */
    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取是否删除N-未删除Y-已删除
     *
     * @return is_deleted - 是否删除N-未删除Y-已删除
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除N-未删除Y-已删除
     *
     * @param isDeleted 是否删除N-未删除Y-已删除
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * 获取创建时间
     *
     * @return gmt_created - 创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreated 创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 获取修改时间
     *
     * @return gmt_modified - 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     *
     * @param gmtModified 修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取修改人
     *
     * @return modifier - 修改人
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 设置修改人
     *
     * @param modifier 修改人
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}