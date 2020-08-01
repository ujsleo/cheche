package cheche.core.helper;

import cheche.core.dto.constant.ApplyEventType;
import cheche.dal.dao.ChecheApplyEventMapper;
import cheche.dal.entity.ChecheApplyEvent;
import common.util.tools.CheckUtils;
import common.util.tools.context.ContextUtils;

/**
 * 审批事件小助手
 * 
 * @author jieli
 *
 */
public class OaEventHelper {
	/**
	 * 添加审批事件
	 * 
	 * @param processId 审批流程ID
	 * @param type      审批事件类型
	 * @param user      处理人的域账号
	 */
	public static void addEvent(Long processId, ApplyEventType type, String user) {
		addEvent(processId, type, user, type.getDesc());
	}

	/**
	 * 添加审批事件
	 * 
	 * @param processId 审批流程ID
	 * @param type      审批事件类型
	 * @param user      处理人的域账号
	 * @param remark    备注
	 */
	public static void addEvent(Long processId, ApplyEventType type, String user, String remark) {
		CheckUtils.checkNotNull(processId, "process_id can NOT be null.");
		ChecheApplyEvent sample = new ChecheApplyEvent();
		sample.setProcessId(processId);
		sample.setType(type.getValue());
		sample.setUser(user);
		sample.setRemark(remark == null ? type.getDesc() : remark);
		ContextUtils.getContext().getBean(ChecheApplyEventMapper.class).insertSelective(sample);
	}
}
