<?php
//图片上传
$uid = intval($_POST['uid']);
$album_id = intval($_POST['album_id']);
if(!$uid || !$album_id){
	return;
}
$root = realpath(dirname(__FILE__)).'/works/';
$dir = $root.$uid.'/'.$album_id.'/';
if(!is_dir($dir)){
	mkdir($dir, 777, true);
}
if(intval($GLOBALS['_FILES']['Filedata']['size']) > 2097160){
	return;
}

$tempFile = $GLOBALS['_FILES']['Filedata']['tmp_name'];
$targetFile = strtolower($dir.$GLOBALS['_FILES']['Filedata']['name']);
$arr = explode('.', $targetFile);
$file_type = trim($arr[1]);
move_uploaded_file($tempFile, $dir.getMicrotime().'.'.$file_type);

function getMicrotime() {
	$time = explode(' ', microtime());
	return str_replace('.','',$time[1].$time[0]);
}
?>