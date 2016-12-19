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
	$delete=$_REQUEST['bigcategory'];
	$conn->Execute("delete from `somethingmusic_bigcategory` where `bigcategory`='$delete'")or die('delete error!');
	$conn->Execute("delete from `somethingmusic_smallcategory` where `bigcategory`='$delete'")or die('delete error!');
	tips('删除成功！');
}
?>