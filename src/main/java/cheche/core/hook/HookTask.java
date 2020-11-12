package cheche.core.hook;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import cheche.common.executor.AbstractExecutorTask;
import cheche.common.utils.JsonUtils;
import cheche.core.dto.hook.HookRequest;
import cheche.core.dto.hook.HookResponse;

/**
 * Hook事件回调任务
 * 
 * @author jieli
 */
public class HookTask extends AbstractExecutorTask<HookRequest, HookResponse> {
    @Override
    protected void init() {
        name = "HookTask";
        // 超时时间为5秒，如果发出请求后5秒内没有返回，则表示发送失败
        timeoutSeconds = 5;
    }

    @Override
    protected HookResponse doExecute(HookRequest request) {
        HookResponse ret = new HookResponse();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(request.getBody(), //
                headers(request.getHeader()));
        String responseJson = restTemplate.exchange(request.getUrl(), //
                request.getMethod(), //
                requestEntity, //
                String.class, //
                uriVariables(request.getBody())).getBody();
        ret.setValue(responseJson);
        return ret;
    }

    @Override
    protected HookResponse recovery(HookRequest request) {
        return null;
    }

    /**
     * 默认请求头为Content-Type: application/json;charset=utf-8
     * 
     * @param headerJson 请求头JSON
     * @return
     */
    @SuppressWarnings("unchecked")
    private HttpHeaders headers(String headerJson) {
        HttpHeaders ret = new HttpHeaders();
        if (StringUtils.isEmpty(headerJson))
            ret.setContentType(MediaType.APPLICATION_JSON_UTF8);
        else
            ret.setAll(JsonUtils.parseObject(headerJson, Map.class));
        return ret;
    }

    /**
     * URL参数
     * 
     * @param bodyJson 请求内容JSON
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, ?> uriVariables(String bodyJson) {
        return JsonUtils.parseObject(bodyJson, Map.class);
    }
}
