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
	$page=showpage($conn,'somethingmusic_comment','*','14','','');//调用分页函数
	$smarty->assign("commentarr",$page['page_arr']);
	$smarty->assign("str",$page['page_infor']);
	$smarty->display('comment.html');
}
?>