<?php /* Smarty version Smarty3-b8, created on 2013-03-27 20:41:17
         compiled from "F:/PHPNOW/vhosts/music//templates/template/deafault\head.html" */ ?>
<?php /*%%SmartyHeaderCode:312425152e8ed03ea39-20603973%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'ef0083a92fb38785eff1d3ce96d941ec7161cd7e' => 
    array (
      0 => 'F:/PHPNOW/vhosts/music//templates/template/deafault\\head.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '312425152e8ed03ea39-20603973',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="<?php echo $_smarty_tpl->getVariable('keyword')->value;?>
" />
<meta name="description" content="<?php echo $_smarty_tpl->getVariable('description')->value;?>
" />
<link rel="shortcut icon" href="favicon.ico"/>
<link rel="stylesheet" href="templates/template/deafault/css/css.css" type="text/css" />
<!--[if IE]><link rel="stylesheet" href="templates/template/deafault/css/all_ie.css" type="text/css" /><![endif]-->
<!--[if IE 6]><link rel="stylesheet" href="templates/template/deafault/css/ie6.css" type="text/css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="templates/template/deafault/css/ie7.css" type="text/css" /><![endif]-->
<title><?php echo $_smarty_tpl->getVariable('web_title')->value;?>
</title>
</head>
<body>
<!--初始化播放器-->
<div id="jquery_jplayer"></div>
<!--头部-->
<div class="header">
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
/?skin=deafault" title="somethingmusic音乐电台"><img src="templates/template/deafault/images/logo.png" width="107" height="35" alt="logo" border="0"/></a>


</div>
<?php if ($_smarty_tpl->getVariable('active')->value=='radio'){?>
<?php }else{ ?>
<!--导航栏-->
<div class="nav">
<ul>
<li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
?skin=deafault" title="网站首页"><?php if ($_smarty_tpl->getVariable('active')->value=='index'){?><font color="#ff0066">首页</font><?php }else{ ?>首页<?php }?></a></li>
<li><a href="radio.php?skin=deafault" title="音乐电台" target="_blank">电台</a></li>
<li><a href="resources.php?skin=deafault&action=all" title="本站资源"><?php if ($_smarty_tpl->getVariable('active')->value=='resources'||$_smarty_tpl->getVariable('active')->value=='album'||$_smarty_tpl->getVariable('active')->value=='search'){?><font color="#ff0066">资源</font><?php }else{ ?>资源<?php }?></a></li>
<li><a href="musician.php?skin=deafault&action=all" title="音乐人介绍"><?php if ($_smarty_tpl->getVariable('active')->value=='musician'){?><font color="#ff0066">音乐人</font><?php }else{ ?>音乐人<?php }?></a></li>
<li><a href="introduce.php?skin=deafault&action=all" title="音乐推荐文章"><?php if ($_smarty_tpl->getVariable('active')->value=='introduce'||$_smarty_tpl->getVariable('active')->value=='content'){?><font color="#ff0066">推荐</font><?php }else{ ?>推荐<?php }?></a></li>
<?php if ($_smarty_tpl->getVariable('active')->value=='index'){?><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li>&nbsp;</li><li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
?skin=gray">新版皮肤</a></li><?php }?>
</ul>
<!--搜索栏-->
<div class="search">
<form action="search.php?skin=deafault&action=search" method="post">
<input type="text" class="search_input" id="search_input" name="search_input" onclick="value=''" autocomplete="off"/>
<div class="submit" title="搜！"><input type="image" src="templates/template/deafault/images/search.png" width="18" height="18" id="search_submit"/></div>
</form></div>
</div>
<?php }?>
<!--网站主体-->
<div class="body">
<div class="main">
<div class="result"></div>