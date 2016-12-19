<?php
include "config.php";//载入系统配置文件
$str=$_POST['adgonggao'];
$time=date("Y-m-d H:i:s"); 
$show='0';//状态默认为隐藏 
if($str=='点击这里添加公告...'||$str=='')
{
	tips("你没有输入任何信息！");
}
else
{
	$sql="insert into `somethingmusic_notice` (`gonggao`,`time`,`show`) values ('$str','$time','$show')";
	$conn->Execute($sql)or die('update database error!');
	tips('公告添加成功！');
}
?>