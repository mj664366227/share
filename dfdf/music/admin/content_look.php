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
	$sql ="select * from `somethingmusic_music_introduce` ";
	$sql.="where `id`='$id'";
	$res=$conn->Execute($sql);
	$result=$res->GetArray();
	$smarty->assign('lookcontent',$result);
	$smarty->display('content_look.html');
}
?>