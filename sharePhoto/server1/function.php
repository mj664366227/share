<?php
/**
 * 过滤字符串
 * @param $str 字符串
 * @return 过滤好的字符串
 */
function filter($str){
	if(!$str){
		return;
	}
	if(is_array($str)) {
		foreach ($str as $key => $s) {
			$str[$key] = fliter($s);
		}
	} else {
		$str = htmlspecialchars($str);
		$str = strip_tags($str);
		$str = trim($str);
		$str = xss($str);
	}
	return $str;
}

/**
 * 防XSS注入
 * @param $str
 */
function xss($str){
	if (is_array($str)){
		foreach($str as $k => $v){
			$str[$k] = crack_xss($v);
		}
	} else {
		$farr = array(
		'/\\s+/',
		'/<(\\/?)(script|i?frame|style|html|body|title|link|meta|object|\\?|\\%)([^>]*?)>/isU',
		'/(<[^>]*)on[a-zA-Z]+\s*=([^>]*>)/isU',
		);
		$str = preg_replace($farr,'',$str);
		$str = addslashes($str);
	}
	return $str;
}

/**
 * 把大图缩略到缩略图指定的范围内，不留白（原图会居中缩放，把超出的部分裁剪掉）
 * @param $f 源图
 * @param $t 生成目标文件名
 * @param $tw 目标图宽度
 * @param $th 目标图高度
 */
function thumb_cut($f, $t, $tw, $th){
	$temp = array(1=>'gif', 2=>'jpeg', 3=>'png');
	list($fw, $fh, $tmp) = getimagesize($f);
	if(!$temp[$tmp]){
		return false;
	}
	$tmp = $temp[$tmp];
	$infunc = "imagecreatefrom$tmp";
	$outfunc = "image$tmp";
	$fimg = $infunc($f);
	if($fw/$tw > $fh/$th){
		$zh = $th;
		$zw = $zh*($fw/$fh);
		$_zw = ($zw-$tw)/2;
	}else{
		$zw = $tw;
		$zh = $zw*($fh/$fw);
		$_zh = ($zh-$th)/2;
	}
	$zimg = imagecreatetruecolor($zw, $zh);
	imagecopyresampled($zimg, $fimg, 0,0, 0,0, $zw,$zh, $fw,$fh);
	$timg = imagecreatetruecolor($tw, $th);
	imagecopyresampled($timg, $zimg, 0,0, 0+$_zw,0+$_zh, $tw,$th, $zw-$_zw*2,$zh-$_zh*2);
	if($outfunc($timg, $t)){
		return true;
	}
	return false;
}
?>