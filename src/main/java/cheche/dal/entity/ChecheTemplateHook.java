package cheche.dal.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cheche_template_hook")
public class ChecheTemplateHook {
    /**
     * 主键
     */
    @Id
    private Long    id;

    /**
     * 审批规则ID
     */
    @Column(name = "approver_id")
    private Long    approverId;

    /**
     * 模板ID
     */
    @Column(name = "template_id")
    private Long    templateId;

    /**
     * 回调类型。1-发起start 2-通过pass 4-驳回reject 8-撤回back
     */
    private Integer type;

    /**
     * 请求地址
     */
    private String  url;

    /**
     * 请求方法POST GET
     */
    private String  method;

    /**
     * 请求体JSON(支持使用模版变量)
     */
    private String  bodyData;

    /**
     * 请求头
     */
    private String  header;

    /**
     * 是否删除N-未删除Y-已删除
     */
    private String  isDeleted;

    /**
     * 创建时间
     */
    private Date    gmtCreated;

    /**
     * 创建人
     */
    private String  creator;

    /**
     * 修改时间
     */
    private Date    gmtModified;

    /**
     * 修改人
     */
    private String  modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBodyData() {
        return bodyData;
    }

    public void setBodyData(String bodyData) {
        this.bodyData = bodyData;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}
