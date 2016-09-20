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
	$server_address=clean($_POST['server_address']);
	$server_name=clean($_POST['server_name']);
	if($server_address=='http://')
	{
		tips('服务器地址没有填写！');
		exit();
	}
	if($server_name=='')
	{
		tips('服务器名称没有填写！');
		exit();
	}
	$conn->Execute("insert into `somethingmusic_server` (`server`,`name`) values ('$server_address','$server_name')")or die('error!');
	tips('服务器添加成功！');
}
?>