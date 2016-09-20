<?php
header("Content-type: application/json");
include '../../config.php';

//获取频道
$channel=get_my_array($conn,'select name,en_name from `somethingmusic_radio_channel_set` order by `id` asc');
$count=count($channel);
for($i=0;$i<$count;$i++)
{
	$channel[$i]['name']=urlencode($channel[$i]['name']);
	unset($channel[$i][1]);
	unset($channel[$i][0]);
}
echo urldecode(json_encode($channel));
?>