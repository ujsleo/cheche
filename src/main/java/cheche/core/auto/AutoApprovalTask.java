package cheche.core.auto;

import cheche.common.executor.AbstractExecutorTask;
import cheche.common.utils.ContextUtils;
import cheche.core.dto.apply.ApproveOpt;
import cheche.core.dto.apply.RejectOpt;
import cheche.core.dto.auto.AutoApprovalRequest;
import cheche.core.dto.constant.ChecheConst;
import cheche.core.service.OaTaskSvc;
import cheche.core.spi.IAutoOaTask;

/**
 * 自动审批任务
 * 
 * @author jieli
 *
 */
public class AutoApprovalTask extends AbstractExecutorTask<AutoApprovalRequest, Void> {
	@Override
	protected void init() {
		name = "AutoApprovalTask";
		// 延迟逻辑（延迟3秒执行）
		delaySeconds = 3;
	}

	@Override
	protected Void doExecute(AutoApprovalRequest request) {
		IAutoOaTask oaTask = (IAutoOaTask) ContextUtils.getContext().getBean(request.getClassName());
		if (oaTask.canAutoPass()) {
			ApproveOpt approveOpt = new ApproveOpt();
			approveOpt.setTaskId(request.getTaskId());
			approveOpt.setUser(ChecheConst.SYS_APPROVAL);
			approveOpt.setRemark("系统自动审批通过");
			ContextUtils.getContext().getBean(OaTaskSvc.class).pass(approveOpt);
		} else {
			RejectOpt rejectOpt = new RejectOpt();
			rejectOpt.setTaskId(request.getTaskId());
			rejectOpt.setUser(ChecheConst.SYS_APPROVAL);
			rejectOpt.setRemark("系统自动驳回");
			rejectOpt.setStep(0); // 默认驳回到开始节点
			ContextUtils.getContext().getBean(OaTaskSvc.class).reject(rejectOpt);
		}
		return null;
	}

	@Override
	protected Void recovery(AutoApprovalRequest request) {
		return null;
	}
}
