<?php
session_start();
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
/*$_SESSION['str']=$_POST['user_search'];
if($_POST['user_search']=='')
{
	$str=$_SESSION['str'];
}
$str=$_SESSION['str'];*/
$str=clean($_REQUEST['user_search']);
if($login=="")
{
	header("location:index.php");
}
else
{
	$arr=showpage($conn,'somethingmusic_admin','*','6','name',$str);
	$smarty->assign("user_array",$page['page_arr']);
	$smarty->assign("str",$page['page_infor']);
	$smarty->display('user.html');
}
?>