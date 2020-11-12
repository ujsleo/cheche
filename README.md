# cheche业务审批编排框架
(Jie © 2020)
## 1 概述
**不只是审批，更是一整套业务流程的整合方案！**
- 基于业务流(business flow)“场景化”编排审批流程
- 整合/快速编排业务系统（尤其是遗留系统）的能力，并且改造成本低；支持自定义Hook事件回调
- “所见即所得”的自定义审批模板设置
- 支持“无人干预”系统自动审批、或签等审批方式

![架构设计](/dev-book/uml/Architecture.png)

### 1.1 场景描述
通过提供轻量级的“业务审批流程引擎”，将审批赋能业务流程化、经营智慧化。<br />
系统根据审批流程自动通知相关人员进行审批操作。<br />
审批状态变化回调通知指定的业务系统，再按具体业务需求进行拓展开发。

![时序图](/dev-book/uml/SequenceDiagram.png)

## 2 Quick Start
### 2.1 准备工作
1. 数据库MySQL准备：<br/>
建表（DDL执行./schema/cheche_schema.sql）；<br/>
工程中配置数据库连接和DataSource数据源（默认淘宝TDDL+MyBatis）。
2. 多实例部署需实现IRedis接口的分布式锁。

### 2.2 审批模板设置
1. [“所见即所得”的模板配置](/dev-book/ApprovalTemplate.md)*
自定义审批表单、“场景化”编排审批流程等

### 2.3 审批节点设置
1. [人工处理（类似OA）](/dev-book/CommonOaTask.md)
2. [事件回调](/dev-book/BizOaTask.md)，即定制化开发来编排业务系统（尤其是遗留系统）
3. [自定义Hook事件回调](/dev-book/CommonHookOaTask.md)，通过 `模板变量` 绑定第三方接口参数
4. [“无人干预”系统自动审批](/dev-book/AutoOaTask.md)，即“秒批”申请量大、重复性高、审核规则标准的业务流程，从而极大地解放人力
5. [跨层级审批](/dev-book/TBD)，即视业务实际情况上报、提前介入审批，从而提高业务流程运转时效

### 2.4 API文档
通过`bizCode业务唯一标识符`（如订单号、报价单号等）将审批单和业务数据绑定
1. [获取审批模板详情](/dev-book/TBD)，审批表单由控件构成
2. [发起/撤回审批申请](/dev-book/TBD)
3. 审批动作：[通过/驳回/转办/批量通过](/dev-book/TBD)
4. [获取审批申请详情](/dev-book/TBD)

## 3 其他
### 3.1 cheche-common工具类库
1. [JExecutor框架](/dev-book/JExecutor.md)
解耦合业务代码与超时策略、重试逻辑
