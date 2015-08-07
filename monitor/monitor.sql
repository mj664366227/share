CREATE TABLE `m_memory` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `server_id` smallint(5) unsigned NOT NULL COMMENT '服务器id',
  `time` int(10) unsigned NOT NULL COMMENT '时间',
  `data` text NOT NULL COMMENT '监控数据(time|mem.total|mem.used;...)',
  PRIMARY KEY (`id`),
  KEY `server_id` (`server_id`,`time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='内存监控';

CREATE TABLE `m_server` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '服务器id',
  `name` varchar(50) NOT NULL COMMENT '服务器名称',
  `ip` bigint(20) NOT NULL COMMENT '服务器ip',
  `type` char(7) NOT NULL COMMENT '操作系统类型：(LINUX|WINDOWS)',
  `port` smallint(6) unsigned NOT NULL COMMENT 'snmp端口',
  `security_name` varchar(1000) NOT NULL COMMENT 'snmp版本为v3时访问SNMP的用户名',
  `pass_phrase` varchar(1000) NOT NULL COMMENT 'snmp版本为v3时访问SNMP的密码',
  `auth_protocol` char(3) NOT NULL COMMENT 'snmp版本为v3时身份验证的加密方式(MD5|SHA)',
  `priv_protocol` char(3) NOT NULL COMMENT '加密/解密协议(DES|AES)',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='服务器';

CREATE TABLE `m_user` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(10) NOT NULL COMMENT '用户名',
  `pass` char(32) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户表';

