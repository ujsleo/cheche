package cheche.dal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cheche_template_approver")
public class ChecheTemplateApprover {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模板ID
     */
    @Column(name = "template_id")
    private Long templateId;

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
     * 审批角色ID：0-抄送人 1-审批人/处理人 16-管理员
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 域账号列表，以逗号,分隔
     */
    private String user;

    /**
     * 角色列表，以逗号,分隔
     */
    private String role;

    /**
     * 管理员域账号列表，以逗号,分隔
     */
    private String admin;

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
     * 获取模板ID
     *
     * @return template_id - 模板ID
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * 设置模板ID
     *
     * @param templateId 模板ID
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
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
     * 获取审批角色ID：0-抄送人 1-审批人/处理人 16-管理员
     *
     * @return role_id - 审批角色ID：0-抄送人 1-审批人/处理人 16-管理员
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置审批角色ID：0-抄送人 1-审批人/处理人 16-管理员
     *
     * @param roleId 审批角色ID：0-抄送人 1-审批人/处理人 16-管理员
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取域账号列表，以逗号,分隔
     *
     * @return user - 域账号列表，以逗号,分隔
     */
    public String getUser() {
        return user;
    }

    /**
     * 设置域账号列表，以逗号,分隔
     *
     * @param user 域账号列表，以逗号,分隔
     */
    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    /**
     * 获取角色列表，以逗号,分隔
     *
     * @return role - 角色列表，以逗号,分隔
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置角色列表，以逗号,分隔
     *
     * @param role 角色列表，以逗号,分隔
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * 获取管理员域账号列表，以逗号,分隔
     *
     * @return admin - 管理员域账号列表，以逗号,分隔
     */
    public String getAdmin() {
        return admin;
    }

    /**
     * 设置管理员域账号列表，以逗号,分隔
     *
     * @param admin 管理员域账号列表，以逗号,分隔
     */
    public void setAdmin(String admin) {
        this.admin = admin == null ? null : admin.trim();
    }

    public Integer getStep() {
        return step;
    }

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