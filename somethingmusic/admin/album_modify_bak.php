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
	$id            =    $_REQUEST['id'];
	$title         =    $_POST['title'];
	if($title=='')
	{
		tips('专辑名称未填写！');
		exit();
	}
	$category      =    $_POST['category1'];
	if($category=='')
	{
		$category      =    $_POST['category'];
	}
	else 
	{
		$category      =    $_POST['category1'];
	}
	if($category=='')
	{
		tips('专辑类型未填写！');
		exit();
	}
	if($_FILES['pic']['tmp_name']=='')
	{
		$pic       =    $_POST['pic'];
	}
	else
	{
		$pic       =    upload_pic();
	}
	if($pic=='')
	{
		tips('图片未上传！');
		exit();
	}
	
	$artist        =    $_POST['artist'];
	if($artist=='')
	{
		tips('歌手名称未填写！');
		exit();
	}
	$company       =    $_POST['company'];
	if($company=='')
	{
		tips('唱片公司未填写！');
		exit();
	}
	$language      =    $_POST['language'];
	
	
	if(is_array($language))
	{
		foreach($language as $l){$language_a.=$l.'&nbsp;';}
	}
	else
	{
		$language_a=$_POST['language'];
	}
	if($language=='')
	{
		tips('语言种类未填写！');
		exit();
	}
	
	$area          =    $_POST['area'];
	$time          =    $_POST['time'];
	
	
	if(is_array($area))
	{
		foreach($area as $a){$area_a.=$a;}
	}
	else
	{
		$area_a    =    $_POST['area'];
	}
	if($area=='')
	{
		tips('来源地区未填写！');
		exit();
	}
	
	$url           =    $_POST['url'];
	$sort          =    $_POST['sort'];
	$songtitle     =    $_POST['songtitle'];
	$content       =    $_POST['content'];
	if($content=='')
	{
		tips('专辑介绍未填写！');
		exit();
	}
	
	$conn->Execute("update `somethingmusic_album` set `title`='$title',`category`='$category',`picture`='$pic',`artist`='$artist',`company`='$company',`time`='$time',`language`='$language_a',`area`='$area_a',`text`='$content' where `id`='$id'")or die('1error!');
	
    $get_album=$conn->Execute("select id from `somethingmusic_album` where `artist`='$artist' and `title`='$title'");
    $albumid=$get_album->GetArray();
    $album_id=$albumid[0][0];
	
	$table    =    'somethingmusic_musicplay';
	$coloum   =    'album';
	$where    =    $title;
	$sql="delete from `".$table."` where `".$coloum."`='".$where."' ".$andwhere;
	$conn->Execute($sql)or die('delete error!');
	
	
	
	$n=count($url);
	for($i=0;$i<$n;$i++)
	{
		$temp=$url[$i];
		if($temp=='')
		{
			tips('歌曲地址未填写！');
			exit();
		}
		$pid=$sort[$i];
		if($pid=='')
		{
			tips('歌曲顺序未填写！');
			exit();
		}
		$stitle=$songtitle[$i];
		if($stitle=='')
		{
			tips('歌曲名称未填写！');
			exit();
		}
		$conn->Execute("insert into `somethingmusic_musicplay` (`artist`,`album`,`title`,`addtime`,`url`,`pid`,`category`,`albumid`) values ('$artist','$title','$stitle','$time','$temp','$pid','$category','$album_id')")or die('2error!');
	}
	tips('专辑修改成功！');
}
?>