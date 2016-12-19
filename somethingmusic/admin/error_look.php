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
	$id=$_REQUEST['id'];
	$res=$conn->Execute("select * from `somethingmusic_question` where `id`='$id'")or die('error!');
	$result=$res->GetArray();
	$smarty->assign('look',$result);
	$smarty->display('error_look.html');
}
?>