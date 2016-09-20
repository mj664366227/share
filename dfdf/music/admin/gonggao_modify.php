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
	$id=$_GET['id'];
	$result=$conn->execute("select * from `somethingmusic_notice` where `id`='$id'");
	$arr=$result->GetArray();
	$smarty->assign('modify',$arr);
	$smarty->display('gonggao_modify.html');
}
?>