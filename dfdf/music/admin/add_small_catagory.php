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
	$small=clean($_POST['smallcatagory']);
	$big=clean($_POST['father']);
	if($big!=''&&$small!='')
	{
		$sql="select * from `somethingmusic_smallcategory` where `bigcategory`='$big' and `smallcategory`='$small'";
		$res=$conn->Execute($sql);
		if($res->recordcount()>=1)
		{
			tips('此类型已存在，请不要重复添加！');
			exit();
		}
		$conn->Execute("insert into `somethingmusic_smallcategory` (`bigcategory`,`smallcategory`) values ('$big','$small')")or die("insert error!");
		tips('音乐类型添加成功！');
		exit();		
	}
	else
	{
		tips('输入为空！');
		exit();
	}
}
?>