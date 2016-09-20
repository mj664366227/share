<?php
session_start();
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
$id=$_GET['id'];
if($login=="")
{
	header("location:index.php");
}
else
{
	$result=$conn->execute("select * from `somethingmusic_admin` where `id`='$id'");
	$arr=$result->GetArray();
	$smarty->assign('modify',$arr);
	$smarty->display('user_modify.html');
}
?>