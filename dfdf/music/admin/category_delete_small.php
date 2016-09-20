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
	$delete=$_REQUEST['smallcategory'];
	delete($conn,'somethingmusic_smallcategory','smallcategory',$delete);
}
?>