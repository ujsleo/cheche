# “所见即所得”的模板配置
(Jie © 2020)
## 1 概述
自定义审批模板，支持审批表单的预览，“所见即所得”，保存并应用之后会立即生效。
>**说明**<br />
>(1) 审批表单搭建、流程编排功能未来将支持可视化的设置方式。

## 2 Quick Start
### 2.1 模板配置
#### 参数说明
参数 | 必须 | 说明
---|---|---
code | 否 | 模板CODE，模板的唯一标识符UNIQUE。默认模板CODE = cheche_ + md5(模板名称)
status | 否 | 模板状态：0-已停用 1-已启用
name | 是 | 模板名称
icon | 是 | 模板图标
groupId | 是 | 模板分组ID：默认1-其他
groupName | 否 | 模板分组名称
**applyContents** | 否 | 模板控件信息，数组。用于模板设置，构成审批表单。详见附表
**approver** | 是 | 审批流程信息，数组。用于规则设置
├ user |  | 审批节点的审批人域账号列表，以逗号,分隔
├ role |  | 审批节点的审批角色列表，以逗号,分隔
├ admin |  | 审批节点的管理员域账号列表，以逗号,分隔
├ className | 是 | 审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统
├ type | 是 | 节点审批方式：0-或签 1-会签
├ step | 是 | 级次
└ **extraAttr** | 否 | 额外的参数
**notifyer** |  | 抄送人

##### 附表：模板控件信息applyContents参数说明
参数 | 必须 | 说明
---|---|---
id | 否 | 控件ID，系统自动生成，控件的唯一标识符。用于定位数据
type | 是 | 控件类型：text-文本 textarea-多行文本 select-单选 multi-多选 file-附件 image-图像 tips-说明文字 date-日期
name | 是 | 控件名
label | 是 | 控件展示名
value | 否 | 控件值/默认值
placeholder | 否 | 控件说明，向申请者展示控件的填写说明
require | 是 | 是否必填：1-必填 0-选填（默认）
**config** | 否 | 单选/多选控件中的所有选项
├ type |  | 选择类型：single-单选；multi-多选
└ **options** |  | 单选/多选控件中的所有选项
├ ├ key |  | 选项key，选项的唯一标识符。可用于发起审批时的控件赋值
└ └ value |  | 选项值

#### 示例
![“所见即所得”的页面模板配置](/dev-book/uml/ApprovalTemplate.png)

```Json
{
    "applyContents": [
        {
            "config": {
                "options": [
                    {
                        "key": "quoteError",
                        "value": "报价异常"
                    },
                    {
                        "key": "issueError",
                        "value": "投保异常"
                    }
                ],
                "type": "single"
            },
            "label": "问题分类",
            "name": "select_1",
            "placeholder": "请选择",
            "require": 1,
            "type": "select"
        }
    ],
    "approver": [
        {
            "admin": "cheche",
            "className": "CommonOaTask",
            "step": 1,
            "type": 0,
            "user": "jieli"
        }
    ],
    "code": "cheche_demo",
    "groupId": 1,
    "icon": "模板图标URL",
    "name": "demo审批流"
}
```
