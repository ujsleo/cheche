# 审批节点：“无人干预”系统自动审批
(Jie © 2020)
## 1 概述
设置审批申请状态变化回调的通知方式为“系统自动审批”，当审批单流程发生变化时，cheche会按照既定的业务规则，来实现“无人干预”自动审批通过或驳回。

![“无人干预”系统自动审批](/dev-book/uml/AutoOaTask.png)
>**说明**<br />
>(1) 此方式需手写代码；<br />
>(2) 业务规则的实现建议使用Drools决策引擎。

## 2 Quick Start
以业务流（发起-->系统自动预审批-->人工审批）为示例，
### 2.1 模板配置
此方式的className为实现了**IAutoOaTask**接口的类。

#### 示例
```Json
{
    "approver": [
        {
            "className": "BizAutoOaTask"
        },
        {
            "className": "CommonOaTask"
        }
    ]
}
```

### 2.2 实现IAutoOaTask接口
```Java
@Component("BizAutoOaTask")
public class BizAutoOaTask implements IAutoOaTask {
	@Autowired
	private DecisionProxy decisionProxy; // cheche-decision决策引擎的FeignClientBean

	@Override
	public boolean canAuto() {
		boolean ruleRet = decisionProxy.fire(); // 执行业务规则
		if (ruleRet) // 该业务实例按照既定规则，支持自动审批
			return true; // 自动
		else
			return false; // 人工
	}

	@Override
	public boolean canAutoPass() {
		boolean ruleRet = decisionProxy.fire(); // 执行业务规则
		if (ruleRet) // 该业务实例按照既定规则，支持自动审批通过
			return true; // 自动审批通过
		else
			return false; // 自动驳回
	}
}
```
