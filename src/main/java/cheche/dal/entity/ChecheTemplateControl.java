package cheche.dal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cheche_template_control")
public class ChecheTemplateControl {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 模板ID
     */
    @Column(name = "template_id")
    private Long templateId;

    /**
     * 控件类型
     */
    private String type;

    /**
     * 控件名
     */
    private String name;

    /**
     * 控件展示名
     */
    private String label;

    /**
     * 控件默认值
     */
    private String value;

    /**
     * 控件说明，向申请者展示控件的填写说明
     */
    private String placeholder;

    /**
     * 是否必填：1-必填 0-选填
     */
    private Integer require;

    /**
     * 控件配置JSON
     */
    private String config;

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
     * 获取控件类型
     *
     * @return type - 控件类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置控件类型
     *
     * @param type 控件类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取控件名
     *
     * @return name - 控件名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置控件名
     *
     * @param name 控件名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取控件展示名
     *
     * @return label - 控件展示名
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置控件展示名
     *
     * @param label 控件展示名
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    /**
     * 获取控件默认值
     *
     * @return value - 控件默认值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置控件默认值
     *
     * @param value 控件默认值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 获取控件说明，向申请者展示控件的填写说明
     *
     * @return placeholder - 控件说明，向申请者展示控件的填写说明
     */
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * 设置控件说明，向申请者展示控件的填写说明
     *
     * @param placeholder 控件说明，向申请者展示控件的填写说明
     */
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder == null ? null : placeholder.trim();
    }

    /**
     * 获取是否必填：1-必填 0-选填
     *
     * @return require - 是否必填：1-必填 0-选填
     */
    public Integer getRequire() {
        return require;
    }

    /**
     * 设置是否必填：1-必填 0-选填
     *
     * @param require 是否必填：1-必填 0-选填
     */
    public void setRequire(Integer require) {
        this.require = require;
    }

    /**
     * 获取控件配置JSON
     *
     * @return config - 控件配置JSON
     */
    public String getConfig() {
        return config;
    }

    /**
     * 设置控件配置JSON
     *
     * @param config 控件配置JSON
     */
    public void setConfig(String config) {
        this.config = config == null ? null : config.trim();
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