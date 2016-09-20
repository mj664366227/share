<?php
//显示公告
session_start();
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
if($login=="")
{
	header("location:index.php");
}
else
{
	if($show=='1')
	{
		tips('操作错误！此公告已经是显示状态！');
	}
	else
	{
		$id=$_GET['id'];
		$conn->Execute("update `somethingmusic_notice` set `show`='1' where id='$id'") or die('update database error!');
		tips('显示成功！');
	}
}
?>