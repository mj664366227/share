<?php
include "config.php";//载入系统配置文件
if($ForceEnter==1)
{
	$_SESSION['login']=md5(rand(1000,9999));
	$_SESSION['username']='ForceEnter';
	header("location:admin.php");
}
if($ForceEnter==0)
{
	$smarty->display('index.html');
}
?>