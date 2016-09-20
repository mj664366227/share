<?php
include 'config.php';
$channel_id=clean($_GET['channel']);//自定义频道
$id=intval($_GET['id']);//自定义歌曲

//获取频道
$channel=get_my_array($conn,'select * from `somethingmusic_radio_channel_set` where `show`=1 order by `count` desc');
if(!$channel_id || $channel_id=='undefined' || UID <= 0){
	$channel_id = $_COOKIE['channel_id'];
}
if(!$channel_id || $channel_id=='undefined' || UID <= 0){
	$channel_id = $channel[0]['id'];
}
setcookie('channel_id',$channel_id,time()+999999);
$smarty->assign('channel',$channel);
$smarty->assign('channel_id',$channel_id);
$smarty->assign('id',$id);
$smarty->assign('num',count($channel));

//获取首卷语
$sjy=get_my_array($conn,'select text from `somethingmusic_sjy` order by rand()');
$smarty->assign('sjy',$sjy[0][0]);

$smarty->display('radio.html');
?>