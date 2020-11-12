# cheche-common工具类库
(Jie © 2018)
## 1 JExecutor框架:解耦合业务代码与超时策略、重试逻辑
![任务执行状态图](/dev-book/uml/ExecutorStatus.png)
- 支持同步、异步、延迟执行
- 支持超时策略
- 支持重试逻辑

### 1.1 快速开始
(1)继承AbstractExecutorTask<U, V>，自定义超时策略、重试逻辑、延迟逻辑等
```Java
/**
 * 【注意】
 * BizTask的超时策略、重试逻辑不能被Spring管理，必须new！如果用到Spring，可以构造方法传进去
 */
public class BizTask extends AbstractExecutorTask<BizRequest, BizResponse> {
    // 如果用到Spring，请使用这个构造方法传进去
    private BizBean bizBean;

    public BizTask(BizBean bizBean) {
        super(); // 调用父构造方法
        this.bizBean = bizBean;
    }

    @Override
    protected void init() {
        name = "BizTask";
        // 加载预设的超时策略、重试逻辑
        loadPolicy(RETRY_POLICY);
        // 延迟逻辑（延迟30秒执行）
        delaySeconds = 30;
    }

    @Override
    protected BizResponse doExecute(BizRequest request) {
        // 业务代码
    }

    @Override
    protected BizResponse recovery(BizRequest request) {
        // 重试次数到达阈值，回调处理
    }
}
```
(2)根据实际业务场景来执行
```Java
BizTask bizTask = new BizTask();
// (1)同步执行+超时策略、重试逻辑
BizResponse bizResponse = bizTask.sync(bizRequest);
// (2)异步执行
Future<BizResponse> future = bizTask.async(bizRequest);
// (3)延迟执行
bizTask.delay(bizRequest);
// (4)直接执行
BizResponse bizResponse = bizTask.execute(bizRequest);
```

### 1.2 任务的自定义设置
#### 超时策略
参数 | 描述
---|---
timeoutPolicy | TERMINATED: 超时终止<br />RETRY: 超时重试
timeoutSeconds | 超时阈值(s)

#### 重试逻辑
参数 | 描述
---|---
retryLogic | RETRY_NAN: 无需重试<br />RETRY_FIXED: retryDelaySeconds后重试<br />RETRY_BACKOFF: (retryDelaySeconds * 剩余重试次数)后重试
retryCount | 当任务被标记为FAILED时，尝试重试的次数
retryDelaySeconds | 重试的延时阈值(s)

#### 延时逻辑
参数 | 描述
---|---
delaySeconds | 延时(s)

### 1.3 任务的有限状态机
状态 | 描述
---|---
IN_PROGRESS | 执行中
FAILED | 失败。先触发重试逻辑，否则任务终止
COMPLETED | 成功
