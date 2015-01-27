<?php include view::dir().'head.php';?>

<div class="table_head"><?php view::load_img('yes.gif');?>文件浏览器</div>
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
<div class="position">当前位置：<?php echo substr($link, 0, -3)?></div>
<?php if($list):?>
<ul class="file_browser">
  <?php foreach($list as $file):if(!check::is_utf8($file)){$file=iconv('GBK', CHARSET_HTML, $file);}?>
  <?php $filetype = explode('.',$file);$count=count($filetype);$count>1?$filetype=$filetype[$count-1]:$filetype='folder';?>
  <li>
    <?php if($filetype == 'jpg' || $filetype == 'jpeg' || $filetype == 'png' || $filetype == 'gif' || $filetype == 'bmp'):?>
    <img src="<?php echo BASE_URL.'..'.str_replace('.', '/', $path).$file?>" <?php if($filetype != 'gif'):?>width="60" height="60"<?php endif?>/>
    <?php else:?>
    <?php view::load_img($filetype.'.png',60,60);?>
    <?php endif?>
    <br/>
    <?php if(strrpos($file, '.') === false):?>
    <div><a href="<?php echo url('system', 'browser', 'path='.$path.$file)?>" title="<?php echo $file?>"><?php echo $file?></a></div>
    <?php else:?>
    <div><a href="<?php echo url('system', 'readfile', 'filename='.$file.'&path='.$path)?>" title="<?php echo $file?>"><?php echo $file?></a></div>
    <?php endif?>
  </li>
  <?php endforeach?>
</ul>
<?php endif?>
<?php include view::dir().'foot.php';?>
