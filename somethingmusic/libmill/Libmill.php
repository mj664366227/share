<?php
include 'MSG.php';
include '../config.php';
$libmill = new Libmill();
$method = trim($_GET['action']);
echo $libmill->$method();

/*飞车坊平台认证类*/
class Libmill{
	
	var $link;
	var $result;
	
	/*构造函数*/
	public function __construct(){
		global $host, $user, $password, $database, $char;
		$this->link = mysql_connect($host, $user, $password) or die(mysql_error());
		mysql_query('set names '.$char, $this->link) or die(mysql_error());
		mysql_select_db($database, $this->link) or die(mysql_error());
	}
	
	/*析构函数*/
	public function __destruct(){
		mysql_close($this->link) or die(mysql_error());
	}
	
	/*登录*/
	public function login(){
		$uc_key = trim($_GET['uc_key']);
		$url = urldecode(trim($_GET['url']));
		$appid = intval($_GET['appid']);
		$type = trim($_GET['type']);
		$sql='select type, appid, url, authkey from uc_applications where appid=\''.$appid.'\'';
		$this->result = mysql_query($sql, $this->link) or die(mysql_error());
		$arr = mysql_fetch_array($this->result, MYSQL_ASSOC);
		if(!$arr){
			die('{"msg":"'.MSG::SYSTEM_ERROR.'"}');
		}
		$_SESSION['appid'] = $arr['appid'];
		$decode = uc_authcode($arr['authkey'], 'DECODE' ,UC_MYKEY);
		if($decode != $uc_key){
			die('{"appid":"'.$_SESSION['appid'].'","msg":"'.MSG::AUTH_KEY_ERROR.'"}');
		}
		if($url != $arr['url']){
			die('{"appid":"'.$_SESSION['appid'].'","msg":"'.MSG::REQUEST_URL_ERROR.'"}');
		}
		if($type != $arr['type']){
			die('{"appid":"'.$_SESSION['appid'].'","msg":"'.MSG::APPLICATION_ERROR.'"}');
		}
		//验证方式 : md5(uc_key + url + appid + type + 当前时间戳)
		$_SESSION['token'] = md5($uc_key . $url . $appid . $type . $_SERVER['REQUEST_TIME']);
		return '{"appid":"'.$_SESSION['appid'].'","token":"'.$_SESSION['token'].'"}';
	}
	
	/*认证，每次调用接口的时候都先调用这个函数*/
	public function auth(){
		$key = trim($_GET['key']);
		if(!$key){
			die('{"msg":"'.MSG::ILLEGAL_POST.'"}');
		}
		if(!$_SESSION['token']){
			die('{"msg":"'.MSG::ILLEGAL_LOGIN.'"}');
		}
		if($key != $_SESSION['token']){
			die('{"msg":"'.MSG::ILLEGAL_LOGIN.'"}');
		}
		echo 1;
	}
}
?>