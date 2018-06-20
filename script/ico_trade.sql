use `skyico`;



create table `t_coin_recharge_apply` (
	`apply_id` bigint unsigned not null comment '申请id',
	`asset_id` bigint unsigned not null comment '资产id',
	`user_id` bigint unsigned not null comment '会员id',
	`recharge_mode` tinyint unsigned not null comment '充值类型: 0-用户充值; 1-平台活动派送; 2-后台管理账号充值',
	`coin_id` bigint unsigned not null comment '币id',
	`coin_name` varchar(16) not null comment '币名称',
	`coin_token` varchar(16) not null comment '币token',
	`recharge_value` decimal(40,18) default null comment '充值数量',
	`apply_time` datetime not null comment '申请时间',
	`finish_time` datetime default null comment '完成时间',
	`from_plat_account` varchar(80) default null comment '来源平台账号',
	`from_address` varchar(80) default null comment '转出地址',
	`to_address` varchar(80) default null comment '转入地址',
	`tx_hash` varchar(100) default null comment '交易hash',
	`tx_btc_vout` int default null comment 'btc交易vout',
	`tx_fee` decimal(40, 18) DEFAULT NULL COMMENT '预期矿工费',
	`actual_tx_fee` decimal(40, 18) DEFAULT NULL COMMENT '实际矿工费',
	`tx_gaslimit` decimal(40,18) DEFAULT NULL COMMENT '手续费限额',
	`tx_gasprice` decimal(40,18) DEFAULT NULL COMMENT '手续费价格',
	`tx_value` varchar(30) DEFAULT NULL COMMENT '交易金额',
	`receipt_time` datetime default null comment '账单时间',
	`recharge_status` tinyint unsigned not null comment '充值状态: 0-申请;1-处理中;98-成功;99失败',
	`version` int unsigned not null default '0' comment '版本号',
	`result` tinyint unsigned not null comment '结果: 0-处理中(未知); 98-成功; 99-失败',
	`fail_code` varchar(16) default null comment '失败码',
	`fail_reason` varchar(256) default null comment '失败原因',
	`create_time` datetime not null comment '创建时间',
	`update_time` datetime not null comment '更新时间',
	primary key (`apply_id`),
	key `k_asset_id` (`asset_id`),
	key `k_user_id` (`user_id`),
	key `k_apply_time` (`apply_time`)
) engine=innodb default charset=utf8 comment='币种充值申请表';



-- create table `t_coin_recharge_flow` (
-- 	`flow_id` bigint unsigned not null comment '流水id',
-- 	`apply_id` bigint unsigned not null comment '充值申请id',
-- 	`asset_id` bigint unsigned not null comment '资产id',
-- 	`user_id` bigint unsigned not null comment '会员id',
-- 	`recharge_mode` tinyint unsigned not null comment '充值类型: 0-用户充值; 1-平台活动派送; 2-后台管理账号充值',
-- 	`coin_id` bigint unsigned not null comment '币id',
-- 	`coin_name` varchar(16) not null comment '币名称',
-- 	`coin_token` varchar(16) not null comment '币token',
-- 	`recharge_value` decimal(30,18) default null comment '充值数量',
-- 	`apply_time` datetime not null comment '申请时间',
-- 	`finish_time` datetime default null comment '完成时间',
-- 	`from_plat_account` varchar(80) default null comment '来源平台账号',
-- 	`from_address` varchar(80) default null comment '转出地址',
-- 	`to_address` varchar(80) default null comment '转入地址',
-- 	`tx_hash` varchar(100) default null comment '交易hash',
-- 	`receipt_time` datetime default null comment '账单时间',
-- 	`recharge_status` tinyint unsigned not null comment  '充值状态: 只有成功的记录',
-- 	`create_time` datetime not null comment '创建时间',
-- 	`update_time` datetime not null comment '更新时间',
-- 	primary key (`flow_id`),
-- 	key `k_apply_id` (`apply_id`),
-- 	key `k_asset_id` (`asset_id`),
-- 	key `k_user_id` (`user_id`),
-- 	key `k_coin_id` (`coin_id`)
-- ) engine=innodb default charset=utf8 comment='币种充值流水表';


