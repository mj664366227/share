<?php
session_start();
header('http-equiv="Content-Type" content="text/html; charset=utf-8');
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
$action=$_GET['action'];
if($action=='')
{
	$action=$_POST['action'];
}
if($login==""||$action=='')
{
	header("location:index.php");
}
else
{
	switch($action)
	{
		case 'index':
		//获取电台频道
		$radio_channel_set=get_my_array($conn,'select * from `somethingmusic_radio_channel_set` order by `count` desc');
		$smarty->assign('radio_channel_set',$radio_channel_set);
		$smarty->display('radio_channel.html');
		break;
		
		case 'add':
		$name=$_POST['name'];
		$en_name=$_POST['en_name'];
		$order=$_POST['order'];
		$strlen_name=strlen($name);
		if($strlen_name%3==0)
		{
			$html=substr($name,0,6).'<br/>'.substr($name,6);		
		}
		else
		{
			$html=substr($name,0,($strlen_name-6)).'<br/>'.substr($name,-6);
		}
		$sql="insert into `somethingmusic_radio_channel_set` (`name`,`en_name`,`html`,`show`,`order`) values ('$name','$en_name','$html','0','$order')";
		$conn->Execute($sql)or die('set radio channel error!');
		$sql="alter table `somethingmusic_musicplay` add `$en_name` char(1) not null";
		$conn->Execute($sql)or die('add radio channel error!');
		tips('新建频道成功！');
		break;
		
		case 'delete':
		$id=$_GET['id'];
		$en_name=$_GET['en_name'];
		$sql="delete from `somethingmusic_radio_channel_set` where `id`='$id'";
		$conn->Execute($sql) or die('delete radio channel error1!');
		$sql="alter table `somethingmusic_musicplay` drop $en_name";
		$conn->Execute($sql) or die('delete radio channel error2!');
		tips('频道删除成功！');
		break;
		
		case 'ajax':
		$id=$_POST['id'];
		$type=$_POST['type'];
		if($type=='true')
		{
			$sql="update `somethingmusic_radio_channel_set` set `show`='1' where `id`='$id'";
			$conn->Execute($sql);
		}
		else
		{
			$sql="update `somethingmusic_radio_channel_set` set `show`='0' where `id`='$id'";
			$conn->Execute($sql);
		}
		break;
		
		case 'modify':
		$order=$_POST['order'];
		$new_order=$_POST['new_order'];
		$en_name=$_POST['en_name'];
		if($order==$new_order)
		{
			tips('输入与原来的相同！');
		}
		else
		{
			$sql="update `somethingmusic_radio_channel_set` set `order`='$new_order' where `en_name`='$en_name'";
			$conn->Execute($sql)or die('update channel order error!');
			tips('修改成功！');
		}
		break;
		
		default:
		header("location:index.php");
		break;
	}
}
?>