package cheche.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cheche.core.dto.approval.MyApprovalSummary;
import cheche.core.dto.constant.ApplyTaskSpotStatus;
import cheche.dal.entity.ChecheApplyTaskSpot;
import tk.mybatis.mapper.common.Mapper;

/**
 * 审批节点的关注DAO
 * 
 * @author jieli
 */
public interface ChecheApplyTaskSpotMapper extends Mapper<ChecheApplyTaskSpot> {
    /**
     * 通过审批节点ID获取关注列表
     * 
     * @param taskId 审批节点ID
     * @return 关注列表
     */
    List<ChecheApplyTaskSpot> findAll(Long taskId);

    /**
     * （处理人）获取'我审批的'
     * 
     * @param user 处理人的域账号
     * @param status 节点的关注状态 {@link ApplyTaskSpotStatus}
     * @param templateCode 模板CODE
     * @param offset 偏移量
     * @param rows 行数
     * @return
     */
    List<MyApprovalSummary> findMyApproval(@Param("user") String user, @Param("status") Integer status,
                                           @Param("templateCode") String templateCode, @Param("offset") Integer offset,
                                           @Param("rows") Integer rows);

    /**
     * （处理人）统计'我审批的'
     * 
     * @param user 处理人的域账号
     * @param status 节点的关注状态 {@link ApplyTaskSpotStatus}
     * @param templateCode 模板CODE
     * @return
     */
    Integer cntMyApproval(@Param("user") String user, @Param("status") Integer status,
                          @Param("templateCode") String templateCode);

    /**
     * （处理人）搜索'我审批的'
     * 
     * @param user 处理人的域账号
     * @param wd 关键词（审批单号、发起人、标题）
     * @param offset 偏移量
     * @param rows 行数
     * @return
     */
    List<MyApprovalSummary> searchMyApproval(@Param("user") String user, @Param("wd") String wd,
                                             @Param("offset") Integer offset, @Param("rows") Integer rows);

    /**
     * （处理人）统计搜索'我审批的'
     * 
     * @param user 处理人的域账号
     * @param wd 关键词（审批单号、发起人、标题）
     * @return
     */
    Integer cntSearchMyApproval(@Param("user") String user, @Param("wd") String wd);

    /** 通过 */
    void pass(@Param("taskId") Long taskId, @Param("user") String user, @Param("remark") String remark);

    /**
     * 驳回
     * 
     * @param processId 流程ID
     * @param fromStep 起级次
     * @param toStep 止级次
     */
    void reject(@Param("processId") Long processId, @Param("fromStep") Integer fromStep,
                @Param("toStep") Integer toStep);

    /** 撤回 */
    void withdraw(Long processId);

    /**
     * 转办
     * 
     * @param taskId 审批节点ID
     * @param user 交办人
     * @param agent 转办人
     */
    void transfer(@Param("taskId") Long taskId, @Param("user") String user, @Param("agent") String agent);

    /**
     * 批量插入
     * 
     * @param lst Sample列表
     * @return
     */
    void batchInsert(List<ChecheApplyTaskSpot> lst);
}
