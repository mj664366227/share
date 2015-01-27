<?php
//读取图片
$root = realpath(dirname(__FILE__)).'/works/';
$uid = intval($_POST['uid']);
$album_id = intval($_POST['album_id']);
if(!$uid || !$album_id){
	return;
}
$path = $root.$uid.'/'.$album_id.'/';
chdir($path);
$rs = array();
$url = 'http://'.$_SERVER['HTTP_HOST'].str_replace('?'.$action,'',$_SERVER['REQUEST_URI']).'works/'.$uid.'/'.$album_id.'/';
foreach(glob('*') as $file){
	$arr = explode('.', $file);
	$rs[] = $url.$file.'?'.$arr[0];
}
echo json_encode($rs);
?>