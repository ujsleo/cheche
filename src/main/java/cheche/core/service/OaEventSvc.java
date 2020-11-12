package cheche.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cheche.core.dto.event.EventItem;
import cheche.dal.dao.ChecheApplyEventMapper;

/**
 * 审批事件服务
 * 
 * @author jieli
 *
 */
@Component
public class OaEventSvc {
	@Autowired
	private ChecheApplyEventMapper eventDao;

	/**
	 * 获取事件列表
	 * 
	 * @param processId 流程ID
	 * @return 事件列表
	 */
	public List<EventItem> lst(Long processId) {
		return eventDao.findLst(processId).stream().map(item -> {
			return new EventItem(item);
		}).collect(Collectors.toList());
	}
}