create table `t_coin_cash_apply` (
	`apply_id` bigint unsigned not null comment '申请id',
	`asset_id` bigint unsigned not null comment '资产id',
	`user_id` bigint unsigned not null comment '会员id',
	`coin_id` bigint unsigned not null comment '币id',
	`coin_name` varchar(16) not null comment '币名称',
	`coin_token` varchar(16) not null comment '币token',
	`apply_time` datetime not null comment '申请时间',
	`finish_time` datetime default null comment '完成时间',
	`cash_value` decimal(40,18) default null comment '提现数量',
	`cash_fee` decimal(40,18) DEFAULT NULL comment '提现手续费',
	`actual_value` decimal(40,18) DEFAULT NULL COMMENT '实际到账',
	`cash_status` tinyint unsigned DEFAULT NULL COMMENT '提现状态 0:提现待确认; 1:提现失败需人工处理; 2:处理中; 98:提现成功; 99:提现失败; ',
	`version` int unsigned not null default '0' comment '版本号',
	`result` tinyint unsigned not null comment '结果: 0-处理中(未知); 98-成功; 99-失败',
	`fail_code` varchar(16) default null comment '失败码',
	`fail_reason` varchar(256) default null comment '失败原因',
	`cash_email_code` varchar(80) DEFAULT NULL COMMENT '提现申请邮箱验证码',
	`cash_google_auth` varchar(100) DEFAULT NULL COMMENT '谷歌授权码',
	`from_address` varchar(80) DEFAULT NULL COMMENT '转出地址',
	`to_address` varchar(80) DEFAULT NULL COMMENT '转入地址',
	`tx_hash` varchar(100) DEFAULT NULL COMMENT '交易hash',
	`tx_btc_vout` int default null comment 'btc交易vout',
	`tx_fee` decimal(40, 18) DEFAULT NULL COMMENT '预期矿工费',
	`actual_tx_fee` decimal(40, 18) DEFAULT NULL COMMENT '实际矿工费',
	`tx_gaslimit` decimal(40,18) DEFAULT NULL COMMENT '手续费限额',
	`tx_gasprice` decimal(40,18) DEFAULT NULL COMMENT '手续费价格',
	`tx_value` varchar(30) DEFAULT NULL COMMENT '交易金额',
	primary key (`apply_id`),
	KEY `k_user_id` (`user_id`),
	KEY `k_asset_id` (`asset_id`),
) engine=innodb default charset=utf8 comment='币种提现申请表';


-- create table `t_coin_cash_apply_flow` (

-- ) engine=innodb default charset=utf8 comment='币种提现申请流水表';


create table `t_coin_tx_receipt` (
	`apply_id` bigint unsigned not null comment '充值申请id',
	`tx_mode` tinyint unsigned not null comment '交易类型: 0-充值; 1-提现; 2-热转冷; 3-热转冷eth矿工费',
	`tx_receipt` varchar(2560) default null comment '交易凭证json',
	primary key (`apply_id`, tx_mode)
) engine=innodb default charset=utf8 comment='币种充值申请交易凭证json表';


