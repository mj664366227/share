<?php include view::dir().'head.php';?>

<div class="table_head"><?php view::load_img('yes.gif');?>文件内容</div>
<?php $arr = explode('.', $path);
$link = '<a href="'.url('system', 'browser').'">网站跟目录</a> >> ';
$p = '';
foreach($arr as $a){
	if(!$a){
		continue;
	}
	$p .= '.'.$a;
	$link .= '<a href="'.url('system', 'browser', 'path='.$p).'">'.$a.'</a> >> ';
}
?>
<div class="position">当前位置：<?php echo substr($link, 0, -3).' >> <a href="'.url('system', 'download', 'filename='.$filename.'&path='.$path).'" title="下载该文件">'.$filename.'</a>'?></div>
<div id="file_content">
<?php if($picture):?>
<img src="<?php echo $picture?>"/>
<?php elseif($swf):?>
<embed src="<?php echo $swf?>"></embed>
<?php elseif($pdf):?>
<iframe src="<?php echo $pdf?>" width="99%" height="700"></iframe>
<?php elseif($content):?>
<textarea><?php echo $content;?></textarea>
<?php endif?>
</div>
<?php include view::dir().'foot.php';?>
