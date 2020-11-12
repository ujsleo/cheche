package cheche.core.spi;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Component;

import cheche.common.utils.ContextUtils;
import cheche.common.utils.FreeMarkerUtils;
import cheche.core.dto.OaVariables;
import cheche.core.dto.constant.HookCallbackType;
import cheche.core.dto.hook.HookRequest;
import cheche.core.dto.hook.HookResponse;
import cheche.core.exception.ChecheException;
import cheche.core.exception.ChecheExceptionEnum;
import cheche.core.helper.OaApplyDataHelper;
import cheche.core.hook.HookTask;
import cheche.dal.dao.ChecheTemplateHookMapper;
import cheche.dal.entity.ChecheTemplateHook;

/**
 * 普通Hook事件回调类节点
 * 
 * @author jieli
 */
@Component("CommonHookOaTask")
public class CommonHookOaTask implements IOaTask {
    @Override
    public boolean canAuto() {
        return false;
    }

    @Override
    public void start(OaVariables variables) {
        execute(variables, HookCallbackType.START);
    }

    @Override
    public void pass(OaVariables variables) {
        execute(variables, HookCallbackType.PASS);
    }

    @Override
    public void reject() {
        throw new ChecheException(ChecheExceptionEnum.E501, "NOT supported.");
    }

    @Override
    public void back() {
        throw new ChecheException(ChecheExceptionEnum.E501, "NOT supported.");
    }

    private void execute(OaVariables variables, Integer type) {
        ChecheTemplateHook hookPojo = ContextUtils.getContext().getBean(ChecheTemplateHookMapper.class)
                .findOne(variables.getProcessId(), type, variables.getStep());
        switch (type) {
            case HookCallbackType.START:
                if (hookPojo == null)
                    return;
                break;
            case HookCallbackType.PASS:
                if (hookPojo == null)
                    throw new ChecheException(ChecheExceptionEnum.E500, "template_hook Illegal.");
                break;
            default:
                throw new ChecheException(ChecheExceptionEnum.E501, "NOT supported.");
        }

        // 填充发送内容中的模板变量
        String body = FreeMarkerUtils.generate(
                String.format("cheche_hook_%d_%d", variables.getProcessId(), variables.getStep()), //
                hookPojo.getBodyData(), //
                OaApplyDataHelper.applyContentsMap(variables.getProcessId()));
        HookRequest hookTaskRequest = new HookRequest(hookPojo.getUrl(), hookPojo.getMethod(), body,
                hookPojo.getHeader());
        HookTask hookTask = new HookTask();
        Future<HookResponse> future = hookTask.async(hookTaskRequest);
        try {
            future.get(hookTask.getTimeoutSeconds(), TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            throw new ChecheException(ChecheExceptionEnum.E500, e);
        } catch (TimeoutException e) {
            throw new ChecheException(ChecheExceptionEnum.E500, "TIMEOUT.");
        }
    }
}