create table `t_coin_hot_to_cold_apply` (
	`apply_id` bigint unsigned not null comment '申请id',
	`asset_id` bigint unsigned default null comment '资产id',
	`user_id` bigint unsigned default null comment '会员id',
	`coin_id` bigint unsigned not null comment '币id',
	`coin_name` varchar(16) not null comment '币名称',
	`coin_token` varchar(16) not null comment '币token',
	`apply_time` datetime not null comment '申请时间',
	`finish_time` datetime default null comment '完成时间',
	`transfer_value` decimal(40,18) default null comment '划拨数量',
	`actual_value` decimal(40,18) DEFAULT NULL COMMENT '实际到账',
	`transfer_status` tinyint unsigned DEFAULT NULL COMMENT '划拨状态: 98:成功; 99:失败; ',
	`version` int unsigned not null default '0' comment '版本号',
	`result` tinyint unsigned not null comment '结果: 0-处理中(未知); 98-成功; 99-失败',
	`fail_code` varchar(16) default null comment '失败码',
	`fail_reason` varchar(256) default null comment '失败原因',
	`from_address` varchar(80) DEFAULT NULL COMMENT '转出地址',
	`to_address` varchar(80) DEFAULT NULL COMMENT '转入地址',
	`tx_hash` varchar(100) DEFAULT NULL COMMENT '交易hash',
	`tx_btc_vout` int default null comment 'btc交易vout',
	`tx_fee` decimal(40, 18) DEFAULT NULL COMMENT '预期矿工费',
	`actual_tx_fee` decimal(40, 18) DEFAULT NULL COMMENT '实际矿工费',
	`tx_gaslimit` decimal(40,18) DEFAULT NULL COMMENT '手续费限额',
	`tx_gasprice` decimal(40,18) DEFAULT NULL COMMENT '手续费价格',
	`tx_value` varchar(30) DEFAULT NULL COMMENT '转账金额',
	primary key (`apply_id`),
	KEY `k_user_id` (`user_id`),
	KEY `k_asset_id` (`asset_id`),
	KEY `k_coin_id` (`coin_id`)
) engine=innodb default charset=utf8 comment='币种热转冷申请表';


create table `t_coin_hot_to_cold_plat_eth_tx_fee_transfer_apply` (
	`apply_id` bigint unsigned not null comment '申请id',
	`asset_id` bigint unsigned default null comment '资产id',
	`user_id` bigint unsigned default null comment '会员id',
	`coin_id` bigint unsigned not null comment '币id',
	`coin_name` varchar(16) not null comment '币名称',
	`coin_token` varchar(16) not null comment '币token',
	`apply_time` datetime not null comment '申请时间',
	`finish_time` datetime default null comment '完成时间',
	`transfer_amount` decimal(40,18) default null comment '划拨数量',
	`actual_value` decimal(40,18) DEFAULT NULL COMMENT '实际到账',
	`transfer_status` tinyint unsigned DEFAULT NULL COMMENT '划拨状态: 98:提现成功; 99:提现失败; ',
	`version` int unsigned not null default '0' comment '版本号',
	`result` tinyint unsigned not null comment '结果: 0-处理中(未知); 98-成功; 99-失败',
	`fail_code` varchar(16) default null comment '失败码',
	`fail_reason` varchar(256) default null comment '失败原因',
	`from_address` varchar(80) DEFAULT NULL COMMENT '转出地址',
	`to_address` varchar(80) DEFAULT NULL COMMENT '转入地址',
	`tx_hash` varchar(100) DEFAULT NULL COMMENT '交易hash',
	`tx_btc_vout` int default null comment 'btc交易vout',
	`tx_fee` decimal(40, 18) DEFAULT NULL COMMENT '预期矿工费',
	`actual_tx_fee` decimal(40, 18) DEFAULT NULL COMMENT '实际矿工费',
	`tx_gaslimit` decimal(40,18) DEFAULT NULL COMMENT '手续费限额',
	`tx_gasprice` decimal(40,18) DEFAULT NULL COMMENT '手续费价格',
	`tx_value` varchar(30) DEFAULT NULL COMMENT '交易金额',
	primary key (`apply_id`)
) engine=innodb default charset=utf8 comment='币种热转冷平台eth矿工费划拨申请表';

-- create table `t_coin_hot_to_cold_apply_flow` (

-- ) engine=innodb default charset=utf8 comment='币种热转冷申请变更流水表';




