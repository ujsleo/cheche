CREATE TABLE `cheche_template` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` VARCHAR(128) NOT NULL COMMENT '模板CODE，模板的唯一标识符',
  `status` INT NOT NULL DEFAULT 1 COMMENT '模板状态：0-已停用 1-已启用',
  `name` VARCHAR(128) NOT NULL COMMENT '模板名称',
  `icon` VARCHAR(512) NOT NULL COMMENT '模板图标',
  `group_id` BIGINT NOT NULL DEFAULT 1 COMMENT '模板分组ID',
  `is_deleted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N-未删除Y-已删除',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '创建人',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_code` (`code`)
) COMMENT='cheche模板' DEFAULT CHARSET=utf8mb4 ENGINE=InnoDB;

CREATE TABLE `cheche_template_approver` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_id` BIGINT NOT NULL COMMENT '模板ID',
  `class_name` VARCHAR(128) NOT NULL COMMENT '审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统',
  `type` INT NOT NULL DEFAULT 0 COMMENT '节点审批方式：0-或签 1-会签',
  `step` INT NOT NULL DEFAULT 0 COMMENT '级次',
  `role_id` INT NOT NULL DEFAULT 1 COMMENT '审批角色ID：0-抄送人 1-审批人/处理人 16-管理员',
  `user` VARCHAR(512) COMMENT '域账号列表，以逗号,分隔',
  `role` VARCHAR(512) COMMENT '角色列表，以逗号,分隔',
  `admin` VARCHAR(512) COMMENT '管理员域账号列表，以逗号,分隔',
  `is_deleted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N-未删除Y-已删除',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '创建人',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '修改人',
  PRIMARY KEY (`id`),
  INDEX `idx_template_id` (`template_id`)
) COMMENT='cheche审批规则' DEFAULT CHARSET=utf8mb4 ENGINE=InnoDB;

CREATE TABLE `cheche_template_hook` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_id` BIGINT NOT NULL COMMENT '模板ID',
  `approver_id` BIGINT NOT NULL COMMENT '审批规则ID',
  `type` Integer NOT NULL COMMENT '回调类型。1-发起start 2-通过pass 4-驳回reject 8-撤回back',
  `url` VARCHAR(512) COMMENT '请求地址',
  `method` VARCHAR(16) COMMENT '请求方法POST GET',
  `body_data` VARCHAR(1024) COMMENT '请求体JSON(支持使用模版变量)',
  `header` VARCHAR(256) COMMENT '请求头',
  `is_deleted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N-未删除Y-已删除',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL DEFAULT 'SYS' COMMENT '创建人',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL DEFAULT 'SYS' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_com` (`approver_id`,`type`),
  INDEX `idx_template_id` (`template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='cheche事件回调Hook';

CREATE TABLE `cheche_template_control` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_id` BIGINT NOT NULL COMMENT '模板ID',
  `type` VARCHAR(32) NOT NULL COMMENT '控件类型',
  `name` VARCHAR(32) NOT NULL COMMENT '控件名',
  `label` VARCHAR(128) NOT NULL COMMENT '控件展示名',
  `value` VARCHAR(128) COMMENT '控件默认值',
  `placeholder` VARCHAR(128) COMMENT '控件说明，向申请者展示控件的填写说明',
  `require` INT NOT NULL DEFAULT 0 COMMENT '是否必填：1-必填 0-选填',
  `config` VARCHAR(4096) COMMENT '控件配置JSON',
  `is_deleted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N-未删除Y-已删除',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '创建人',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '修改人',
  PRIMARY KEY (`id`),
  INDEX `idx_template_id` (`template_id`)
) COMMENT='cheche模板控件' DEFAULT CHARSET=utf8mb4 ENGINE=InnoDB;

CREATE TABLE `cheche_template_group` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(32) NOT NULL COMMENT '模板分组名称',
  `is_deleted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N-未删除Y-已删除',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '创建人',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '修改人',
  PRIMARY KEY (`id`)
) COMMENT='cheche模板分组' DEFAULT CHARSET=utf8mb4 ENGINE=InnoDB;
INSERT INTO cheche_template_group(id, name) VALUES(1, '其他');

