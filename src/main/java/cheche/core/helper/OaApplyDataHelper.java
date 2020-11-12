package cheche.core.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cheche.common.utils.ContextUtils;
import cheche.core.dto.template.ControlItem;
import cheche.dal.dao.ChecheApplyControlMapper;

/**
 * 审批申请数据小助手
 * 
 * @author jieli
 */
public class OaApplyDataHelper {
    /**
     * 获取审批申请数据列表
     * 
     * @param processId 流程ID
     * @return
     */
    public static List<ControlItem> applyContents(Long processId) {
        return ContextUtils.getContext().getBean(ChecheApplyControlMapper.class).findLst(processId);
    }

    /**
     * 获取审批申请数据键值对
     * 
     * @param processId
     * @return 控件name-value键值对
     */
    public static Map<String, String> applyContentsMap(Long processId) {
        Map<String, String> ret = new HashMap<>();
        for (ControlItem one : applyContents(processId)) {
            ret.put(one.getName(), one.getValue());
        }
        return ret;
    }

    /**
     * 根据流程ID+控件ID唯一获取控件值
     * 
     * @param processId 流程ID
     * @param controlId 控件ID
     * @return
     */
    public static String controlValue(Long processId, Long controlId) {
        return ContextUtils.getContext().getBean(ChecheApplyControlMapper.class).findValue(processId, controlId);
    }

    /**
     * 根据流程ID+控件名唯一获取控件值
     * 
     * @param processId 流程ID
     * @param controlName 控件名
     * @return
     */
    public static String controlValue(Long processId, String controlName) {
        return ContextUtils.getContext().getBean(ChecheApplyControlMapper.class).findValueByName(processId,
                controlName);
    }
}
