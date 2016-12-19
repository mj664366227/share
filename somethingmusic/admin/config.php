<?php
/*基本信息*/
include "../common.php"; 

//载入类库文件
include "../lib/Smarty/Smarty.class.php";   //Smarty类库
include "../lib/adodb5/adodb.inc.php";      //adodb5类库  
include "../lib/function.php";              //自定义函数

//定义本站绝对路径
define("WWW_URL",$_SERVER['DOCUMENT_ROOT']);

//定义smarty模板配置信息
$cut=strrpos($_SERVER['REQUEST_URI'],'/');
$path=substr($_SERVER['REQUEST_URI'],0,$cut);
define("SMARTY_PATH",$path."/templates/");//smarty模板根目录

//定义本站的路径
define("BASE_URL","http://".$_SERVER['HTTP_HOST']."/smu/");

//创建一个smarty对象
$smarty=new Smarty;

//定义各个目录的路径
$smarty->template_dir  =WWW_URL.SMARTY_PATH."template/";   //模板文件目录
$smarty->complie_dir   =WWW_URL.SMARTY_PATH."template_c/"; //编译文件目录
$smarty->config_dir    =WWW_URL.SMARTY_PATH."config/";     //配置文件目录
$smarty->cache_dir     =WWW_URL.SMARTY_PATH."cache/";      //缓存文件目录

//smarty缓存
$smarty->caching=false;

//某些全局变量
$smarty->assign('base_url',BASE_URL);

//连接数据库
$conn=ADONewConnection($sql);
$conn->PConnect($host,$user,$password,$database)or die('connect database error! host: '.$host.', user: '.$user.', database: '.$database);
$conn->Execute('set names '.$char);

$ForceEnter=0;
?>