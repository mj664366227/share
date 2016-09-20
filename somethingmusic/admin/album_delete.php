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
	$conn->Execute("delete from `somethingmusic_musicplay` where `albumid`='$id'");
	delete($conn,'somethingmusic_album','id',$id);	
}
?>