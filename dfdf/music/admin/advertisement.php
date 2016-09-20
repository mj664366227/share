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
	$page=showpage($conn,'somethingmusic_ad','*','6','','');//调用分页函数
	$smarty->assign("adarr",$page['page_arr']);
	$smarty->assign("str",$page['page_infor']);
	$smarty->display('advertisement.html');
}
?>