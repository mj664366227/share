<?php
//读取单张图片
$root = realpath(dirname(__FILE__)).'/works/';
$uid = intval($_POST['uid']);
$album_id = intval($_POST['album_id']);
$file = trim($_POST['file']);
if(!$uid || !$album_id || !is_numeric($file)){
	return;
}
$path = $root.$uid.'/'.$album_id.'/';
chdir($path);
$rs = array();
$url = 'http://'.$_SERVER['HTTP_HOST'].str_replace('?'.$action,'',$_SERVER['REQUEST_URI']).'works/'.$uid.'/'.$album_id.'/';
$files = glob('*.{jpg,jpeg,png,gif}', GLOB_BRACE);
$index = array_search($file.'.jpg', $files);

$rs['url'] = $files[$index];
$rs['next'] = $files[$index + 1];
$rs['prev'] = $files[$index - 1];
$rs['base'] = $url;
$rs['cur'] = $index + 1;
$rs['total'] = count($files);

echo json_encode($rs);
?>