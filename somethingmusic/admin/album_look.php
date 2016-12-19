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
	$res1=$conn->Execute("select * from `somethingmusic_album` where `id`='$id'")or die('error!');
	$res2=$conn->Execute("select * from `somethingmusic_musicplay` where `albumid`='$id' order by `id` desc")or die('error!');
	$result1=$conn->Execute("select * from `somethingmusic_bigcategory` order by `id` asc");
	$result2=$conn->Execute("select * from `somethingmusic_smallcategory` order by `id` asc");
	$result3=$conn->Execute("select * from `somethingmusic_server` order by `id` asc");
	$bigarray=$result1->GetArray();
	$smallarray=$result2->GetArray();
	$server=$result3->GetArray();
	$album_info=$res1->GetArray();
	$ablum_music=$res2->GetArray();
	$smarty->assign('album_info',$album_info);
	$smarty->assign('ablum_music',$ablum_music);
	$smarty->assign('big',$bigarray);
	$smarty->assign('small',$smallarray);
	$smarty->assign('server',$server);
	$smarty->display('album_look.html');
}
?>