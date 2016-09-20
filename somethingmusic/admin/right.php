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
	
/*获服务器站基本信息*/
$operatersystem=php_uname();//服务器操作系统
$cgi=php_sapi_name();//服务器运行方式
$phpversion=phpversion();//获取PHP版本
$mysql_version=mysql_get_server_info();//获取MySQL版本
$software=$_SERVER['SERVER_SOFTWARE'];//获取服务器软件信息
$smarty->assign("operatersystem",$operatersystem);
$smarty->assign("cgi",$cgi);
$smarty->assign("phpversion",$phpversion);
$smarty->assign("mysql_version",$mysql_version);
$smarty->assign("software",$software);

/*获取本站基本信息*/
$sql="select * from `somethingmusic_system`";
$result=$conn->Execute($sql)or die('select database:somethingmusic_system error!');
if($result->RecordCount()==1)
{
	$webcount=$result->fields['count'];//获取网站访问统计
}
/*获取总专辑数*/
$sql="select * from `somethingmusic_album`";
$result=$conn->Execute($sql)or die('select database:somethingmusic_album error!');
$album=$result->RecordCount();
/*获取总歌曲数*/
$sql="select * from `somethingmusic_musicplay`";
$result=$conn->Execute($sql)or die('select database:somethingmusic_musicplay error!');
$music=$result->RecordCount();
/*文件服务器总数*/
$sql="select * from `somethingmusic_server`";
$result=$conn->Execute($sql)or die('select database:somethingmusic_server error!');
$server=$result->RecordCount();
/*获取评论条数*/
$sql="select * from `somethingmusic_comment`";
$result=$conn->Execute($sql)or die('select database:somethingmusic_comment error!');
$comment=$result->RecordCount();

$smarty->assign("webcount",$webcount);
$smarty->assign("album",$album);
$smarty->assign("music",$music);
$smarty->assign("server",$server);
$smarty->assign("comment",$comment);
$smarty->display('right.html');
}
?>