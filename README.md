# cheche业务审批编排框架
(Jie © 2020)
## 1 关于[cheche业务审批编排框架](https://mp.weixin.qq.com/s/3YoQPepMTB9fyWsdEyoXVw)
不只是审批，更是一整套业务流程的整合方案！
- 基于业务流(business flow)“场景化”编排审批流程
- 整合/快速对接业务系统，并且（尤其是遗留系统）改造成本低、非侵入式
- “所见即所得”的自定义模板配置
- 支持“无人干预”系统自动审批、或签等审批方式

## 2 快速开始
### 2.1 准备工作
(1)数据库MySQL准备：<br/>建表（DDL执行./schema/cheche_schema.sql）；<br/>工程中配置数据库连接和DataSource数据源（默认淘宝TDDL+MyBatis）。<br/>
(2)多实例部署需实现IRedis接口的分布式锁。<br/>

### 2.2 模板管理
```Json
// 创建模板
POST /admin/template/save
{
  "applyContents": [
    {
      "config": {
        "options": [
          {
            "key": "quoteError",
            "value": "报价异常"
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

> 模板页面、流程编排功能未来将支持可视化的设置方式；<br/>
> TemplateBuilder链式生成器支持创建模板详情的元数据。
> ```Java
> TemplateContent template = new TemplateBuilder() //
> 	.code("cheche_demo").name("demo审批流").icon("模板图标URL").groupId(1L) // 模板信息
> 	.approvers() // 审批流程
> 	.add().className("CommonOaTask").user("jieli").admin("cheche") //
> 	.and() //
> 	.applyContents() // 模板控件
> 	.add().type("select").name("select_1").label("问题分类").placeholder("请选择").require(1).config(config) //
> 	.and() //
> 	.build();
> ```

### 2.3 审批流程
```
// 获取模板详情
GET /oa/template/get?templateCode=cheche_demo

// 发起审批申请或撤回
POST /oa/process/apply
POST /oa/process/withdraw

// 审批通过、驳回或转办
POST /oa/task/pass
POST /oa/task/reject
POST /oa/task/transfer
```

### 2.4 对接业务系统
只需要实现IOaTask接口，通过REST回调业务系统暴露的相关接口。cheche负责本地事务的边界控制以及在异常时的审批流数据回滚（或业务补偿逆向操作等）。
```Java
@Component
public class BizOaTask implements IOaTask {
	@Autowired
	private BizProxy bizProxy; // 业务系统FeignClientBean

	@Override
	public void start() {
		...略...
	}

	@Override
	public void pass() {
		...略...
		bizProxy.pass(request); // REST回调业务系统通过
	}

	@Override
	public void reject() {
		...略...
		bizProxy.reject(request); // REST回调业务系统驳回
	}

	@Override
	public void back() {
		...略...
		bizProxy.back(request); // REST回调业务系统撤回
	}
}
```

## 3 特殊案例
### 3.1 “无人干预”系统自动审批
对于半自动化的业务流（发起-->系统自动预审批-->人工审批），系统根据流程实例并按照既定的业务规则，来实现“无人干预”自动审批通过或驳回。<br />
(1)实现IAutoOaTask接口
```Java
@Component
public class BizAutoOaTask implements IAutoOaTask {
	@Override
	public boolean canAuto() {
		if 该业务实例按照既定规则，支持自动审批
			return true; // 自动
		else
			return false; // 人工
	}

	@Override
	public boolean canAutoPass() {
		if 该业务实例按照既定规则，支持自动审批通过
			return true; // 自动审批通过
		else
			return false; // 自动驳回
	}
}
```
(2)模板设置
```Json
{
  ...略...
  "approver": [
    {
      ...略...
      "className": "BizAutoOaTask",
    }
  ],
  ...略...
}
```
