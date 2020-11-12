# 审批节点：人工处理（类似OA）
(Jie © 2020)
## 1 概述
设置审批申请状态变化回调的通知方式为“人工处理”，当审批单流程发生变化时，cheche会以企业微信/邮件等方式通知相关人员手动处理（类似OA）。
>**说明**<br />
>(1) 此方式无需手写代码。

## 2 Quick Start
以审批流程（发起-->审批人：jieli-->审批人：上级角色+管理员）为示例，
### 2.1 模板配置
此方式的className为`CommonOaTask`。

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
            "admin": "cheche",
            "className": "CommonOaTask",
            "role": "CHIEF",
            "type": 0
        }
    ]
}
```
