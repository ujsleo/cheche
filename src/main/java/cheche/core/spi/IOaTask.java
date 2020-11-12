package cheche.core.spi;

import cheche.core.dto.OaVariables;

/**
 * IOaTask接口：处理当审批单的状态发生变化时与业务系统的回调通知
 * 
 * @author jieli
 */
public interface IOaTask {
    /**
     * 是否自动审批
     * 
     * @return true-自动审批 false-人工审批
     */
    boolean canAuto();

    /** 发起审批：申请冻结相关系统的业务数据（或状态置ING中间状态） */
    void start(OaVariables variables);

    /** 通过：业务数据从冻结到提交（或状态置已处理状态） */
    void pass(OaVariables variables);

    /** 驳回 */
    void reject();

    /** 撤回：取消冻结 */
    void back();
}
