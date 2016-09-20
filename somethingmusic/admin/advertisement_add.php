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
	$title=clean($_POST['ad_title']);
	$url=clean($_POST['ad_url']);
	if($url=='http://')
	{
		$url='';
	}
	if($title=='')
	{
		tips('广告标题不能为空！');
		exit();
	}
	$time=date("Y-m-d H:i:s");
	//上传文件
	$name=upload_pic();
	$conn->Execute("insert into `somethingmusic_ad` (`title`,`time`,`picture`,`show`,`url`) values ('$title','$time','$name','1','$url')")or die('insert database error!');
	//tips('广告添加成功！');
	header('Location:http://lovemusic.cc/thumbs/?thumb=admin/'.$name.'&w=320&h=150&q=85');
    exit();
	
}
?>