CREATE TABLE `cheche_apply_process` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` VARCHAR(256) NOT NULL COMMENT '流程CODE',
  `sn` VARCHAR(32) NOT NULL COMMENT '审批单号',
  `status` INT NOT NULL COMMENT '审批状态',
  `step` INT NOT NULL DEFAULT 0 COMMENT '级次',
  `template_code` VARCHAR(128) NOT NULL COMMENT '模板CODE',
  `biz_code` VARCHAR(128) NOT NULL COMMENT '业务CODE',
  `user` VARCHAR(32) NOT NULL COMMENT '申请人的域账号',
  `title` VARCHAR(256) NOT NULL COMMENT '申请单标题',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `is_deleted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N-未删除Y-已删除',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '创建人',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE `uniq_code` (`code`),
  INDEX `idx_com` (`user`, `gmt_created`)
) COMMENT='cheche审批流程' DEFAULT CHARSET=utf8mb4 ENGINE=InnoDB;

CREATE TABLE `cheche_apply_task` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `process_id` BIGINT NOT NULL COMMENT '流程ID',
  `status` INT NOT NULL COMMENT '节点状态',
  `class_name` VARCHAR(128) NOT NULL COMMENT '审批节点的类名，框架反射用（需实现IOaTask接口）。一般用于回调通知业务系统',
  `type` INT NOT NULL DEFAULT 0 COMMENT '节点审批方式：0-或签 1-会签',
  `step` INT NOT NULL COMMENT '级次',
  `is_deleted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N-未删除Y-已删除',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '创建人',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '修改人',
  PRIMARY KEY (`id`),
  INDEX `idx_process_id` (`process_id`)
) COMMENT='cheche审批节点' DEFAULT CHARSET=utf8mb4 ENGINE=InnoDB;

CREATE TABLE `cheche_apply_task_spot` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` BIGINT NOT NULL COMMENT '节点ID',
  `process_id` BIGINT NOT NULL COMMENT '流程ID',
  `user` VARCHAR(32) NOT NULL COMMENT '处理人的域账号',
  `assigner` VARCHAR(32) COMMENT '（处理人转办）交办人的域账号',
  `status` INT NOT NULL COMMENT '关注状态',
  `role_id` INT NOT NULL COMMENT '角色ID',
  `remark` VARCHAR(128) COMMENT '（处理人转办）备注',
  `step` INT NOT NULL COMMENT '级次',
  `is_deleted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N-未删除Y-已删除',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '创建人',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '修改人',
  PRIMARY KEY (`id`),
  INDEX `idx_task_id` (`task_id`),
  INDEX `idx_process_id` (`process_id`),
  INDEX `idx_com` (`user`, `gmt_created`)
) COMMENT='cheche审批节点的关注' DEFAULT CHARSET=utf8mb4 ENGINE=InnoDB;

CREATE TABLE `cheche_apply_control` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `process_id` BIGINT NOT NULL COMMENT '流程ID',
  `control_id` BIGINT NOT NULL COMMENT '模板控件ID',
  `name` VARCHAR(32) NOT NULL COMMENT '控件名',
  `value` VARCHAR(128) COMMENT '控件默认值',
  `is_deleted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N-未删除Y-已删除',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '创建人',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '修改人',
  PRIMARY KEY (`id`),
  INDEX `idx_process_id` (`process_id`)
) COMMENT='cheche审批申请数据' DEFAULT CHARSET=utf8mb4 ENGINE=InnoDB;

CREATE TABLE `cheche_apply_event` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `process_id` BIGINT NOT NULL COMMENT '流程ID',
  `type` INT NOT NULL COMMENT '审批事件类型',
  `user` VARCHAR(32) NOT NULL COMMENT '处理人的域账号',
  `remark` VARCHAR(128) NOT NULL COMMENT '备注',
  `is_deleted` char(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N-未删除Y-已删除',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creator` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '创建人',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifier` varchar(32) NOT NULL DEFAULT 'cheche' COMMENT '修改人',
  PRIMARY KEY (`id`),
  INDEX `idx_process_id` (`process_id`)
) COMMENT='cheche审批事件' DEFAULT CHARSET=utf8mb4 ENGINE=InnoDB;
