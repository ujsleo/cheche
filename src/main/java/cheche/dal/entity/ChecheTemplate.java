package cheche.dal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cheche_template")
public class ChecheTemplate {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模板CODE，模板的唯一标识符
     */
    private String code;

    /**
     * 模板状态：0-已停用 1-已启用
     */
    private Integer status;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 模板图标
     */
    private String icon;

    /**
     * 模板分组ID
     */
    @Column(name = "group_id")
    private Long groupId;

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
     * 获取模板CODE，模板的唯一标识符
     *
     * @return code - 模板CODE，模板的唯一标识符
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置模板CODE，模板的唯一标识符
     *
     * @param code 模板CODE，模板的唯一标识符
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取模板状态：0-已停用 1-已启用
     *
     * @return status - 模板状态：0-已停用 1-已启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置模板状态：0-已停用 1-已启用
     *
     * @param status 模板状态：0-已停用 1-已启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取模板名称
     *
     * @return name - 模板名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置模板名称
     *
     * @param name 模板名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取模板图标
     *
     * @return icon - 模板图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置模板图标
     *
     * @param icon 模板图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取模板分组ID
     *
     * @return group_id - 模板分组ID
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 设置模板分组ID
     *
     * @param groupId 模板分组ID
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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