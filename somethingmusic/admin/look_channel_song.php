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
	$action=clean($_GET['action']);
	$channel=clean($_GET['channel']);
	if($channel=='') $channel='ktv';
	switch($action)
	{
		case 'index':
		//获取电台
		$sql='select id,name,en_name as en from somethingmusic_radio_channel_set where `id`!=2 order by `id` asc';
		$channels=get_my_array($conn,$sql);
		$c=showpage1($conn,'somethingmusic_musicplay','',10,'`'.$channel.'`=1','index&channel='.$channel);
		$smarty->assign('channel',$channels);
		$smarty->assign('ch',$channel);
		$smarty->assign('res',$c['page_arr']);
		$smarty->assign('p',$c['page_infor']);
		$smarty->display('look_channel_song.html');
		break;
	}
}
?>