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
	$webtitle=$_POST['webtitle'];
	$yuming=$_POST['yuming'];
	$seo=$_POST['seo'];
	$description=$_POST['description'];
	$status=$_POST['status'];
	$reason=$_POST['reason'];
	$template=$_POST['template'];
	$slide=$_POST['slide'];
	$conn->Execute("update `somethingmusic_system` set `webtitle`='$webtitle',`yuming`='$yuming',`seo`='$seo',`description`='$description',`status`='$status',`reason`='$reason',`template`='$template',`slide`='$slide' where `id`='1'")or die('update database error!');
	tips('站点信息更新成功！');
}
?>