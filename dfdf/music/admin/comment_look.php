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
	$result=$conn->Execute("select * from `somethingmusic_comment` where `id`='$id'")or die('select error!');
	$array=$result->GetArray();
	$smarty->assign("content",$array);
	$smarty->display('comment_look.html');
}
?>