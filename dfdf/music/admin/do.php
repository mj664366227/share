<?php
session_start();
include "config.php";//载入系统配置文件
$action=$_POST['action'];
if(!isset($action))
{
	header("location:index.php");
}
else
{
	$action=$_POST['action'];
	$title=$_POST['title'];
	if(isset($action))
	{
		$sql="select distinct artist from `somethingmusic_album` where `title`='$title'";
		$result=get_my_array($conn,$sql);
		print_r($result);
		$i=count($result);
		/*
		if($i>=1)
		{
			for($a=0;$a<$i;$a++)
			{
				$text.=' '.$result[$a][0].' ';
			}
			echo $text;
			exit();
		}*/
	}
}
?>