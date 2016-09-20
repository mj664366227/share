<?php
//后台首页右边，网站基本参数显示
session_start();
include "config.php";//载入系统配置文件
include WWW_URL."/lib/Smarty/plugins/modifier.truncate.php";
$login=$_SESSION['login'];
if($login=="")
{
	header("location:index.php");
}
else
{
	$path=$_GET['path'].'/';
	$dir=WWW_URL.'/';
	switch($_GET['action'])
	{
		case 'list':
			if($path)
			{
				$dir.=$path;
			}
			chdir($dir);
			$type=1;
		break;
		
		case 'scan':
			$filename=$_GET['filename'];
			$file_arr=explode('.',$filename);
			if($file_arr[1]!='jpg'&&$file_arr[1]!='jpeg'&&$file_arr[1]!='png'&&$file_arr[1]!='ico')
			{
				$file_type='txt';
				$doc=file_get_contents($dir.$path.$filename);
			}
			else
			{
				$file_type='pic';
				$doc=BASE_URL.'admin/upload/'.$filename;
			}
			$type=2;
		break;
	}
	$file=folder_sort(glob('*'));
	$path_arr=explode('/',$path);
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="templates/template/css.css" type="text/css" />
<title>文件浏览器</title>
</head>
<body>
<?php if($type==1):?>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>文件浏览器</a></div>
  <table cellpadding="0" cellspacing="0"><tr><td>
  <a href="file_scan.php?action=list" title="网站根目录">网站根目录</a> 
  <?php if($path_arr):?>
    <?php foreach($path_arr as $pp):?>
		<? if($pp):?> >> <a href="file_scan.php?action=list&path=<?=cut($path,$pp)?>" title="<?=$pp?>"><?=$pp?></a><? endif?>
    <?php endforeach;endif?>
  <br/><br/>
  <ul class="file_scan">
  <?php if($file):?>
  <?php foreach($file as $f): ?>
  <a href="file_scan.php?action=<?php if(substr($f,strlen($f)-1)==1):?>list&path=<?=$path.substr($f,0,-1)?><?php elseif(substr($f,strlen($f)-1)==2):?>scan&path=<?=substr($path,0,-1)?>&filename=<?=substr($f,0,-1)?><?php endif?>" title="<?=substr($f,0,-1)?><?php if(substr($f,strlen($f)-1)==2):?> 文件大小：<?=size(filesize($dir.substr($f,0,-1)));endif?>">
  <li>
  <? $ty=explode('.',$f);?>
  <? if(!$ty[1]):?><img src="<?=BASE_URL?>admin/templates/template/images/folder.png" width="64" height="64"/>
  <? elseif($ty[1]=='php2'):?><img src="<?=BASE_URL?>admin/templates/template/images/php.png" width="64" height="64"/>
  <? elseif($ty[1]=='jpeg2'||$ty[1]=='jpg2'||$ty[1]=='png2'||$ty[1]=='ico2'||$ty[1]=='gif2'):?><img src="<?=BASE_URL?>admin/templates/template/images/pic.png" width="64" height="64"/>
  <? elseif($ty[1]=='css2'):?><img src="<?=BASE_URL?>admin/templates/template/images/css.png" width="64" height="64"/>
  <? elseif($ty[1]=='js2'):?><img src="<?=BASE_URL?>admin/templates/template/images/js.png" width="64" height="64"/>
  <? elseif($ty[1]=='html2'||$ty[1]=='shtml2'||$ty[1]=='htm2'):?><img src="<?=BASE_URL?>admin/templates/template/images/html.png" width="64" height="64"/>
  <? elseif($ty[1]=='swf2'):?><img src="<?=BASE_URL?>admin/templates/template/images/swf.png" width="64" height="64"/>
  <? elseif($ty[1]=='txt2'||$ty[1]=='htacess2'):?><img src="<?=BASE_URL?>admin/templates/template/images/txt.gif" width="64" height="64"/>
  <? elseif($ty[1]=='xml2'):?><img src="<?=BASE_URL?>admin/templates/template/images/xml.png" width="64" height="64"/>
  <? endif?>
  <br/><?=smarty_modifier_truncate(substr($f,0,-1),15)?>
  </li></a>
  <?php endforeach;endif?>
  </ul></td></tr></table>
</div>
<?php elseif($type==2):?>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>查看文件内容</a></div>
  <table cellspacing="0">
    <tr id="msg"><td>
    <a href="file_scan.php?action=list" title="网站根目录">网站根目录</a> 
    <?php if($path_arr):?>
    <?php foreach($path_arr as $pp):?>
		<? if($pp):?> >> <a href="file_scan.php?action=list&path=<?=cut($path,$pp)?>" title="<?=$pp?>"><?=$pp?></a><? endif?>
    <?php endforeach;endif?>
  <br/><br/>
    <? if($file_type=='txt'):?>
    <textarea style="width:90%;height:600px;overflow:scroll;overflow-x:hidden"><?=$doc?></textarea>
    <?php elseif($file_type=='pic'):?>
    <img src="<?=$doc?>"/>
	<?php endif?>
    </td></tr>
  </table>
</div>
<?php endif?>
</body>
</html>
<style>
.file_scan{ background-color:#FFFFFF; padding:0; margin:0}
.file_scan li{ list-style:none; width:100px; height:100px;float:left; margin:0 0 3px 3px; text-align:center}
.file_scan a{ text-decoration:none; color:#000}
table tr td a {color:#09F;text-decoration:underline;line-height:20px}
table tr td a:hover {color:#F00;font-weight:bold;text-decoration:none}
</style>