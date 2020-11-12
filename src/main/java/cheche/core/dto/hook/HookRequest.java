package cheche.core.dto.hook;

import org.springframework.http.HttpMethod;

/**
 * Hook事件回调任务Request
 * 
 * @author jieli
 */
public class HookRequest {
    /** 请求地址 */
    private String     url;
    /** 请求方法 */
    private HttpMethod method;
    /** 请求体JSON */
    private String     body;
    /** 请求头JSON */
    private String     header;

    public HookRequest() {
    }

    public HookRequest(String url, String method, String body, String header) {
        this.url = url;
        this.method = HttpMethod.resolve(method);
        this.body = body;
        this.header = header;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
