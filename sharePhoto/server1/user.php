<?php
//用户头像图片
$uid = intval($_POST['uid']);
if(!$uid){
	return;
}
$root = realpath(dirname(__FILE__)).'/user/';
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
$file = $dir.$uid.'.'.$file_type;
move_uploaded_file($tempFile, $file);

thumb_cut($file, $dir.$uid.'_64_64.'.$file_type, 64, 64);
thumb_cut($file, $dir.$uid.'_30_30.'.$file_type, 30, 30);
thumb_cut($file, $dir.$uid.'_120_120.'.$file_type, 120, 120);
unlink($file);

echo $file_type;
?>