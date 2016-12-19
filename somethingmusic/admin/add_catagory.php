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
	$big=clean($_POST['bigcatagory']);
	if($big!='')
	{
		$sql="select * from `somethingmusic_bigcategory` where `bigcategory`='$big'";
		$result=$conn->Execute($sql);
		if($result->recordcount()>=1)
		{
			tips('此分类已存在，请不要重复添加！');
			exit();
		}
		else
		{
			$conn->Execute("insert into `somethingmusic_bigcategory` (`bigcategory`) values ('$big')")or die("insert error!");
		    tips('一级标题添加成功！');
			exit();
		}
	}
	else
	{
		tips('输入为空！');
		exit();
	}
}
?>