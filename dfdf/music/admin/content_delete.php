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
	$conn->Execute('delete from `somethingmusic_read_comment` where `content_id`='.$id);
	delete($conn,'somethingmusic_music_introduce','id',$id);
}
?>