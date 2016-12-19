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
	$path=WWW_URL.'/templates/template/';
	$file=scandir($path);
	unset($file[0]);
	unset($file[1]);
	$result=$conn->Execute("select * from `somethingmusic_system`");
	$array=$result->GetArray();
	foreach($file as $k=>$f)
	{
		if(strrpos($f,'.'))
		{
			unset($file[$k]);
		}
		else
		{
			$pic[$f]=scandir($path.$f);
		}
	}
	foreach($pic as $k=>$p)
	{
		foreach($p as $kk=>$pp)
		{
			if(!strrpos($pp,'png'))
			{
				unset($pic[$k][$kk]);
			}
		}
	}
	$num=array(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30);
	$smarty->assign('num',$num);
	$smarty->assign('array',$array);
	$smarty->assign('file',$file);
	$smarty->assign('pic',$pic);
	$smarty->display('website.html');
}
?>