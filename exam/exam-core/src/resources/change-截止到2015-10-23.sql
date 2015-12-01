

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 企业表新增关注用户数    2015.10.22       ruan(已加)
ALTER TABLE  `gu`.`company` ADD  `focus_user_num` INT NOT NULL COMMENT  '关注用户数' AFTER  `collect_num`;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 专案统计 (这个要按照从上到下的顺序执行)    2015.10.21       ruan(已加)
RENAME TABLE  `gu_log`.`case_count_focus` TO  `gu_log`.`case_count_takepartin` ;
RENAME TABLE  `gu_log`.`case_count_total` TO  `gu_log`.`case_count_takepartin_total` ;

CREATE TABLE IF NOT EXISTS `gu_log`.`case_count_focus` (
  `date` int(11) NOT NULL COMMENT '日期',
  `case_id` bigint(20) NOT NULL COMMENT '专案id',
  `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
  `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
  `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
  `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
  `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
  `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
  `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
  `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
  `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
  `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
  `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
  `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
  `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
  `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
  `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
  `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
  `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
  `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
  `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
  `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
  `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
  `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
  `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
  `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
  UNIQUE KEY `count_focus` (`case_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案关注统计';

CREATE TABLE IF NOT EXISTS `gu_log`.`case_count_focus_total` (
  `date` int(11) NOT NULL COMMENT '日期',
  `case_id` bigint(20) NOT NULL COMMENT '专案id',
  `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
  `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
  `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
  `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
  `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
  `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
  `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
  `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
  `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
  `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
  `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
  `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
  `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
  `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
  `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
  `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
  `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
  `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
  `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
  `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
  `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
  `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
  `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
  `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
  `total` int(11) NOT NULL DEFAULT '0' COMMENT '关注总数',
  UNIQUE KEY `count_focus` (`case_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案关注总数统计';

CREATE TABLE IF NOT EXISTS `gu_log`.`case_count_unfocus` (
  `date` int(11) NOT NULL COMMENT '日期',
  `case_id` bigint(20) NOT NULL COMMENT '专案id',
  `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
  `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
  `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
  `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
  `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
  `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
  `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
  `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
  `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
  `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
  `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
  `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
  `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
  `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
  `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
  `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
  `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
  `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
  `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
  `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
  `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
  `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
  `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
  `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
  UNIQUE KEY `count_focus` (`case_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案取消关注统计';

CREATE TABLE IF NOT EXISTS `gu_log`.`case_count_idea` (
  `date` int(11) NOT NULL COMMENT '日期',
  `case_id` bigint(20) NOT NULL COMMENT '专案id',
  `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
  `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
  `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
  `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
  `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
  `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
  `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
  `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
  `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
  `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
  `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
  `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
  `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
  `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
  `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
  `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
  `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
  `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
  `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
  `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
  `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
  `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
  `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
  `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
  UNIQUE KEY `count_focus` (`case_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案新增点子统计';


CREATE TABLE IF NOT EXISTS `gu_log`.`case_count_idea_total` (
  `date` int(11) NOT NULL COMMENT '日期',
  `case_id` bigint(20) NOT NULL COMMENT '专案id',
  `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
  `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
  `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
  `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
  `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
  `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
  `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
  `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
  `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
  `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
  `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
  `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
  `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
  `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
  `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
  `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
  `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
  `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
  `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
  `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
  `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
  `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
  `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
  `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
  `total` int(11) NOT NULL DEFAULT '0' COMMENT '点子总数',
  UNIQUE KEY `count_focus` (`case_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案点子总数统计';

CREATE TABLE IF NOT EXISTS `gu_log`.`company_count_idea` (
  `date` int(11) NOT NULL COMMENT '日期',
  `company_id` bigint(20) NOT NULL COMMENT '专案id',
  `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
  `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
  `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
  `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
  `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
  `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
  `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
  `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
  `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
  `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
  `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
  `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
  `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
  `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
  `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
  `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
  `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
  `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
  `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
  `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
  `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
  `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
  `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
  `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
  UNIQUE KEY `count_focus` (`company_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业新增点子统计';

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 企业粉丝统计表   2015 10.20   ruan (已加)
CREATE TABLE `gu_log`.`company_count_age` (
  `company_id` bigint(20) NOT NULL COMMENT '企业id',
  `since00` int(11) NOT NULL DEFAULT '0' COMMENT '00后',
  `since95` int(11) NOT NULL DEFAULT '0' COMMENT '95后',
  `since90` int(11) NOT NULL DEFAULT '0' COMMENT '90后',
  `since85` int(11) NOT NULL DEFAULT '0' COMMENT '85后',
  `since80` int(11) NOT NULL DEFAULT '0' COMMENT '80后',
  `since70` int(11) NOT NULL DEFAULT '0' COMMENT '70后',
  `before70` int(11) NOT NULL DEFAULT '0' COMMENT '70前',
  UNIQUE KEY `company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业年龄统计';
CREATE TABLE `gu_log`.`company_count_city` (
  `company_id` bigint(20) NOT NULL COMMENT '企业id',
  `province_id` bigint(20) NOT NULL COMMENT '省份id',
  `city_id` bigint(20) NOT NULL COMMENT '城市id',
  `count` int(11) NOT NULL COMMENT '总数',
  UNIQUE KEY `company_id` (`company_id`,`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业城市统计';
CREATE TABLE `gu_log`.`company_count_focus` (
  `date` int(11) NOT NULL COMMENT '日期',
  `company_id` bigint(20) NOT NULL COMMENT '企业id',
  `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
  `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
  `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
  `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
  `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
  `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
  `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
  `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
  `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
  `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
  `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
  `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
  `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
  `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
  `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
  `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
  `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
  `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
  `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
  `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
  `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
  `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
  `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
  `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
  UNIQUE KEY `count_focus` (`company_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业关注统计';
CREATE TABLE `gu_log`.`company_count_focus_total` (
  `date` int(11) NOT NULL COMMENT '日期',
  `company_id` bigint(20) NOT NULL COMMENT '企业id',
  `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
  `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
  `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
  `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
  `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
  `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
  `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
  `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
  `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
  `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
  `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
  `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
  `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
  `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
  `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
  `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
  `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
  `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
  `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
  `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
  `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
  `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
  `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
  `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
  `total` int(11) NOT NULL DEFAULT '0' COMMENT '关注总数',
  UNIQUE KEY `count_focus` (`company_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业关注总数统计';
CREATE TABLE `gu_log`.`company_count_prove` (
  `company_id` bigint(20) NOT NULL COMMENT '企业id',
  `prove` int(11) NOT NULL DEFAULT '0' COMMENT '已认证',
  `un_prove` int(11) NOT NULL DEFAULT '0' COMMENT '未认证',
  UNIQUE KEY `company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业实名认证统计';
CREATE TABLE `gu_log`.`company_count_province` (
  `company_id` bigint(20) NOT NULL COMMENT '企业id',
  `province_id` bigint(20) NOT NULL COMMENT '省份id',
  `count` int(11) NOT NULL COMMENT '总数',
  UNIQUE KEY `company_id` (`company_id`,`province_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业省份统计';
CREATE TABLE `gu_log`.`company_count_sex` (
  `company_id` bigint(20) NOT NULL COMMENT '企业id',
  `boy` int(11) NOT NULL DEFAULT '0' COMMENT '男',
  `girl` int(11) NOT NULL DEFAULT '0' COMMENT '女',
  UNIQUE KEY `company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业性别统计';
CREATE TABLE `gu_log`.`company_count_unfocus` (
  `date` int(11) NOT NULL COMMENT '日期',
  `company_id` bigint(20) NOT NULL COMMENT '企业id',
  `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
  `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
  `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
  `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
  `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
  `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
  `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
  `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
  `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
  `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
  `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
  `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
  `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
  `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
  `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
  `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
  `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
  `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
  `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
  `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
  `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
  `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
  `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
  `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
  UNIQUE KEY `count_unfocus` (`company_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业取消关注统计';


-- 企业消息数量     2015.10.20     ruan (已加)
CREATE TABLE `gu`.`company_message_num` (
 `company_id` bigint(20) NOT NULL COMMENT '企业id',
 `message_type` int(11) NOT NULL COMMENT '消息大类型',
 `message_sign` int(11) NOT NULL COMMENT '消息小类型',
 `num` int(11) NOT NULL COMMENT '数量',
 KEY `company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业消息数量';


-- 创意百分比要有多位小数,有10,000G点时,0.001%也能分到1点  (已加)
ALTER TABLE  `gu_log`.`case_finish_bonus_prize_user_log` CHANGE  `idea_percent`  `idea_percent` DECIMAL( 11, 8 ) NOT NULL COMMENT  '创意百分比(专案总赞数/创意获赞数)';

-- 按用户合并分红记录表		2015.10.20		luo (已加)
-- 数据库: `gu_log`
-- 表的结构`case_finish_bonus_user_log`
CREATE TABLE IF NOT EXISTS  `gu_log`.`case_finish_bonus_user_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `case_id` bigint(20) NOT NULL COMMENT '专案id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `bonus_points` int(11) NOT NULL COMMENT '分红点数',
  `create_time` int(11) NOT NULL COMMENT '添加时间',
  `bonus_remark` varchar(100) NOT NULL COMMENT '分红备注',
  PRIMARY KEY (`id`),
  KEY `case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='按用户合并分红记录表';

-- 专案关注总数统计     2015.10.19     ruan (已加)
CREATE TABLE `gu_log`.`case_count_focus_total` (
 `date` int(11) NOT NULL COMMENT '日期',
 `case_id` bigint(20) NOT NULL COMMENT '专案id',
 `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
 `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
 `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
 `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
 `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
 `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
 `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
 `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
 `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
 `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
 `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
 `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
 `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
 `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
 `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
 `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
 `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
 `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
 `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
 `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
 `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
 `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
 `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
 `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
 `total` int(11) NOT NULL DEFAULT '0' COMMENT '关注总数',
 UNIQUE KEY `count_focus` (`case_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案关注总数统计';


-- 城市统计加省份id     2015.10.19     ruan (已加)
ALTER TABLE  `gu_log`.`case_count_city` add `province_id` BIGINT( 20 ) NOT NULL COMMENT  '省份id' after `case_id`;

-- G点支出列表     2015.10.19     ruan (已加)
CREATE TABLE `gu`.`company_expense` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
 `company_id` bigint(11) NOT NULL COMMENT '企业id',
 `case_id` bigint(20) NOT NULL COMMENT '专案id',
 `points` int(11) NOT NULL COMMENT '费用',
 `create_time` int(11) NOT NULL COMMENT '记录时间',
 PRIMARY KEY (`id`),
 KEY `company_id` (`company_id`),
 KEY `case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='G点支出列表';

-- 回复的用户id     2015.10.19     ruan (已加)
ALTER TABLE  `gu`.`idea_comment` add  `reply_user_id`  BIGINT( 20 ) NOT NULL COMMENT  '回复的用户id';

-- 企业关注用户     2015.10.16     ruan (已加)
CREATE TABLE `gu`.`company_focus` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
 `company_id` bigint(20) NOT NULL COMMENT '企业id',
 `user_id` bigint(20) NOT NULL COMMENT '用户id',
 `create_time` int(11) NOT NULL COMMENT '关注时间',
 PRIMARY KEY (`id`),
 KEY `company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业关注用户';

-- 企业、用户，增加最后登录时间   2015.10.14     ruan (已加)
ALTER TABLE  `gu`.`company` ADD  `last_login_time` INT NOT NULL COMMENT  '最后登录时间' AFTER  `password`;
ALTER TABLE  `gu`.`user` ADD  `last_login_time` INT NOT NULL COMMENT  '最后登录时间' AFTER  `password`;

-- 增加企业收藏创意表          2015.10.14      ruan (已加)
CREATE TABLE `gu`.`collect_idea` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
 `company_id` bigint(20) NOT NULL COMMENT '企业id',
 `idea_id` bigint(20) NOT NULL COMMENT '创意id',
 `create_time` int(11) NOT NULL COMMENT '收藏时间',
 PRIMARY KEY (`id`),
 KEY `company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业收藏创意表';

-- 增加企业图片字段          2015.10.14      ruan (已加)
ALTER TABLE  `gu`.`company` ADD  `pic` VARCHAR( 500 ) NOT NULL COMMENT  '企业图片' AFTER  `logo_image`;
ALTER TABLE  `gu`.`company` ADD  `collect_num` INT NOT NULL COMMENT  '专案收藏数' AFTER  `fans_num`;

-- 使用时长统计要用到的表    2015.10.13      ruan (已加)
CREATE TABLE `gu_log`.`use_distribution` (
  `date` int(11) NOT NULL COMMENT '日期',
  `less_than_1m` int(11) NOT NULL DEFAULT '0' COMMENT '小于1分钟',
  `range_1m_5m` int(11) NOT NULL DEFAULT '0' COMMENT '1-5分钟',
  `range_6m_10m` int(11) NOT NULL DEFAULT '0' COMMENT '6-10分钟',
  `range_10m_30m` int(11) NOT NULL DEFAULT '0' COMMENT '10-30分钟',
  `range_30m_60m` int(11) NOT NULL DEFAULT '0' COMMENT '30-60分钟',
  `range_1h_plus` int(11) NOT NULL DEFAULT '0' COMMENT '大于1小时',
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='使用时长分布';
CREATE TABLE  `gu_log`.`use_distribution_log` (
  `date` int(11) NOT NULL COMMENT '日期',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `duration` int(11) NOT NULL COMMENT '使用时长(单位：秒)',
  UNIQUE KEY `date` (`date`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='使用时长流水';

-- 企业回复表	2015.10.7		luo
-- 数据库: `gu`
-- 表的结构 `idea_company_comment` (已加)
	
CREATE TABLE `gu`.`idea_company_comment` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
 `company_id` bigint(20) NOT NULL COMMENT '企业id',
 `user_id` bigint(20) NOT NULL COMMENT '用户id',
 `idea_id` bigint(20) NOT NULL COMMENT '创意id',
 `create_time` int(11) NOT NULL COMMENT '回复时间',
 `content` varchar(200) NOT NULL COMMENT '内容',
 `action` tinyint(4) NOT NULL COMMENT '动作(1-我回复了xx 2-xx回复了我)',
 PRIMARY KEY (`id`),
 KEY `idea_id` (`idea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业回复表';

-- 企业消息表	2015.10.7		luo
-- 数据库: `gu`
-- 表的结构 `company_message` (已加)
CREATE TABLE  `gu`.`company_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(11) NOT NULL COMMENT '类型',
  `sign` int(11) NOT NULL COMMENT '消息号码',
  `company_id` bigint(20) NOT NULL COMMENT '接收者id',
  `sender_id` bigint(20) NOT NULL COMMENT '发送者id',
  `create_time` int(11) NOT NULL COMMENT '添加时间',
  `data` blob NOT NULL COMMENT '消息内容',
  PRIMARY KEY (`id`),  
  KEY `type` (`type`),
  KEY `sign` (`sign`),
  KEY `company_id` (`company_id`),
  KEY `sender_id` (`sender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业消息表';

-- 用户消息表	2015.10.7		luo
-- 数据库: `gu`
-- 表的结构 `user_message` (已加)
CREATE TABLE  `gu`.`user_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(11) NOT NULL COMMENT '类型',
  `sign` int(11) NOT NULL COMMENT '消息号码',
  `user_id` bigint(20) NOT NULL COMMENT '接收者id',
  `sender_id` bigint(20) NOT NULL COMMENT '发送者id',
  `create_time` int(11) NOT NULL COMMENT '添加时间',
  `data` blob NOT NULL COMMENT '消息内容',
  PRIMARY KEY (`id`),  
  KEY `type` (`type`),
  KEY `sign` (`sign`),
  KEY `user_id` (`user_id`),
  KEY `sender_id` (`sender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户消息表';


-- 添加系统参数配置表     2015.10.13     ruan (已加)
CREATE TABLE `gu_config`.`system` (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
 `keywords` varchar(300) NOT NULL COMMENT '网站关键字',
 `description` varchar(300) NOT NULL COMMENT '网站描述',
 `gb_exchange_rate` int(11) NOT NULL COMMENT 'G币汇率',
 `company_pub_case_per_day` int(11) NOT NULL COMMENT '企业每天可以发的专案数',
 `app_version` int(11) NOT NULL COMMENT 'app版本',
 `sms_mobiles` varchar(1000) NOT NULL COMMENT '接收短信的手机(;分割)',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数配置';
INSERT INTO `gu_config`.`system` (`keywords`, `description`, `gb_exchange_rate`, `company_pub_case_per_day`,`app_version`,`sms_mobiles`) VALUES ('共做室，一个协作、开放、分享的高质量创意讨论社区。', '共做室，一个协作、开放、分享的高质量创意讨论社区。', 100, 3, 1000,'');


-- 添加专案数量字段     2015.10.09     ruan (已加)
ALTER TABLE  `gu`.`company` ADD  `all_case_num` INT NOT NULL DEFAULT  '0' COMMENT  '全部专案数量' AFTER  `points`;
ALTER TABLE  `gu`.`company` ADD  `going_case_num` INT NOT NULL DEFAULT  '0' COMMENT  '进行专案数量' AFTER  `all_case_num`;
ALTER TABLE  `gu`.`company` ADD  `over_case_num` INT NOT NULL DEFAULT  '0' COMMENT  '完结专案数量' AFTER  `going_case_num`;
ALTER TABLE  `gu`.`company` ADD  `fans_num` INT NOT NULL DEFAULT  '0' COMMENT  '粉丝数量' AFTER  `over_case_num`;

-- 日启动次数流水      2015.10.09     ruan (已加)
CREATE TABLE `gu_log`.`start_distribution_log` (
 `date` int(11) NOT NULL COMMENT '日期',
 `user_id` bigint(20) NOT NULL COMMENT '用户id',
 `start_times` int(11) NOT NULL COMMENT '启动次数',
 UNIQUE KEY `date` (`date`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日启动次数流水';

-- 日启动次数分布      2015.10.09     ruan (已加)
CREATE TABLE `gu_log`.`start_distribution` (
 `date` int(11) NOT NULL COMMENT '日期',
 `range1_2` int(11) NOT NULL DEFAULT '0' COMMENT '1-2次',
 `range3_5` int(11) NOT NULL DEFAULT '0' COMMENT '3-5次',
 `range6_9` int(11) NOT NULL DEFAULT '0' COMMENT '6-9次',
 `range10_19` int(11) NOT NULL DEFAULT '0' COMMENT '10-19次',
 `range20_49` int(11) NOT NULL DEFAULT '0' COMMENT '20-49次',
 `range50plus` int(11) NOT NULL DEFAULT '0' COMMENT '50+次',
 PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日启动次数分布';

-- user_prove添加是否实名认证		2015.10.9	luo (已加)
ALTER TABLE `gu`.`user_prove` ADD `is_prove` INT NOT NULL COMMENT '是否实名认证',
ADD INDEX ( `is_prove` );
ALTER TABLE `user_prove` ADD `create_time` INT NOT NULL COMMENT '添加时间';
ALTER TABLE `user_prove` ADD `check_time` INT NOT NULL COMMENT '审核时间';

-- 启动人数统计      2015.10.08     ruan (已加)
CREATE TABLE `gu_log`.`start_user` (
 `date` int(11) NOT NULL COMMENT '日期',
 `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
 `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
 `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
 `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
 `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
 `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
 `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
 `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
 `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
 `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
 `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
 `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
 `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
 `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
 `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
 `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
 `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
 `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
 `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
 `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
 `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
 `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
 `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
 `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
 `total` int(11) NOT NULL DEFAULT '0' COMMENT '今天启动的人数',
 PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='启动人数统计';

-- 企业所有专案的标签      2015.10.08     ruan (已加)
CREATE TABLE `gu`.`company_case_tag` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
 `company_id` bigint(20) NOT NULL COMMENT '企业id',
 `type_id` bigint(20) NOT NULL COMMENT '标签id(自定义的+100000000)',
 PRIMARY KEY (`id`),
 UNIQUE KEY `company_id` (`company_id`,`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业所有专案的标签';

-- 专案关注统计      2015.10.08     ruan (已加)
CREATE TABLE `gu_log`.`case_count_focus` (
 `date` int(11) NOT NULL COMMENT '日期',
 `case_id` bigint(20) NOT NULL COMMENT '专案id',
 `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
 `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
 `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
 `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
 `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
 `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
 `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
 `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
 `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
 `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
 `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
 `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
 `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
 `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
 `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
 `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
 `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
 `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
 `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
 `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
 `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
 `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
 `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
 `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
 UNIQUE KEY `count_focus` (`case_id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案关注统计';

-- 参与专案年龄统计     2015.10.07     ruan (已加)
CREATE TABLE `gu_log`.`case_count_age` (
 `case_id` bigint(20) NOT NULL COMMENT '专案id',
 `since00` int(11) NOT NULL DEFAULT '0' COMMENT '00后',
 `since95` int(11) NOT NULL DEFAULT '0' COMMENT '95后',
 `since90` int(11) NOT NULL DEFAULT '0' COMMENT '90后',
 `since85` int(11) NOT NULL DEFAULT '0' COMMENT '85后',
 `since80` int(11) NOT NULL DEFAULT '0' COMMENT '80后',
 `since70` int(11) NOT NULL DEFAULT '0' COMMENT '70后',
 `before70` int(11) NOT NULL DEFAULT '0' COMMENT '70前',
 UNIQUE KEY `case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参与专案年龄统计';

-- 优化表结构  2015.10.07     ruan (已加)
ALTER TABLE `gu_log`.`case_count_sex` DROP `id`;
ALTER TABLE `gu_log`.`case_count_province` DROP `id`;
ALTER TABLE `gu_log`.`case_count_city` DROP `id`;

-- 参与专案实名认证统计    2015.10.07     ruan (已加)
CREATE TABLE `gu_log`.`case_count_prove` (
 `case_id` bigint(20) NOT NULL COMMENT '专案id',
 `prove` int(11) NOT NULL DEFAULT '0' COMMENT '已认证',
 `un_prove` int(11) NOT NULL DEFAULT '0' COMMENT '未认证',
 UNIQUE KEY `case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参与专案实名认证统计';

-- 一句话介绍公司品牌    2015.10.07     ruan (已加)
ALTER TABLE  `gu`.`company` ADD  `introduce` VARCHAR( 100 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT  '' COMMENT  '一句话介绍公司品牌' AFTER  `description`

-- 控制专案是否显示    2015.10.07     ruan (已加)
ALTER TABLE  `gu`.`case` ADD  `is_show` TINYINT NOT NULL DEFAULT  '1' COMMENT  '是否显示(1-是 0-否)' AFTER  `type`;

-- 用户设置表		2015.9.25		luo (已加)
-- 数据库: `gu`
-- 表的结构 `user_setting`
CREATE TABLE  `gu`.`user_setting` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `all_visit` tinyint(4) NOT NULL COMMENT '对所有人可见',
  `friend_visit` tinyint(4) NOT NULL COMMENT '对好友可见',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户设置表';

﻿-- 专案支持上传视频    ruan     2015.10.05 (已加)
ALTER TABLE  `gu`.`case` ADD  `video` VARCHAR( 500 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT  '' COMMENT  '视频地址' AFTER  `image5`;

-- 企业自定义专案标签    ruan    2015.09.30 (已加)
CREATE TABLE `gu`.`company_define_case_tag` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
 `company_id` bigint(20) NOT NULL COMMENT '企业id',
 `name` varchar(100) NOT NULL COMMENT '标签名',
 `case_num` int(11) NOT NULL COMMENT '有多少个专案使用这个标签',
 PRIMARY KEY (`id`),
 UNIQUE KEY `company_id_2` (`company_id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业自定义专案标签';

-- 最新专案列表，把end_time做索引，专案类型支持对标签的，所以要改成字符串     ruan    2015.09.30  (已加)
ALTER TABLE  `gu`.`case` ADD INDEX (  `end_time` );
ALTER TABLE  `gu`.`case` change `type`  `type` VARCHAR( 100 ) NOT NULL COMMENT  '专案类型' AFTER  `description`;

﻿-- 新增分享统计表    ruan     2015.09.29   (已加)
CREATE TABLE `gu_log`.`share_statistics` (
 `date` int(11) NOT NULL COMMENT '日期',
 `platform` int(11) NOT NULL COMMENT '第三方平台',
 `share_times` int(11) NOT NULL COMMENT '分享次数',
 `share_clicks` int(11) NOT NULL COMMENT '分享点击数',
 `down_nums` int(11) NOT NULL COMMENT '下载数',
 UNIQUE KEY `date` (`date`,`platform`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分享统计';

-- 删除无用表  pay_percent_type   ruan  2015.09.28 (已加)
DROP TABLE `gu_config`.`pay_percent_type`;

-- 新增表 `case_points` 专案金额配置    ruan  2015.09.28 (已加)
CREATE TABLE  `gu_config`.`case_points` (
  `points` int(11) NOT NULL COMMENT '金额',
  `description` varchar(100) NOT NULL COMMENT '描述',
  PRIMARY KEY (`points`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案金额配置';

INSERT INTO `gu_config`.`case_points` (`points`, `description`) VALUES (30000, '30,000'),(100000, '100,000');

-- 新增表  `case_expire`  专案有效天数配置   ruan  2015.09.28 (已加)
CREATE TABLE  `gu_config`.`case_expire` (
  `seconds` int(11) NOT NULL COMMENT '失效时间(单位：秒)',
  `description` varchar(100) NOT NULL COMMENT '描述',
  PRIMARY KEY (`seconds`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案有效天数配置';
INSERT INTO `case_expire` (`seconds`, `description`) VALUES (259200, '3天'),(691200, '8天');

-- 控制专案是否显示    2015.10.07     ruan (已加)
ALTER TABLE  `gu`.`case` ADD  `is_show` TINYINT NOT NULL DEFAULT  '1' COMMENT  '是否显示(1-是 0-否)' AFTER  `type`;

-- 是否实名认证		2015.9.24		luo  (已加)
ALTER TABLE `gu`.`user` ADD `is_prove` TINYINT( 4 ) NOT NULL DEFAULT '0' COMMENT '是否实名认证';

-- 用户实名认证信息表		2015.9.24		luo (已加)
-- 数据库: `gu`
-- 表的结构 `gu`.`user_prove`
CREATE TABLE  `gu`.`user_prove` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `company` varchar(120) NOT NULL COMMENT '公司',
  `job` varchar(60) NOT NULL COMMENT '职位',
  `name` varchar(60) NOT NULL COMMENT '真实姓名',
  `identity_card` varchar(500) NOT NULL COMMENT '身份证图片',
  `work_card` varchar(500) NOT NULL COMMENT '名片/工牌',
  `other_card` varchar(500) NOT NULL COMMENT '其他证件图片',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户实名认证信息表';

-- 用户举报表		2015.9.24		luo (已加)
-- 数据库: `gu_log`
-- 表的结构 `user_accusation`
CREATE TABLE  `gu_log`.`user_accusation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '举报人id',
  `type` tinyint(4) NOT NULL COMMENT '类型号码',
  `type_id` bigint(20) NOT NULL COMMENT '类型id',
  `create_time` int(11) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户举报表' ;

-- 新增企业统计  2015.09.24   ruan  (已加)
CREATE TABLE `gu_log`.`new_company` (
 `date` int(11) NOT NULL COMMENT '日期',
 `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
 `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
 `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
 `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
 `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
 `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
 `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
 `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
 `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
 `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
 `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
 `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
 `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
 `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
 `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
 `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
 `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
 `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
 `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
 `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
 `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
 `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
 `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
 `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
 PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新增用户统计';


-- 企业日活跃量统计  2015.09.24   ruan (已加)
CREATE TABLE `gu_log`.`daily_active_company` (
 `date` int(11) NOT NULL COMMENT '日期',
 `h1` int(11) NOT NULL DEFAULT '0' COMMENT '0点-1点',
 `h2` int(11) NOT NULL DEFAULT '0' COMMENT '1点-2点',
 `h3` int(11) NOT NULL DEFAULT '0' COMMENT '2点-3点',
 `h4` int(11) NOT NULL DEFAULT '0' COMMENT '3点-4点',
 `h5` int(11) NOT NULL DEFAULT '0' COMMENT '4点-5点',
 `h6` int(11) NOT NULL DEFAULT '0' COMMENT '5点-6点',
 `h7` int(11) NOT NULL DEFAULT '0' COMMENT '6点-7点',
 `h8` int(11) NOT NULL DEFAULT '0' COMMENT '7点-8点',
 `h9` int(11) NOT NULL DEFAULT '0' COMMENT '8点-9点',
 `h10` int(11) NOT NULL DEFAULT '0' COMMENT '9点-10点',
 `h11` int(11) NOT NULL DEFAULT '0' COMMENT '10点-11点',
 `h12` int(11) NOT NULL DEFAULT '0' COMMENT '11点-12点',
 `h13` int(11) NOT NULL DEFAULT '0' COMMENT '12点-13点',
 `h14` int(11) NOT NULL DEFAULT '0' COMMENT '13点-14点',
 `h15` int(11) NOT NULL DEFAULT '0' COMMENT '14点-15点',
 `h16` int(11) NOT NULL DEFAULT '0' COMMENT '15点-16点',
 `h17` int(11) NOT NULL DEFAULT '0' COMMENT '16点-17点',
 `h18` int(11) NOT NULL DEFAULT '0' COMMENT '17点-18点',
 `h19` int(11) NOT NULL DEFAULT '0' COMMENT '18点-19点',
 `h20` int(11) NOT NULL DEFAULT '0' COMMENT '19点-20点',
 `h21` int(11) NOT NULL DEFAULT '0' COMMENT '20点-21点',
 `h22` int(11) NOT NULL DEFAULT '0' COMMENT '21点-22点',
 `h23` int(11) NOT NULL DEFAULT '0' COMMENT '22点-23点',
 `h24` int(11) NOT NULL DEFAULT '0' COMMENT '23点-24点',
 PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业日活跃量统计';


-- 一句话亮身份例子	2015.09.23		luo (已加)
-- 数据库: `gu_config`
-- 表的结构 `user_identity_config`
CREATE TABLE  `gu_config`.`user_identity_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(20) NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一句话亮身份例子'  ;

-- 专案结算分红日志添加企业id字段		2015.09.22		luo  (已加)
ALTER TABLE `gu_log`.`case_finish_bonus_log` ADD `company_id` BIGINT NOT NULL COMMENT '企业id' AFTER `case_id`;
ALTER TABLE `gu_log`.`case_finish_bonus_log` ADD INDEX ( `company_id` );
-- 企业日留存率	2015.09.18	luo (已加)
-- 数据库: `gu_log`
-- 表的结构 `company_day_remain`
CREATE TABLE  `gu_log`.`company_day_remain` (
  `date` int(11) NOT NULL,
  `reg_num` int(11) NOT NULL,
  `d1` int(11) NOT NULL DEFAULT '0',
  `d2` int(11) NOT NULL DEFAULT '0',
  `d3` int(11) NOT NULL DEFAULT '0',
  `d4` int(11) NOT NULL DEFAULT '0',
  `d5` int(11) NOT NULL DEFAULT '0',
  `d6` int(11) NOT NULL DEFAULT '0',
  `d7` int(11) NOT NULL DEFAULT '0',
  `d8` int(11) NOT NULL DEFAULT '0',
  `d9` int(11) NOT NULL DEFAULT '0',
  `d10` int(11) NOT NULL DEFAULT '0',
  `d11` int(11) NOT NULL DEFAULT '0',
  `d12` int(11) NOT NULL DEFAULT '0',
  `d13` int(11) NOT NULL DEFAULT '0',
  `d14` int(11) NOT NULL DEFAULT '0',
  `d15` int(11) NOT NULL DEFAULT '0',
  `d16` int(11) NOT NULL DEFAULT '0',
  `d17` int(11) NOT NULL DEFAULT '0',
  `d18` int(11) NOT NULL DEFAULT '0',
  `d19` int(11) NOT NULL DEFAULT '0',
  `d20` int(11) NOT NULL DEFAULT '0',
  `d21` int(11) NOT NULL DEFAULT '0',
  `d22` int(11) NOT NULL DEFAULT '0',
  `d23` int(11) NOT NULL DEFAULT '0',
  `d24` int(11) NOT NULL DEFAULT '0',
  `d25` int(11) NOT NULL DEFAULT '0',
  `d26` int(11) NOT NULL DEFAULT '0',
  `d27` int(11) NOT NULL DEFAULT '0',
  `d28` int(11) NOT NULL DEFAULT '0',
  `d29` int(11) NOT NULL DEFAULT '0',
  `d30` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业留存率';

--企业登录时间表(30天内注册的)	2015.09.18	luo (已加)
-- 数据库: `gu_log`
-- 表的结构 `company_logintime_log`
CREATE TABLE  `gu_log`.`company_logintime_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` bigint(20) NOT NULL COMMENT '企业id',
  `reg_time` int(11) NOT NULL COMMENT '注册时间',
  `login_time` int(11) NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='企业登录时间表(30天内注册的)' ;

-- 用户意见反馈表		2015.9.17	luo (已加)
-- 数据库: `gu_log`
-- 表的结构 `user_feedback`
CREATE TABLE  `gu_log`.`user_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `feedback` varchar(1000) NOT NULL COMMENT '意见反馈',
  `create_time` int(11) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
);

-- 预留手机号送G点   gu库  2015.9.16   ruan (已加)
CREATE TABLE `gu`.`store_mobile` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
 `mobile` bigint(20) NOT NULL COMMENT '手机号',
 `points` int(11) NOT NULL COMMENT 'G点数',
 `create_time` int(11) NOT NULL COMMENT '记录时间',
 PRIMARY KEY (`id`),
 UNIQUE KEY `mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预留手机号送G点';

-- 管理后台用户名区分大小写，加唯一索引  gu_admin库  2015.9.15   ruan  (已加)
ALTER TABLE `gu_admin`.`admin` CHANGE  `nickname`  `nickname` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT  '登录名';
ALTER TABLE `gu_admin`.`admin` ADD UNIQUE(`nickname`)

-- 分红百分比配置表 ,保存分红百分比和每人的上限		luo (已加)
-- 数据库: `gu_config`
-- 表的结构 `bonus_percent_config`
CREATE TABLE  `gu_config`.`bonus_percent_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '分红的名字',
  `percent` int(11) NOT NULL COMMENT '百分比',
  `points_max` int(11) NOT NULL DEFAULT '-1' COMMENT '平均每人上限(G点)(-1为没上限)',
  `remark` varchar(20) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='分红百分比配置表' ;
-- 转存表中的数据 `bonus_percent_config` (已加)
INSERT INTO `gu_config`.`bonus_percent_config` (`id`, `name`, `percent`, `points_max`, `remark`) VALUES
(1, 'ideaAll', 40, 100, '全部创意平均分'),
(2, 'flow', 10, 10, '吐槽(去重)'),
(3, 'praiseAll', 10, 10, '全部的点赞人(去重)'),
(4, 'ideaTop', 30, -1, '全部创意获赞比例分(获赞数/总赞数)'),
(5, 'praiseTopThree', 10, -1, '前3创意的点赞人(去重)');

-- 专案结算分红日志		luo(已加)
-- 数据库: `gu_log`
-- 表的结构 `case_finish_bonus_log`
CREATE TABLE  `case_finish_bonus_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `case_id` bigint(20) NOT NULL COMMENT '专案id',
  `points_plan` int(11) NOT NULL COMMENT '计划分红G点',
  `points_real` int(11) NOT NULL COMMENT '实际分红G点',
  `points_surplus` int(11) NOT NULL COMMENT '剩余分红G点',
  `finish_time` int(11) NOT NULL COMMENT '结算时间',
  PRIMARY KEY (`id`),
  KEY `case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案结算分红日志';

-- 专案结算普通参与用户分红日志		luo  (已加)
-- 数据库: `gu_log`
-- 表的结构 `case_finish_bonus_common_user_log`
CREATE TABLE  `case_finish_bonus_common_user_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `case_id` bigint(20) NOT NULL COMMENT '专案id',
  `percent` int(11) NOT NULL COMMENT '分红占专案G点百分比',
  `bonus_points_plan` int(11) NOT NULL COMMENT '计划分红G点',
  `bonus_points_real` int(11) NOT NULL COMMENT '实际分红G点',
  `bonus_points_surplus` int(11) NOT NULL COMMENT '剩余分红G点',
  `bonus_points_average` int(11) NOT NULL COMMENT '平均分红G点',
  `user_num` int(11) NOT NULL COMMENT '分红用户总数',
  `create_time` int(11) NOT NULL COMMENT '分红时间',
  `bonus_name` varchar(20) NOT NULL COMMENT '分红名字',
  `user_id_list` text NOT NULL COMMENT '用户id列表',
  PRIMARY KEY (`id`),
  KEY `case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案结算普通参与用户分红日志';
-- 专案id索引(已加)
ALTER TABLE `case_finish_bonus_common_user_log` ADD INDEX ( `case_id` );

-- 专案结算创意获奖用户分红日志(已加)
-- 数据库: `gu_log`
-- 表的结构 `case_finish_bonus_prize_user_log`
CREATE TABLE  `case_finish_bonus_prize_user_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `case_id` bigint(20) NOT NULL COMMENT '专案id',
  `percent` int(11) NOT NULL COMMENT '专案分配百分比',
  `bonus_points_num` int(11) NOT NULL COMMENT '分配的G点总数',
  `idea_id` bigint(20) NOT NULL COMMENT '创意id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `case_praise` int(11) NOT NULL COMMENT '专案总赞数',
  `idea_praise` int(11) NOT NULL COMMENT '创意获赞数',
  `idea_percent` int(11) NOT NULL COMMENT '创意分配百分比(专案总赞数/创意获赞数)',
  `bonus_points` int(11) NOT NULL COMMENT '创意获分G点',
  `create_time` int(11) NOT NULL COMMENT '分红时间',
  `bonus_name` varchar(20) NOT NULL COMMENT '分红名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案结算创意获奖用户分红日志';

-- 专案id索引(已加)
ALTER TABLE `case_finish_bonus_prize_user_log` ADD INDEX ( `case_id` );

-- 专案表添加对点赞的统计 (已加)
ALTER TABLE `case` ADD `praise_num` INT NOT NULL DEFAULT '0' COMMENT '统计专案的点赞数' AFTER `flow_num` ;

-- 专案表吐槽数量统计默认0 (已加)
ALTER TABLE `case` CHANGE `flow_num` `flow_num` INT( 11 ) NOT NULL DEFAULT '0' COMMENT '吐槽数';

-- 管理后台用户名区分大小写  gu_admin库  2015.9.15   ruan(已加)
ALTER TABLE  `admin` CHANGE  `nickname`  `nickname` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT  '登录名';

-- 标志企业是否已经通过邮箱认证
ALTER TABLE  `company` CHANGE  `is_auth`  `is_auth` TINYINT( 4 ) NOT NULL DEFAULT  '0' COMMENT  '是否通过邮箱认证(0-否 1-是)';
-- 发布创意也支持emoji表情   gu库   2015.9.11     ruan (已加) 
ALTER TABLE  `idea` CHANGE  `content`  `content` VARCHAR( 1000 ) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT  '详细(描述)';
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户意见反馈表' AUTO_INCREMENT=1 ;


-- 以后所有对数据库有修改的都在这里写，按时间倒序排列