package cheche.dal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cheche_apply_task_spot")
public class ChecheApplyTaskSpot {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 节点ID
     */
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 流程ID
     */
    @Column(name = "process_id")
    private Long processId;

    /**
     * 处理人的域账号
     */
    private String user;

    /**
     * （处理人转办）交办人的域账号
     */
    private String assigner;

    /**
     * 关注状态
     */
    private Integer status;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * （处理人转办）备注
     */
    private String remark;

    /**
     * 级次
     */
    private Integer step;

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
     * 获取节点ID
     *
     * @return task_id - 节点ID
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * 设置节点ID
     *
     * @param taskId 节点ID
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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
     * 获取（处理人转办）交办人的域账号
     *
     * @return assigner - （处理人转办）交办人的域账号
     */
    public String getAssigner() {
        return assigner;
    }

    /**
     * 设置（处理人转办）交办人的域账号
     *
     * @param assigner （处理人转办）交办人的域账号
     */
    public void setAssigner(String assigner) {
        this.assigner = assigner == null ? null : assigner.trim();
    }

    /**
     * 获取关注状态
     *
     * @return status - 关注状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置关注状态
     *
     * @param status 关注状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取（处理人转办）备注
     *
     * @return remark - （处理人转办）备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置（处理人转办）备注
     *
     * @param remark （处理人转办）备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取级次
     *
     * @return step - 级次
     */
    public Integer getStep() {
        return step;
    }

    /**
     * 设置级次
     *
     * @param step 级次
     */
    public void setStep(Integer step) {
        this.step = step;
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