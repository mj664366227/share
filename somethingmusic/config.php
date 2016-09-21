<?php
session_start();
/*基本信息*/
include "common.php"; 

//载入类库文件
include "lib/Smarty/Smarty.class.php";   //Smarty类库
include "lib/adodb5/adodb.inc.php";      //adodb5类库
include "lib/function.php";              //自定义函数
include 'config.inc.php';                //ucenter配置文件
include 'api/client.php';                //ucenter  api
include 'ucenter/data/config.inc.php';   //ucenter 
include 'pms_config.php';                //小字条配置
include 'sina/config.php';               //新浪微博appid
include 'qq/comm/config.php';            //QQ空间appid

//定义本站绝对路径
define("WWW_URL",$_SERVER['DOCUMENT_ROOT']);

//定义smarty模板配置信息
$cut=strrpos($_SERVER['REQUEST_URI'],'/');
$path=substr($_SERVER['REQUEST_URI'],0,$cut);
define("SMARTY_PATH",$path."/templates/");//smarty模板根目录

//创建一个smarty对象
global $smarty;
$smarty=new Smarty;

//smarty缓存
$smarty->caching=false;

//连接数据库
global $conn;
$conn=ADONewConnection($sql);
$conn->PConnect($host,$user,$password,$database)or die('connect database error! host: '.$host.', user: '.$user.', database: '.$database);
$conn->Execute('set names '.$char);
/*获取网站信息*/
$system=get_my_array($conn,"select * from `somethingmusic_system` where `id`='1'");

//网站关闭
if($system[0]['status']==0)
{
	$str='<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />'.$system[0]['reason'];
	die($str);
}

//某些全局变量
$smarty->assign('base_url',$system[0]['yuming']);//网站根目录
$smarty->assign('current_time',date('Y',$_SERVER['REQUEST_TIME']));//当前时间
$smarty->assign('pms',$pms);//小字条配置
$smarty->assign('sina_appid',WB_AKEY);
$smarty->assign('qq_appid',$_SESSION["appid"]);

//定义各个目录的路径
if(!empty($_GET['skin'])){
	$template = $_GET['skin'];
} else if(!empty($_COOKIE['libmill_skin'])){
	$template = $_COOKIE['libmill_skin'];
} else {
	$template=$system[0]['template'];
}

//自动登录实现
/*
$decode = uc_authcode($_COOKIE['libmill']);
$decode = $decode ? $decode : '';
global $uc_user_data;
$uc_user_data = uc_get_user($decode);
define('UID',$uc_user_data[0] ? intval($uc_user_data[0]) : 0);
$smarty->assign('uid', UID);
*/

//3个平台的登录
/*
$smarty->assign('libmill', $decode);
$smarty->assign('qq', $_COOKIE['qq']);
$smarty->assign('sina', $_SESSION['weibo_token']);
*/
//sina自动登录
/*if($_SESSION['weibo_token']){
	$sina = new SaeTClientV2(WB_AKEY,WB_SKEY,$_SESSION['weibo_token']);
}*/

//跳转目录地址
define('REDIRECT', $system[0]['yuming'].'msg.php?skin='.$_COOKIE['libmill_skin']);

setcookie('libmill_skin',$template,time()+999999);
define('TEMPLATE',$template);

$smarty->template_dir  =WWW_URL.SMARTY_PATH."template/".TEMPLATE;   //模板文件目录
$smarty->complie_dir   =WWW_URL.SMARTY_PATH."template_c/";          //编译文件目录
$smarty->config_dir    =WWW_URL.SMARTY_PATH."config/";              //配置文件目录
$smarty->cache_dir     =WWW_URL.SMARTY_PATH."cache/";               //缓存文件目录
?>