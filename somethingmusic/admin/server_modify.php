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
	$result=$conn->Execute("select * from `somethingmusic_server` where `id`='$id'");
	$res=$result->GetArray();
	$smarty->assign('server',$res);
	$smarty->display('server_modify.html');
}
?>