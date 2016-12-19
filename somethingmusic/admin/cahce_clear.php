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
	admin();
	index();
	$conn->CacheFlush();
	tips('缓存清理成功！');
}

function admin()
{
	$admin_i=strpos($_SERVER['SCRIPT_FILENAME'],'cahce_clear.php');	
	$admin_path=substr($_SERVER['SCRIPT_FILENAME'],0,$admin_i).'templates_c/';
	$admin_files=scandir($admin_path);
	$admin_count=count($admin_files);
	for($i=2;$i<$admin_count;$i++)
	{
		unlink($admin_path.$admin_files[$i])or die('fuck');
		//echo $admin_path.$admin_files[$i].'<br>';
	}
}

function index()
{
	$index_i=strpos($_SERVER['SCRIPT_FILENAME'],'admin/cahce_clear.php');	;
	$index_path=substr($_SERVER['SCRIPT_FILENAME'],0,$index_i).'templates_c/';
	$index_files=scandir($index_path);
	$index_count=count($index_files);
	for($i=2;$i<$index_count;$i++)
	{
		unlink($index_path.$index_files[$i])or die('fuck');
	}
}
?>