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
	$temp_=0;
	$id            =    $_POST['id'];
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
		$temp_     =    1;
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
	
	$sql="delete from `somethingmusic_musicplay` where `albumid`='$id'";
	$conn->Execute($sql)or die('delete error!');
	
	$n=count($url);
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
		$sql.="('$artist','$title','$stitle','$time','$temp','$pid','$category','$id'),";
	}
	$conn->Execute(substr($sql,0,-1))or die ('update album error...');
	if($temp_)
	{
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
				header('Location:http://lovemusic.cc/thumbs/?thumb=admin/'.$pic.'&w=170&h=170&q=15');
			}
		}
	}
	else
	{
		tips('成功！');
	}
}
?>