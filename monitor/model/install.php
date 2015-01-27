<?php
class minstall extends model{
	/**
	 * 安装sql语句
	 */
	public static function sql(){
		$sql[] = '
		CREATE TABLE `m_memory` (
		`id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT \'id\',
		`server_id` smallint(5) unsigned NOT NULL COMMENT \'服务器id\',
		`time` int(10) unsigned NOT NULL COMMENT \'时间\',
		`data` text NOT NULL COMMENT \'监控数据(time|mem.total|mem.used;...)\',
		PRIMARY KEY (`id`),
		KEY `server_id` (`server_id`,`time`)
		) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT=\'内存监控\';';

		$sql[] = '
		CREATE TABLE `m_server` (
		`id` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT \'服务器id\',
		`name` varchar(20) NOT NULL COMMENT \'服务器名称\',
		`ip` int(10) NOT NULL COMMENT \'服务器ip\',
		`type` char(7) NOT NULL COMMENT \'操作系统类型：(LINUX|WINDOWS)\',
		`port` smallint(6) unsigned NOT NULL COMMENT \'snmp端口\',
		`snmp_version` tinyint(4) unsigned NOT NULL COMMENT \'snmp版本：2:2c 3:v3\',
		`community` varchar(100) NOT NULL COMMENT \'老版本的SNMP访问时需要提供的community字符串\',
		`security_name` varchar(100) NOT NULL COMMENT \'snmp版本为v3时访问SNMP的用户名\',
		`pass_phrase` varchar(100) NOT NULL COMMENT \'snmp版本为v3时访问SNMP的密码\',
		`auth_protocol` char(3) NOT NULL COMMENT \'snmp版本为v3时身份验证的加密方式(MD5|SHA)\',
		`priv_protocol` char(3) NOT NULL COMMENT \'加密/解密协议(DES|AES)\',
		PRIMARY KEY (`id`)
		) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT=\'服务器\';';

		$sql[] = '
		CREATE TABLE `m_user` (
		`id` smallint(6) NOT NULL AUTO_INCREMENT COMMENT \'用户id\',
		`name` varchar(10) NOT NULL COMMENT \'用户名\',
		`pass` char(32) NOT NULL COMMENT \'密码\',
		PRIMARY KEY (`id`)
		) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT=\'用户表\';';

		return $sql;
	}

	/**
	 * 检查数据库是否已经创建
	 * @param $db_host 数据库主机
	 * @param $db_user 数据库用户
	 * @param $db_pass 数据库密码
	 * @param $db_pre 数据库前缀
	 * @param $db_name 数据库名称
	 * @param $db_port 数据库端口(默认3306)
	 */
	public static function check($db_host, $db_user, $db_pass, $db_pre, $db_name, $db_port = 3306){
		$mysql = new shareMysql($db_host, $db_user, $db_pass, $db_pre, $db_name, $db_port);
		return $mysql->can_use();
	}

	/**
	 * 创建数据表
	 * @param $host 数据库主机
	 * @param $port 数据库端口
	 * @param $user 数据库用户
	 * @param $pass 数据库密码
	 * @param $pre 数据库前缀
	 * @param $db 数据库名称
	 */
	public static function build($host, $port, $user, $pass, $pre, $db){
		$mysql = new shareMysql($host, $user, $pass, $pre, null);
		$mysql->query('create database `'.$db.'` default character set utf8 collate utf8_general_ci;');
		$mysql->query('use `'.$db.'`');
		foreach(minstall::sql() as $sql) {
			$mysql->query($sql);
		}
	}

	/**
	 * 显示现在安装了多少个数据表
	 */
	public static function show(){
		$mysql = new shareMysql(DB_HOST, DB_USER, DB_PASS, DB_PRE, DB_NAME, DB_PORT, CHARSET);
		return $mysql->query('show tables');
	}

	/**
	 * 更新配置文件
	 * @param $host 数据库主机
	 * @param $port 数据库端口
	 * @param $user 数据库用户
	 * @param $pass 数据库密码
	 * @param $pre 数据库前缀
	 * @param $db 数据库名称
	 */
	public static function update_config_file($host, $port, $user, $pass, $pre, $db){
		$file = file_get_contents(DOCUMENT_ROOT.'config.php');
		$file = str_replace('define(\'DB_HOST\', \'\');', 'define(\'DB_HOST\', \''.$host.'\');', $file);
		$file = str_replace('define(\'DB_PORT\', \'\');', 'define(\'DB_PORT\', '.$port.');', $file);
		$file = str_replace('define(\'DB_USER\', \'\');', 'define(\'DB_USER\', \''.$user.'\');', $file);
		$file = str_replace('define(\'DB_PASS\', \'\');', 'define(\'DB_PASS\', \''.$pass.'\');', $file);
		$file = str_replace('define(\'DB_PRE\', \'\');', 'define(\'DB_PRE\', \''.$pre.'\');', $file);
		$file = str_replace('define(\'DB_NAME\', \'\');', 'define(\'DB_NAME\', \''.$db.'\');', $file);
		file_put_contents(DOCUMENT_ROOT.'config.php', $file, LOCK_EX);
	}
}
?>