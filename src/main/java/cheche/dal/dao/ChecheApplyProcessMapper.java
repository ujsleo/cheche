package cheche.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cheche.core.dto.approval.MyApplySummary;
import cheche.core.dto.constant.ApplyProcessStatus;
import cheche.dal.entity.ChecheApplyProcess;
import tk.mybatis.mapper.common.Mapper;

/**
 * 审批流程DAO
 * 
 * @author jieli
 */
public interface ChecheApplyProcessMapper extends Mapper<ChecheApplyProcess> {
    /**
     * 通过CODE获取流程详情
     * 
     * @param code 流程CODE
     * @return 流程详情
     */
    ChecheApplyProcess findByCode(String code);

    /**
     * （申请人）获取'我发起的'
     * 
     * @param user 申请人的域账号
     * @param status 流程状态 {@link ApplyProcessStatus}
     * @param templateCode 模板CODE
     * @param offset 偏移量
     * @param rows 行数
     * @return
     */
    List<MyApplySummary> findMyApply(@Param("user") String user, @Param("status") Integer status,
                                     @Param("templateCode") String templateCode, @Param("offset") Integer offset,
                                     @Param("rows") Integer rows);

    /**
     * （申请人）统计'我发起的'
     * 
     * @param user 申请人的域账号
     * @param status 流程状态 {@link ApplyProcessStatus}
     * @param templateCode 模板CODE
     * @return
     */
    Integer cntMyApply(@Param("user") String user, @Param("status") Integer status,
                       @Param("templateCode") String templateCode);

    /**
     * （申请人）搜索'我发起的'
     * 
     * @param user 申请人的域账号
     * @param wd 关键词（审批单号、发起人、标题）
     * @param offset 偏移量
     * @param rows 行数
     * @return
     */
    List<MyApplySummary> searchMyApply(@Param("user") String user, @Param("wd") String wd,
                                       @Param("offset") Integer offset, @Param("rows") Integer rows);

    /**
     * （申请人）统计搜索'我发起的'
     * 
     * @param user 申请人的域账号
     * @param wd 关键词（审批单号、发起人、标题）
     * @return
     */
    Integer cntSearchMyApply(@Param("user") String user, @Param("wd") String wd);

    /** 成功结束 */
    void completed(@Param("id") Long id, @Param("user") String user);

    /** 被驳回而终止 */
    void terminated(@Param("id") Long id, @Param("step") Integer step, @Param("user") String user);

    /** 撤回 */
    void withdraw(@Param("id") Long id, @Param("user") String user);

    /** 更新级次 */
    void updateStep(@Param("id") Long id, @Param("step") Integer step, @Param("user") String user);
}
