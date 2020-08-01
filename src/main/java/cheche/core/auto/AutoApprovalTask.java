package cheche.core.auto;

import cheche.core.dto.apply.ApproveOpt;
import cheche.core.dto.apply.RejectOpt;
import cheche.core.dto.auto.AutoApprovalRequest;
import cheche.core.service.OaTaskSvc;
import cheche.core.spi.IAutoOaTask;
import common.util.executor.AbstractExecutorTask;
import common.util.tools.context.ContextUtils;

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
			approveOpt.setUser("cheche");
			approveOpt.setRemark("系统自动审批通过");
			ContextUtils.getContext().getBean(OaTaskSvc.class).pass(approveOpt);
		} else {
			RejectOpt rejectOpt = new RejectOpt();
			rejectOpt.setTaskId(request.getTaskId());
			rejectOpt.setUser("cheche");
			rejectOpt.setRemark("系统自动驳回");
			ContextUtils.getContext().getBean(OaTaskSvc.class).reject(rejectOpt);
		}
		return null;
	}

	@Override
	protected Void recovery(AutoApprovalRequest request) {
		return null;
	}
}
