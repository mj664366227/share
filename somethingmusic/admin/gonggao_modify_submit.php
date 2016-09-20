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
	$str=$_POST['gonggao'];
	$time=date("Y-m-d H:i:s"); 
	if($str=='')
	{
		tips("你没有输入任何信息！");
	}
	else
	{
		$conn->Execute("update `somethingmusic_notice` set `gonggao`='$str',`time`='$time' where `id`='$id'") or die('update database error!');
		tips('修改成功！');
		echo "<script>window.location='gonggao.php'</script>";
	}
}
?>