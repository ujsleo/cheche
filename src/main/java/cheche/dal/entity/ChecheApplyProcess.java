package cheche.dal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cheche_apply_process")
public class ChecheApplyProcess {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 流程CODE
     */
    private String code;

    /**
     * 审批单号
     */
    private String sn;

    /**
     * 审批状态
     */
    private Integer status;

    /**
     * 级次
     */
    private Integer step;

    /**
     * 模板CODE
     */
    @Column(name = "template_code")
    private String templateCode;

    /**
     * 业务CODE
     */
    @Column(name = "biz_code")
    private String bizCode;

    /**
     * 申请人的域账号
     */
    private String user;

    /**
     * 申请单标题
     */
    private String title;

    /**
     * 开始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

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
     * 获取流程CODE
     *
     * @return code - 流程CODE
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置流程CODE
     *
     * @param code 流程CODE
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取审批单号
     *
     * @return sn - 审批单号
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置审批单号
     *
     * @param sn 审批单号
     */
    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    /**
     * 获取审批状态
     *
     * @return status - 审批状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置审批状态
     *
     * @param status 审批状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取模板CODE
     *
     * @return template_code - 模板CODE
     */
    public String getTemplateCode() {
        return templateCode;
    }

    /**
     * 设置模板CODE
     *
     * @param templateCode 模板CODE
     */
    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode == null ? null : templateCode.trim();
    }

    /**
     * 获取业务CODE
     *
     * @return biz_code - 业务CODE
     */
    public String getBizCode() {
        return bizCode;
    }

    /**
     * 设置业务CODE
     *
     * @param bizCode 业务CODE
     */
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode == null ? null : bizCode.trim();
    }

    /**
     * 获取申请人的域账号
     *
     * @return user - 申请人的域账号
     */
    public String getUser() {
        return user;
    }

    /**
     * 设置申请人的域账号
     *
     * @param user 申请人的域账号
     */
    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    /**
     * 获取申请单标题
     *
     * @return title - 申请单标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置申请单标题
     *
     * @param title 申请单标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取开始时间
     *
     * @return start_date - 开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置开始时间
     *
     * @param startDate 开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取结束时间
     *
     * @return end_date - 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束时间
     *
     * @param endDate 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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