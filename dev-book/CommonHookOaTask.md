# 审批节点：自定义Hook事件回调
(Jie © 2020)
## 1 概述
设置审批申请状态变化回调的通知方式为“Hook-自定义”，当审批单流程发生变化时，cheche会以指定方式发送到自定义的Hook地址。<br />
支持 `模板变量` ${control_name}，即第三方接口中的参数绑定表单中的control_name，从而建立起映射关系来获取指定的控件值。
>**说明**<br />
>(1) 此方式对应的超时时间为5秒，如果发出请求后5秒内没有返回，则表示发送失败；<br />
>(2) 此方式无需手写代码。

## 2 Quick Start
### 2.1 模板配置
此方式的className为`CommonHookOaTask`。

#### 参数说明
参数 | 必须 | 说明 | 示例
---|---|---|---
type | 是 | Hook回调类型枚举（1-发起start 2-通过pass 4-驳回reject 8-撤回back） | 2
url | 是 | 自定义的Hook地址，支持路径占位符绑定模板变量 | https://cheche/detail?id={control_name}
method | 是 | 支持GET、POST、DELETE、PUT等请求方法 | POST
body_data | 是 | 自定义请求体(JSON)。支持接口参数绑定模板变量 | {"msgtype": "text","text": {"content": "申请理由：${control_name}"}}
header | 否 | 自定义请求头(JSON)。默认请求头为Content-Type: application/json;charset=UTF-8 | {"Content-Type":"application/json;charset=UTF-8","ticket": "TICKET"}

#### 示例
```Json
{
    "approver": [
        {
            "className": "CommonHookOaTask",
            "extraAttr": {
                "hook": [
                    {
                        "type": 1,
                        "url": "自定义的Hook地址",
                        "method": "HTTP请求方法",
                        "bodyData": "自定义请求体(JSON)",
                        "header": "自定义请求头(JSON)"
                    }
                ]
            }
        }
    ]
}
```

## 3 特例
### 3.1 数组类型参数绑定动态表单（@Deprecated 配置复杂，不推荐） 
发起人提交的动态表单，支持映射到第三方接口中的数组类型参数。注意此方式的模板变量是复杂对象类型。

#### (1) 动态表单的配置
control_name的配置表达式必须是 对象名.属性名. + index ！<br />
动态增减表单项即修改index值
```
"name": "items.key." + index, // items.key.0, items.key.1, …
"label": "申请理由",
```

#### (2) 数组类型参数的配置
模板变量：${对象名.属性名}
```
"arrs": [
  <#list items as item>
    {
      "a": "${item.key}"
    }
  </#list>
]
```
