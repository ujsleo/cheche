package cheche.dal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cheche_apply_task")
public class ChecheApplyTask {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 流程ID
     */
    @Column(name = "process_id")
    private Long processId;

    /**
     * 节点状态
     */
    private Integer status;

    /**
     * 审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统
     */
    @Column(name = "class_name")
    private String className;

    /**
     * 节点审批方式：0-或签 1-会签
     */
    private Integer type;

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
     * 获取节点状态
     *
     * @return status - 节点状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置节点状态
     *
     * @param status 节点状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统
     *
     * @return class_name - 审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统
     *
     * @param className 审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统
     */
    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    /**
     * 获取节点审批方式：0-或签 1-会签
     *
     * @return type - 节点审批方式：0-或签 1-会签
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置节点审批方式：0-或签 1-会签
     *
     * @param type 节点审批方式：0-或签 1-会签
     */
    public void setType(Integer type) {
        this.type = type;
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