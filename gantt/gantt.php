<?php
require('mysql.php');

// 定义配置
define('GANTT_USER', $_SERVER["HTTP_USER_AGENT"].'@'.$_SERVER["REMOTE_ADDR"]);
define('DB_HOST','127.0.0.1');
define('DB_USER','root');
define('DB_PASS','root');
define('DB_PORT','3306');
define('DB_NAME','gantt');

// 连接数据库
$mysql = new mysql(DB_HOST, DB_USER, DB_PASS, '', 'mysql', DB_PORT);

// 如果没有库和表，自动创建
$hasGanttDb = false;
$rs = $mysql->query('show databases;');
foreach($rs as $db){
	if(trim($db['Database']) === DB_NAME){
		$hasGanttDb = true;	
	}
}
if(!$hasGanttDb){
	$mysql->query('create database '.DB_NAME.' DEFAULT CHARSET=utf8');
	$sql = 'CREATE TABLE `'.DB_NAME.'`.`user` (
		 `id` int(11) NOT NULL AUTO_INCREMENT,
		 `user` varchar(1000) NOT NULL,
		 `create_time` int(11) NOT NULL,
		 PRIMARY KEY (`id`),
		 UNIQUE KEY `user` (`user`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;';
	$mysql->query($sql);
	
	$sql = 'CREATE TABLE `'.DB_NAME.'`.`data` (
		 `id` int(11) NOT NULL AUTO_INCREMENT,
		 `uid` int(11) NOT NULL,
		 `title` varchar(1000) NOT NULL,
		 `data` longtext NOT NULL,
		 `create_time` int(11) NOT NULL,
		 PRIMARY KEY (`id`),
		 KEY `uid` (`uid`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;';
	$mysql->query($sql);
}
$mysql->select_db(DB_NAME);

$rs = $mysql->select('user')->where('user')->eq(GANTT_USER)->query();
$uid = intval(count($rs)) > 0 ? intval($rs[0]) : 0;

$action = trim($_REQUEST['action']);
switch($action){
	case 'list':
		if($uid <= 0){
			$uid = $mysql->insert('user', array('user' => GANTT_USER, 'create_time' => $_SERVER["REQUEST_TIME"]));
		}
		
		$rs = $mysql->select('data')->where('uid')->eq($uid)->order_by(array('id' => DESC))->query();
		echo json_encode($rs);
	break;
	
	case 'save':
		$id = intval($_REQUEST['id']);
		$title = trim($_REQUEST['title']);
		$data = trim($_REQUEST['data']);
		$data = json_decode($data, true);
		$data = parseGanttData($data);
		$data = json_encode($data, JSON_UNESCAPED_UNICODE);
		if($id > 0){
			$mysql->update('data', array('title' => $title, 'data' => $data))->where('id')->eq($id)->query();
		} else {
			$mysql->insert('data', array('uid' => $uid, 'title' => $title, 'data' => $data, 'create_time' => $_SERVER["REQUEST_TIME"]));
		}
		echo '1';
	break;
	
	default:
		die;
	break;
}

function parseGanttData($json){
	$result = array();
	foreach($json as $value){
		if($value['children']){
			$result = array_merge($result, parseGanttData($value['children']));
			unset($value['children']);
		} 
		$result[] = $value;
	}
	return $result;
}
?>