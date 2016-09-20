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
	$title=$_POST['title'];
	if($title=='')
	{
		tips('文章题目未填写！');
		exit();
	}
	$tmp=$_FILES['pic']['tmp_name'];
	if($tmp!='')
	{
		$pic=upload_pic();
		if($pic=='')
		{
			tips('图片未上传！');
			exit();
		}
		$content=$_POST['content'];
		if($content=='')
		{
			tips('文章内容未填写！');
			exit();
		}
		$conn->Execute("UPDATE `somethingmusic_music_introduce` SET `title` = '$title',`content` = '$content',`pic` = '$pic' WHERE `id`='$id';
")or die('error!'); 
	}
	else
	{
		$pic=$_POST['pic'];
		if($pic=='')
		{
			tips('图片未上传！');
			exit();
		}
		$content=$_POST['content'];
		if($content=='')
		{
			tips('文章内容未填写！');
			exit();
		}
		$conn->Execute("UPDATE `somethingmusic_music_introduce` SET `title` = '$title',`content` = '$content',`pic` = '$pic' WHERE `id`='$id';
")or die('error!'); 
	}
	header('Location:http://lovemusic.cc/thumbs/?thumb=admin/'.$pic.'&w=575&h=270&q=70');
	echo "<script>window.location='content.php'</script>";
}
?>