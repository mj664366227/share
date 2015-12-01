
-- 商品类型配置		2015.11.24		luo
-- 表的结构 `gu_config`.`shop_goods_type`
CREATE TABLE IF NOT EXISTS `gu_config`.`shop_goods_type` (
  `id` bigint(20) NOT NULL COMMENT '类型id',
  `name` varchar(50) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型配置';

-- 积分商城商品		2015.11.24		luo
-- 表的结构 `gu`.`shop_goods`
CREATE TABLE IF NOT EXISTS `gu`.`shop_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(60) NOT NULL COMMENT '商品名称',
  `introduce` text NOT NULL COMMENT '商品简介',
  `score` int(11) NOT NULL COMMENT '兑换分数',
  `num` int(11) NOT NULL COMMENT '数量',
  `type` varchar(20) NOT NULL COMMENT '分类',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态(0=下架,1=上架)',
  `company_id` bigint(20) NOT NULL COMMENT '商家',
  `create_time` int(11) NOT NULL COMMENT '添加时间',
  `image1` varchar(500) NOT NULL COMMENT '图片1',
  PRIMARY KEY (`id`),
  KEY `state` (`state`),
  KEY `company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分商城商品';

-- 专案审核   2015.11.26     ruan(已加)
ALTER TABLE `gu`.`case` ADD `is_pass` TINYINT NOT NULL DEFAULT '1' COMMENT '是否审核通过(1-是 0-否)' AFTER `is_show`;

-- 手机支持国际号码    2015.11.26     ruan(已加)
ALTER TABLE `gu`.`user` CHANGE `mobile` `mobile` VARCHAR(20) NOT NULL COMMENT '手机号码';

-- 支付密码		2015.11.23		luo(已加)
ALTER TABLE `gu`.`user` ADD `payment_password` CHAR(32) NOT NULL COMMENT '支付密码' AFTER `password`;

-- 完善举报功能   2015.11.13    ruan(已加)
ALTER TABLE  `gu_log`.`user_accusation` ADD  `content` VARCHAR( 500 ) NOT NULL COMMENT  '举报内容' AFTER  `type_id`;

-- 报错日志加操作系统字段   2015.11.13    ruan(已加)
ALTER TABLE  `gu_log`.`crash_log` ADD  `os` VARCHAR( 500 ) NOT NULL COMMENT  '操作系统' AFTER  `mobile_type`;
ALTER TABLE  `gu_log`.`crash_log` ADD  `resolve_time` INT NOT NULL COMMENT  '解决时间' AFTER  `create_time`;

-- 在线统计   2015.11.12    ruan(已加)
CREATE TABLE `gu_log`.`online_user` (
 `date` bigint(11) NOT NULL COMMENT '日期(格式：yyyyMMddHHmm)',
 `num` int(11) NOT NULL COMMENT '在线数量',
 PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户在线统计';

CREATE TABLE `gu_log`.`online_company` (
 `date` bigint(11) NOT NULL COMMENT '日期(格式：yyyyMMddHHmm)',
 `num` int(11) NOT NULL COMMENT '在线数量',
 PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业在线统计';

--  充值测试   2015.11.11     ruan(已加)
ALTER TABLE  `gu_config`.`system` ADD  `test_charge_company` VARCHAR( 1000 ) NOT NULL COMMENT  '测试充值的企业' AFTER  `min_checkout_points`;

-- 重建用户实名认证信息表		2015.11.10		luo(已加)
-- 表的结构 `user_prove`
DROP TABLE `gu`.`user_prove`;
CREATE TABLE IF NOT EXISTS `gu`.`user_prove` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `prove_info` varchar(60) NOT NULL COMMENT '认证信息',
  `identity_card` varchar(500) NOT NULL COMMENT '身份证图片',
  `other_card` varchar(500) NOT NULL COMMENT '其他证件图片',
  `is_prove` int(11) NOT NULL COMMENT '是否实名认证',
  `create_time` int(11) NOT NULL,
  `check_time` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `is_prove` (`is_prove`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户实名认证信息表';

-- 用户消息数量		2015.11.10		luo(已加)
-- 表的结构`gu`.`user_message_num`
CREATE TABLE IF NOT EXISTS `gu`.`user_message_num` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `message_type` int(11) NOT NULL COMMENT '消息大类型',
  `message_sign` int(11) NOT NULL COMMENT '消息小类型',
  `num` int(11) NOT NULL COMMENT '数量',
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户消息数量';

-- 专案表增加征集类型字段		2015.11.10		ruan(已加)
ALTER TABLE  `gu`.`case` ADD  `style` int NOT NULL COMMENT  '征集类型' AFTER  `type`;

-- 专案征集类型		2015.11.09		ruan(已加)
CREATE TABLE `gu_config`.`case_style` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类型id',
 `name` varchar(50) NOT NULL COMMENT '类型名称',
 `points` int(11) NOT NULL COMMENT '对应G点',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案征集类型配置';


-- 创意添加观看权限		2015.11.09		luo(已加)
ALTER TABLE  `gu`.`idea` ADD  `visit` VARCHAR( 6000 ) NOT NULL DEFAULT  '-1' COMMENT  '观看权限(整数=用户id,0=不公开,-1=all,-2=friend)';
-- 用户积分增加流水表		2015.11.06		luo (已加)
-- 表的结构 `gu_log`.`user_score_add_log`
CREATE TABLE IF NOT EXISTS `gu_log`.`user_score_add_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `score` int(11) NOT NULL COMMENT '增加的积分',
  `event` int(11) NOT NULL COMMENT '积分增加事件',
  `create_time` int(11) NOT NULL COMMENT '记录生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户积分增加流水表';

-- 用户积分减少流水表		2015.11.06		luo(已加)
-- 表的结构 `gu_log`.`user_score_sub_log`
CREATE TABLE IF NOT EXISTS `gu_log`.`user_score_sub_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `score` int(11) NOT NULL COMMENT '减少的积分',
  `event` int(11) NOT NULL COMMENT '积分减少事件',
  `create_time` int(11) NOT NULL COMMENT '记录生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户积分减少流水表';

-- 用户手机型号分布    2015.11.05     ruan(已加)
CREATE TABLE `gu_log`.`user_mobile_type` (
 `mobile_type` varchar(100) NOT NULL COMMENT '手机机型',
 `num` int(11) NOT NULL COMMENT '使用数量',
 PRIMARY KEY (`mobile_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户app版本分布';

-- 用户信息统计    2015.11.04     ruan(已加)
CREATE TABLE `gu_log`.`user` (
 `id` bigint(11) NOT NULL COMMENT '用户id',
 `version` int(11) NOT NULL COMMENT '当前使用的app版本号',
 `mobile_type` varchar(100) NOT NULL COMMENT '手机机型',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息统计';

-- 用户app版本分布    2015.11.04     ruan (已加)
CREATE TABLE `gu_log`.`user_app_version` (
 `version` int(11) NOT NULL COMMENT '版本号',
 `num` int(11) NOT NULL COMMENT '使用数量',
 PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户app版本分布';

-- 企业趋势统计(按照顺序执行)      2015.11.03     ruan  (已加)
RENAME TABLE  `gu_log`.`recent7_active` TO  `gu_log`.`user_recent7_active` ;
RENAME TABLE  `gu_log`.`recent14_active` TO  `gu_log`.`user_recent14_active` ;

CREATE TABLE `gu_log`.`company_recent7_active` (
 `start_date` int(11) NOT NULL COMMENT '开始日期',
 `end_date` int(11) NOT NULL COMMENT '结束日期',
 `num` int(11) NOT NULL COMMENT '活跃数量',
 UNIQUE KEY `start_date` (`start_date`,`end_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='最近7天活跃企业';

CREATE TABLE `gu_log`.`company_recent14_active` (
 `start_date` int(11) NOT NULL COMMENT '开始日期',
 `end_date` int(11) NOT NULL COMMENT '结束日期',
 `num` int(11) NOT NULL COMMENT '活跃数量',
 UNIQUE KEY `start_date` (`start_date`,`end_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='最近14天活跃企业';

CREATE TABLE `gu_log`.`company_pub_case_week` (
 `week` int(11) NOT NULL COMMENT '周',
 `company_num` int(11) NOT NULL COMMENT '发专案企业数',
 `case_num` int(11) NOT NULL COMMENT '专案数',
 PRIMARY KEY (`week`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平均每个品牌每周发布专案的次数';

CREATE TABLE `gu_log`.`company_pub_case_month` (
 `month` int(11) NOT NULL COMMENT '月',
 `company_num` int(11) NOT NULL COMMENT '发专案企业数',
 `case_num` int(11) NOT NULL COMMENT '专案数',
 PRIMARY KEY (`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平均每个品牌每月发布专案的次数';

CREATE TABLE `gu_log`.`company_pub_case` (
 `date` int(11) NOT NULL COMMENT '日期',
 `company_num` int(11) NOT NULL COMMENT '企业数',
 PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发布专案的品牌统计';

CREATE TABLE `gu_log`.`active_company` (
 `date` int(11) NOT NULL COMMENT '日期',
 `reg_num` int(11) NOT NULL COMMENT '截至今天总注册数',
 `active` int(11) NOT NULL COMMENT '今天的活跃企业数',
 PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业日活跃率统计';

CREATE TABLE `gu_log`.`case_pub_week` (
 `week` int(11) NOT NULL COMMENT '周',
 `case_num` int(11) NOT NULL COMMENT '专案数',
 PRIMARY KEY (`week`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每周发布专案数统计';

CREATE TABLE `gu_log`.`case_total_count` (
 `week` int(11) NOT NULL COMMENT '周',
 `case_num` int(11) NOT NULL COMMENT '结束了的专案数',
 `take_part_in_num` int(11) NOT NULL COMMENT '总参与数',
 `idea_num` int(11) NOT NULL COMMENT '总点子数',
 PRIMARY KEY (`week`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案概况统计';

-- 充值流水记录   2015.10.30    ruan(已加)
CREATE TABLE `charge_log` (
 `order_id` bigint(20) NOT NULL COMMENT '订单号',
 `company_id` bigint(20) NOT NULL COMMENT '企业id',
 `points` int(11) NOT NULL COMMENT '充值点数',
 `channel` tinyint(4) NOT NULL COMMENT '充值渠道',
 `status` tinyint(4) NOT NULL COMMENT '订单状态(1-充值成功 0-已下订单)',
 `create_time` int(11) NOT NULL COMMENT '充值时间',
 PRIMARY KEY (`order_id`),
 KEY `channel` (`channel`),
 KEY `company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值流水';

-- 提现流水    2015.10.27   ruan(已加)
CREATE TABLE `checkout_log` (
 `order_id` bigint(20) NOT NULL COMMENT '订单号',
 `user_id` bigint(20) NOT NULL COMMENT '用户id',
 `points` int(11) NOT NULL COMMENT '提现G点',
 `type` int(11) NOT NULL COMMENT '提现类型',
 `status` tinyint(4) NOT NULL COMMENT '订单状态(1-提现成功 0-已下订单)',
 `create_time` int(11) NOT NULL COMMENT '提现时间',
 PRIMARY KEY (`order_id`),
 KEY `type` (`type`),
 KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提现流水';

--消息内容改用JSON保存		2015.11.03		luo(已加)
ALTER TABLE  `gu`.`company_message` ADD  `body` TEXT NOT NULL COMMENT  '消息内容(JSON)';
ALTER TABLE  `gu`.`user_message` ADD  `body` TEXT NOT NULL COMMENT  '消息内容(JSON)';

--奖励线,创意超过X个才进入奖励机制(现在保存X=15)		2015.11.03		luo(已加)
INSERT INTO  `gu_config`.`bonus_percent_config` (`id` ,`name` ,`percent` ,`points_max` ,`remark`)VALUES (NULL ,  'bonusLine',  '0',  '15',  '奖励线');

-- 创意圈_作品表		2015.10.27		luo (已加)
-- 表的结构 `gu`.`market_opus`
CREATE TABLE IF NOT EXISTS `gu`.`market_opus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `create_time` int(11) NOT NULL COMMENT '添加时间',
  `title` varchar(80) NOT NULL COMMENT '题目',
  `content` varchar(1000) NOT NULL COMMENT '内容',
  `praise` int(11) NOT NULL DEFAULT '0' COMMENT '赞',
  `comment_num` int(11) NOT NULL DEFAULT '0' COMMENT '评论统计',
  `image1` varchar(500) NOT NULL COMMENT '图片1',
  `image2` varchar(500) NOT NULL COMMENT '图片2',
  `image3` varchar(500) NOT NULL COMMENT '图片3',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='创意圈_作品表';

-- 创意圈_作品评论表		2015.10.27		luo(已加)
-- 表的结构 `gu`.`market_opus_comment`
CREATE TABLE IF NOT EXISTS `gu`.`market_opus_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `market_opus_id` bigint(20) NOT NULL COMMENT '作品id',
  `create_time` int(11) NOT NULL COMMENT '添加时间',
  `content` varchar(200) NOT NULL COMMENT '内容',
  `reply_user_id` int(11) NOT NULL COMMENT '回复的用户id',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `market_opus_id` (`market_opus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='创意圈_作品评论表';

-- 创意圈_作品点赞表		2015.10.27		luo  (已加)
-- 表的结构 `gu`.`market_opus_praise`
CREATE TABLE IF NOT EXISTS `gu`.`market_opus_praise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `market_opus_id` bigint(20) NOT NULL COMMENT '作品id',
  `create_time` int(11) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `market_opus_id` (`market_opus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='创意圈_作品点赞表';

-- 提现最低G点限制   2015.10.27      ruan  (已加)
ALTER TABLE  `gu_config`.`system` ADD  `min_checkout_points` INT NOT NULL COMMENT  '最小提现G点' AFTER  `sms_mobiles`;

-- 推送在user表加的字段   2015.10.29    ruan (已加)
ALTER TABLE  `gu`.`user` ADD  `platform` INT NOT NULL DEFAULT  '2' COMMENT  '平台：1-安卓，2-IOS，3-IPAD' AFTER  `weibo`, ADD  `push_key` varchar(500) NOT NULL DEFAULT '' COMMENT  '推送标识' AFTER  `platform`;

-- 创意表保存评论数量		2015.10.27		luo (已加)
ALTER TABLE  `gu`.`idea` ADD  `comment_num` INT NOT NULL DEFAULT  '0' COMMENT  '评论总数' AFTER  `praise`;

-- 微信表加索引   2015.10.27    ruan(已加)
ALTER TABLE  `gu`.`wechat_open` ADD INDEX (  `user_id` );
ALTER TABLE  `gu`.`wechat_open` ADD INDEX (  `open_id` );

-- 2015.10.23		luo(已加)
ALTER TABLE `gu`.`wechat_open` ADD `create_time` INT NOT NULL; 

-- 最近7天平均日使用时长     2015.10.26     ruan(已加)
CREATE TABLE `gu_log`.`use_average` (
 `start_date` int(11) NOT NULL COMMENT '开始日期',
 `end_date` int(11) NOT NULL COMMENT '结束日期',
 `user_num` int(11) NOT NULL COMMENT '用户人数',
 `duration` int(11) NOT NULL COMMENT '使用时长(单位：秒)',
 PRIMARY KEY (`start_date`,`end_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app人均使用时长';


-- 最近7天活跃用户统计     2015.10.23      ruan(已加)
CREATE TABLE `gu_log`.`recent7_active` (
 `start_date` int(11) NOT NULL COMMENT '开始日期',
 `end_date` int(11) NOT NULL COMMENT '结束日期',
 `num` int(11) NOT NULL COMMENT '活跃数量',
 UNIQUE KEY `start_date` (`start_date`,`end_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='最近7天活跃用户';

-- 最近14天活跃用户统计     2015.10.23      ruan(已加)
CREATE TABLE `gu_log`.`recent14_active` (
 `start_date` int(11) NOT NULL COMMENT '开始日期',
 `end_date` int(11) NOT NULL COMMENT '结束日期',
 `num` int(11) NOT NULL COMMENT '活跃数量',
 UNIQUE KEY `start_date` (`start_date`,`end_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='最近14天活跃用户';

-- 用户日活跃，企业日活跃加一个今天总活跃的字段      2015.10.23      ruan(已加)
ALTER TABLE  `gu_log`.`daily_active_user` ADD  `total` INT NOT NULL DEFAULT 0 COMMENT  '今天总活跃';
ALTER TABLE  `gu_log`.`daily_active_company` ADD  `total` INT NOT NULL DEFAULT 0 COMMENT  '今天总活跃';

-- 日活跃率统计    2015.10.23     ruan(已加)
CREATE TABLE `gu_log`.`active_user` (
 `date` int(11) NOT NULL COMMENT '日期',
 `reg_num` int(11) NOT NULL COMMENT '截至今天总注册数',
 `active` int(11) NOT NULL COMMENT '今天的活跃用户数',
 PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日活跃率统计';