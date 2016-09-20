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
	$id=$_REQUEST['id'];
	$sql ="select * from `somethingmusic_admin`";
	$sql.="where `id`='$id'";
	$res=$conn->Execute($sql);
	$result=$res->GetArray();
	if($result[0][1]==$_SESSION['username'])
	{
		tips("You can't kill myself!!!");
		exit();
	}
	delete($conn,'somethingmusic_admin','id',$id);
}
?>