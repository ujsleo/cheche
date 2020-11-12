# 审批节点：事件回调
(Jie © 2020)
## 1 概述
设置审批申请状态变化回调的通知方式为“事件回调”，当审批单流程发生变化时，cheche会以企业微信/邮件等方式通知相关人员，审批动作回调业务系统。
>**说明**<br />
>(1) 此方式需手写代码。

## 2 Quick Start
以业务流（发起-->审批人：jieli-->审批人：上级角色 审批动作回调业务系统）为示例，
### 2.1 模板配置
此方式的className为实现了**IOaTask**接口的类。

#### 示例
```Json
{
    "approver": [
        {
            "className": "CommonOaTask",
            "type": 0,
            "user": "jieli"
        },
        {
            "className": "BizOaTask",
            "role": "CHIEF",
            "type": 0
        }
    ]
}
```

### 2.2 实现IOaTask接口
定制化开发（实现**IOaTask**接口），当审批单流程发生变化时通过REST回调第三方接口。<br />
借鉴了两阶段提交(2 Phase Commit)的思路（而不是引入分布式事务TCC模式的复杂性），cheche负责本地事务的边界控制以及在异常时的审批流数据回滚（或业务补偿逆向操作等）。
```Java
@Component("BizOaTask")
public class BizOaTask implements IOaTask {
	@Autowired
	private BizProxy bizProxy; // 业务系统的FeignClientBean

	@Override
	public void start() {
		// 可选。申请冻结相关系统的业务数据（或状态置ING中间状态）
		bizProxy.start(request);
	}

	@Override
	public void pass() {
		// 必须。业务数据从冻结到提交（或状态置已处理状态）
		bizProxy.pass(request);
	}

	@Override
	public void reject() {
		// 可选。回调业务系统驳回
		bizProxy.reject(request);
	}

	@Override
	public void back() {
		// 可选。取消冻结
		bizProxy.back(request);
	}
}
```
