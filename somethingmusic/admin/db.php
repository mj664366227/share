<?php
//后台首页右边，网站基本参数显示
session_start();
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
if($login=="")
{
	header("location:index.php");
}
else
{
	$sql = 'SHOW TABLE STATUS FROM '.$database;
	$table = get_my_array($conn,$sql);
	$smarty->assign('table',$table);
	$smarty->display('db.html');
}
?>