<?php
header('Content-type:text/xml;charset=utf-8');
include '../../config.php';

//获取频道
$channel=get_my_array($conn,'select name,en_name from `somethingmusic_radio_channel_set` order by `id` asc');
echo '<?xml version="1.0" encoding="utf-8"?><list>';
foreach ($channel as $c)
{
	echo '<channel en_name="'.$c['en_name'].'">'.$c['name'].'</channel>';
}
echo '</list>';
?>