<?php
//隐藏公告
session_start();
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
if($login=="")
{
	header("location:index.php");
}
else
{
	if($show=='0')
	{
		tips('操作错误！此公告已经是隐藏状态！');
	}
	else
	{
		$id=$_GET['id'];
		$conn->Execute("update `somethingmusic_notice` set `show`='0' where id='$id'") or die('update database error!');
		tips('隐藏成功！');
	}
}
?>