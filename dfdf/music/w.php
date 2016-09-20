<?php
include 'config.php';

//获取频道
$channel=get_my_array($conn,'select * from `somethingmusic_radio_channel_set` where `show`=1 order by `count` desc');
$smarty->assign('channel',$channel);

$smarty->display('w.html');
?>