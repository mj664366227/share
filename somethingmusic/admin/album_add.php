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
	$result1=$conn->Execute("select * from `somethingmusic_bigcategory` order by `id` asc");
	$result2=$conn->Execute("select * from `somethingmusic_smallcategory` order by `id` asc");
	$result3=$conn->Execute("select * from `somethingmusic_server` order by `id` asc");
	$bigarray=$result1->GetArray();
	$smallarray=$result2->GetArray();
	$server=$result3->GetArray();
	$smarty->assign('big',$bigarray);
	$smarty->assign('small',$smallarray);
	$smarty->assign('server',$server);
	$smarty->display('album_add.html');
}
?>