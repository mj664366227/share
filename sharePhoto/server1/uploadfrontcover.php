<?php
//图片上传
$uid = intval($_POST['uid']);
$file = intval($_POST['file']);
if(!$uid || !$file){
	return;
}
$root = realpath(dirname(__FILE__)).'/frontcover/';
$dir = $root.$uid.'/';
if(!is_dir($dir)){
	mkdir($dir, 777, true);
}
if(intval($GLOBALS['_FILES']['Filedata']['size']) > 512000){
	return;
}

$tempFile = $GLOBALS['_FILES']['Filedata']['tmp_name'];
$targetFile = strtolower($dir.$GLOBALS['_FILES']['Filedata']['name']);
$arr = explode('.', $targetFile);
$file_type = trim($arr[1]);
move_uploaded_file($tempFile, $dir.$file.'.'.$file_type);

$url = 'http://'.$_SERVER['HTTP_HOST'].str_replace('?'.$action,'',$_SERVER['REQUEST_URI']).'frontcover/'.$uid.'/';

$rs['filename'] = $file;
$rs['filetype'] = $file_type;
$rs['url'] = $url;
echo json_encode($rs);
?>