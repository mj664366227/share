<?php
//后台首页右边，网站基本参数显示
session_start();
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
if($login=="")
{
	header("location:index.php");
}
else
{
	$doc = file_get_contents('../libmill/MSG.php');
	$doc=str_replace('<?php','',$doc);
	$doc=str_replace('?>','',$doc);
	$doc=str_replace('class MSG{','',$doc);
	$doc=str_replace('}','',$doc);
	$smarty->assign('doc',$doc);
	$smarty->display('doc.html');
}
?>