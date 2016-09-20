<?php
header("Content-type: application/json");
include '../../config.php';

//此文件调用格式:http://lovemusic.cc/mobile/mobile_get_album.php?id=要查询的专辑id号，如id不存在则报错

$id=$_GET['id'];
if($id!='')
{
	$sql="select * from `somethingmusic_album` where `id`='$id'";

	$album=get_my_array($conn,$sql) or die('select error!');
	for($i=0;$i<12;$i++)
	{
		unset($album[0][$i]);
	}
	$album[0]['text']=urlencode(clean($album[0]['text']));
	$album[0]['title']=urlencode($album[0]['title']);
	$album[0]['category']=urlencode($album[0]['category']);
	$album[0]['company']=urlencode($album[0]['company']);
	$album[0]['area']=urlencode($album[0]['area']);
	$album[0]['language']=urlencode($album[0]['language']);
	$album[0]['artist']=urlencode($album[0]['artist']);
	$album[0]['picture']=urlencode(BASE_URL.'thumbs/thumbs/cache/w190h190/admin/'.clean($album[0]['picture']));
	$album[0]['time']=urlencode($album[0]['time']);
	echo urldecode(json_encode($album[0]));
	
	
	
	$sql="select * from `somethingmusic_musicplay` where `albumid`='$id' order by `pid` asc";
	$list=get_my_array($conn,$sql) or die('select error!');
	$newlist=array();
	$count=count($list);
	for($a=0;$a<$count;$a++)
	{
		for($aa=0;$aa<11;$aa++)
		{
			unset($list[$a][$aa]);
		}
		$newlist[$a]['artist']=urlencode($list[$a]['artist']);
		$newlist[$a]['album']=urlencode($list[$a]['album']);
		$newlist[$a]['title']=urlencode($list[$a]['title']);
		$newlist[$a]['url']=urlencode($list[$a]['url']);
		$newlist[$a]['category']=urlencode($list[$a]['category']);
		$newlist[$a]['pid']=$list[$a]['pid'];
		$newlist[$a]['have_played']=$list[$a]['have_played'];
		$newlist[$a]['download']=$list[$a]['download'];
	}
	echo urldecode(json_encode($newlist));
	
}
else
{
	die('Access Dany!');
}
?>