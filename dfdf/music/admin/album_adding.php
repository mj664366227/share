<?php
session_start();
include "config.php";//载入系统配置文件
include '../thumbs/phpThumb/phpthumb.class.php'; 
$login=$_SESSION['login'];
set_time_limit(0);
if($login=="")
{
	header("location:index.php");
}
else
{
	/*由于html页已经完成了输入判断，不过还要加，因为ff不能执行js*/
	$title         =    addslashes($_POST['title']);
	/*if($title=='')
	{
		tips('专辑名称未填写！');
		exit();
	}*/
	$category      =    $_POST['category'];
	/*if($category=='')
	{
		tips('专辑类型未填写！');
		exit();
	}*/
	$pic           =    upload_pic();
	/*if($pic=='')
	{
		tips('图片未上传！');
		exit();
	}*/
	$artist        =    $_POST['artist'];
	/*if($artist=='')
	{
		tips('歌手名称未填写！');
		exit();
	}*/
	$company       =    $_POST['company'];
	/*if($company=='')
	{
		tips('唱片公司未填写！');
		exit();
	}*/
	$language      =    $_POST['language'];
	/*if($language=='')
	{
		tips('语言种类未填写！');
		exit();
	}*/
	$area          =    $_POST['area'];
	/*if($area=='')
	{
		tips('来源地区未填写！');
		exit();
	}*/
	$url           =    $_POST['url'];
	$sort          =    $_POST['sort'];
	$songtitle     =    $_POST['songtitle'];
	$content       =    $_POST['content'];
	/*if($content=='')
	{
		tips('专辑介绍未填写！');
		exit();
	}*/
	$time          =    $_POST['time'];
	foreach($language as $l)
	{
		$language_a.=$l.'&nbsp;';
	}
	foreach($area as $a)
	{
		$area_a.=$a.'&nbsp;';
	}
	
	$author=$_SESSION['username'];
	
	//print_r($url);
	$n=count($url);
	if($n==0)
	{
		tips('未添加歌曲！');
		exit();
	}
	$conn->Execute("insert into `somethingmusic_album` (`category`,`title`,`artist`,`time`,`company`,`area`,`language`,`text`,`picture`,`author`) values ('$category','$title','$artist','$time','$company','$area_a','$language_a','$content','$pic','$author')")or die('error!');
	$album_id=$conn->Insert_ID();
	$sql="insert into `somethingmusic_musicplay` (`artist`,`album`,`title`,`addtime`,`url`,`pid`,`category`,`albumid`) values ";
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
		$sql.="('$artist','$title','$stitle','$time','$temp','$pid','$category','$album_id'),";
	}
	//die(substr($sql,0,-1));
	$conn->Execute(substr($sql,0,-1))or die('add album error...');
	//tips('专辑添加成功！');
	$phpThumb=new phpThumb();
	$thumb='admin/'.$pic;
	$w=190;$h=190;$q=75;
	$image=$_SERVER['DOCUMENT_ROOT'].'/'.$thumb;
    $phpThumb->setSourceFilename($image);   
    $phpThumb->setParameter('w',$w);   
    $phpThumb->setParameter('h',$h);  
	$phpThumb->setParameter('q',$q); 
	$cache=$_SERVER['DOCUMENT_ROOT'].'/thumbs/thumbs/cache/w'.$w.'h'.$h.'/'.$thumb;
    if($phpThumb->GenerateThumbnail()) 
	{
        @mkdir(dirname($cache),0777,true);   
        if($phpThumb->RenderToFile($cache))
		{
			//die('Location:http://music.libmill.com/thumbs/?thumb=admin/'.$pic.'&w=170&h=170&q=15');
			header('Location:http://music.libmill.com/thumbs/?thumb=admin/'.$pic.'&w=170&h=170&q=15');
		}
	}
}
?>