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
	$page=showpage($conn,'somethingmusic_question','*','15','','');//调用分页函数
	$smarty->assign("error_info",$page['page_arr']);
	$smarty->assign("str",$page['page_infor']);
	$smarty->display('error_info.html');
}
?>