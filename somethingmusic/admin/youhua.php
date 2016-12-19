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
	$table=$_GET['table'];
	if($table=='all')
	{
		$sql='show tables';
		$res=get_my_array($conn,$sql);
		foreach($res as $r)
		{
			doing($r['Tables_in_'.$database]);
		}
		$table='所有';
	}
	else
	{
		doing($table);
	}
	tips($table.'表优化成功！');
}
function doing($table)
{
	global $conn;
	$sql='CHECK TABLE  `'.$table.'`';
	$conn->Execute($sql)or die('err1');
	$sql='ANALYZE TABLE  `'.$table.'`';
	$conn->Execute($sql)or die('err2');
	$sql='REPAIR TABLE  `'.$table.'`';
	$conn->Execute($sql)or die('err3');
	$sql='OPTIMIZE TABLE  `'.$table.'`';
	$conn->Execute($sql)or die('err4');
}
?>