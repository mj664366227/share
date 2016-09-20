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
	$bigarray=$result1->GetArray();
	$smallarray=$result2->GetArray();
	$smarty->assign('bigcategory',$bigarray);
	$smarty->assign('smallarray',$smallarray);
	$smarty->display('category.html');
}
?>