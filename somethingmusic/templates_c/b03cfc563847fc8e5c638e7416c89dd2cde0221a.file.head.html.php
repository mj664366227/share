<?php /* Smarty version Smarty3-b8, created on 2016-09-21 15:41:41
         compiled from "D:\nginx/www/share.git/somethingmusic/templates/template/gray\head.html" */ ?>
<?php /*%%SmartyHeaderCode:2377557e2aa35ea58c3-34192804%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'b03cfc563847fc8e5c638e7416c89dd2cde0221a' => 
    array (
      0 => 'D:\\nginx/www/share.git/somethingmusic/templates/template/gray\\head.html',
      1 => 1474472499,
    ),
  ),
  'nocache_hash' => '2377557e2aa35ea58c3-34192804',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta property="qc:admins" content="121151554313641251446375" />
<link rel="stylesheet" href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/css/css.css?20120212" type="text/css" />
<!--[if IE 6]>
<link rel="stylesheet" href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/css/ie6.css?20120212" type="text/css" />
<script type="text/javascript" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/js/DD_belatedPNG_0.0.8a.js?20120212"></script><script type="text/javascript">DD_belatedPNG.fix('*');</script>
<![endif]-->
<script type="text/javascript" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/js/jquery-1.5.1.min.js?20120212"></script>
<script type="text/javascript" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/js/common.js?20120212"></script>
<script type="text/javascript" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/js/jquery.jplayer.js?20120212"></script>
<script language="javascript">$(function(){ $('input').attr('autocomplete','off');if(document.location=='<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
?skin=gray'||document.location=='<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
index.php?skin=gray'){ var version=$.browser.version;if(version<=7){ var html='<?php echo $_smarty_tpl->getVariable('pms')->value['browser'];?>
';$('.pms').append(html).css({ <?php echo $_smarty_tpl->getVariable('pms')->value['css'];?>
 })}}})</script>
<link rel="shortcut icon" href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
favicon.ico"/>
<title><?php echo $_smarty_tpl->getVariable('web_title')->value;?>
</title>
</head>
<body>
<div id="jquery_jplayer"></div>
<div class="pms"></div>
<div class="body">
<div class="header">
<!--logo-->
<div class="logo"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
?skin=gray" title="<?php echo $_smarty_tpl->getVariable('web_title')->value;?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/logo.png" border="0"/></a></div>
<!--搜索框-->
<div class="search">
<form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
search.php?skin=gray&action=search" method="post">
<input type="text" class="search_input" id="search_input" name="search_input" onclick="value=''" autocomplete="off" <?php if ($_smarty_tpl->getVariable('key_words')->value){?>value="<?php echo $_smarty_tpl->getVariable('key_words')->value;?>
"<?php }?> x-webkit-speech lang="zh-CN" x-webkit-grammar="bUIltin:search"/>
<div id="submit" title="搜！"><input type="image" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/search.png" width="24" height="24" id="search_submit"/></div>
<div class="result"></div>
</form>
</div>
<!--顶部链接-->
<div class="top">

</div>
</div>
<!--导航-->
<div class="nav">
<ul>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
?skin=gray" title="网站首页"><li><?php if ($_smarty_tpl->getVariable('active')->value=='index'){?><font color="#d06e6f">首页</font><?php }else{ ?>首页<?php }?></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
radio.php?skin=gray" title="音乐电台" target="_blank"><li><?php if ($_smarty_tpl->getVariable('active')->value==''){?><font color="#d06e6f">电台</font><?php }else{ ?>电台<?php }?></li></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=all" title="本站资源"><li><?php if ($_smarty_tpl->getVariable('active')->value=='resources'||$_smarty_tpl->getVariable('active')->value=='album'||$_smarty_tpl->getVariable('active')->value=='search'){?><font color="#d06e6f">资源</font><?php }else{ ?>资源<?php }?></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all" title="音乐人介绍"><li><?php if ($_smarty_tpl->getVariable('active')->value=='musician'){?><font color="#d06e6f">音乐人</font><?php }else{ ?>音乐人<?php }?></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
introduce.php?skin=gray&action=all" title="音乐推荐文章"><li><?php if ($_smarty_tpl->getVariable('active')->value=='introduce'||$_smarty_tpl->getVariable('active')->value=='content'){?><font color="#d06e6f">推荐</font><?php }else{ ?>推荐<?php }?></li></a>
</ul>
<?php $_template = new Smarty_Internal_Template("login.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>

</div>