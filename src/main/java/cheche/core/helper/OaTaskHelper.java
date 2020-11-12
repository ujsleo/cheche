package cheche.core.helper;

import java.util.List;

import cheche.common.utils.ContextUtils;
import cheche.core.dto.OaVariables;
import cheche.core.dto.template.ApproverItem;
import cheche.core.spi.IOaTask;

/**
 * 审批节点小助手
 * 
 * @author jieli
 */
public class OaTaskHelper {
    /**
     * 回调业务系统发起
     * 
     * @param className 审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统
     * @param variables 流程变量
     */
    public static void start(String className, OaVariables variables) {
        IOaTask oaTask = (IOaTask) ContextUtils.getContext().getBean(className);
        oaTask.start(variables);
    }

    /**
     * 回调业务系统通过
     * 
     * @param className 审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统
     * @param variables 流程变量
     */
    public static void pass(String className, OaVariables variables) {
        IOaTask oaTask = (IOaTask) ContextUtils.getContext().getBean(className);
        oaTask.pass(variables);
    }

    /**
     * 回调各业务系统驳回或撤回
     * 
     * @param startedApprover 已发起的节点信息列表
     */
    public static void undo(List<ApproverItem> startedApprover) {
        int size = startedApprover.size();
        if (size > 1) {
            // 所有已通过的，撤回
            for (ApproverItem approver : startedApprover.subList(0, size - 1)) {
                IOaTask oaTask = (IOaTask) ContextUtils.getContext().getBean(approver.getClassName());
                oaTask.back();
            }
        }
        // 当前发起的，驳回
        IOaTask oaTask = (IOaTask) ContextUtils.getContext().getBean(startedApprover.get(size - 1).getClassName());
        oaTask.reject();
    }

    /**
     * @param className
     * @return 是否自动审批
     */
    public static boolean canAuto(String className) {
        IOaTask oaTask = (IOaTask) ContextUtils.getContext().getBean(className);
        return oaTask.canAuto();
    }
}
