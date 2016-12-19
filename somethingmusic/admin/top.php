<?php
session_start();
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
if($login=="")
{
	header("location:index.php");
}
else
{
	/*获取错误条数*/
	$sql="select * from `somethingmusic_question`";
	$result=$conn->Execute($sql)or die('select database:somethingmusic_question error!');
	$question=$result->RecordCount();
	$smarty->assign("username",$_SESSION['username']);
	$smarty->assign("question",$question);
	$smarty->assign("ip",$_SERVER['REMOTE_ADDR']);
	$smarty->display('top.html');
}
?>