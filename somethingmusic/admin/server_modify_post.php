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
	$id=$_REQUEST['id'];
	$conn->Execute("update `somethingmusic_server` set `server`='$server_address',`name`='$server_name' where `id`='$id'")or die('error!');
	tips('服务器信息修改成功！');
	echo "<script>window.location='server.php'</script>";
}
?>