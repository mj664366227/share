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
	$conn->Execute("update `somethingmusic_ad` set `show`='0' where `id`='$id'")or die('error!');
	tips('隐藏成功！');
}
?>