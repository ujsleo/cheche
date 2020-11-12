package cheche.core.msg;

import cheche.common.executor.AbstractExecutorTask;
import cheche.core.dto.msg.SendMsgRequest;
import cheche.core.dto.msg.SendMsgResponse;

/**
 * 发送消息的子任务（多实例，框架配置超时/重试逻辑）
 * 
 * @author jieli
 *
 */
public class SendMsgTask extends AbstractExecutorTask<SendMsgRequest, SendMsgResponse> {
	// TODO 消息发送的FeignClientBean = ContextUtils.getContext().getBean();

	@Override
	protected void init() {
		name = "SendMsgTask";
		// 重试逻辑（重试3次，固定间隔30秒）
//		retryCount = 3;
//		retryDelaySeconds = 30;
//		retryLogic = RETRY_FIXED;
		// 延迟逻辑（延迟3秒执行）
		delaySeconds = 3;
	}

	@Override
	protected SendMsgResponse doExecute(SendMsgRequest request) {
		SendMsgResponse ret = new SendMsgResponse();
		// TODO FeignClient发送消息
		// 1) 企业微信消息推送
		// 2) 邮件
		// 3) 短信
		// 4) 站内信
		return ret;
	}

	@Override
	protected SendMsgResponse recovery(SendMsgRequest request) {
		SendMsgResponse ret = new SendMsgResponse();
		// TODO 重试次数耗尽之后，依旧失败的降级处理
		return ret;
	}
}
