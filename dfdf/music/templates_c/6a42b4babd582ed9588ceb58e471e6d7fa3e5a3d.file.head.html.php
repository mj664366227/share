<?php /* Smarty version Smarty3-b8, created on 2012-02-24 18:57:11
         compiled from "/home/libmill/domains/lovemusic.cc/public_html//templates/template/gray/head.html" */ ?>
<?php /*%%SmartyHeaderCode:3960217024f476d07552428-80599377%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '6a42b4babd582ed9588ceb58e471e6d7fa3e5a3d' => 
    array (
      0 => '/home/libmill/domains/lovemusic.cc/public_html//templates/template/gray/head.html',
      1 => 1329493041,
    ),
  ),
  'nocache_hash' => '3960217024f476d07552428-80599377',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<script language="javascript">$(function(){ if(document.location=='<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
?skin=gray'||document.location=='<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
?skin=gray<?php echo $_smarty_tpl->getVariable('active')->value;?>
.php'){ var version=$.browser.version;if(version<=7){ var html='<?php echo $_smarty_tpl->getVariable('pms')->value['browser'];?>
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
<ul>
<a href="http://lovemovie.cc" target="_blank" title="视频点播"><li class="vod_out" onmouseout="this.className='vod_out'" onmouseover="this.className='vod_on'"></li></a>
<a href="http://dh.gcu.edu.cn" target="_blank" title="校内导航"><li class="dh_out" onmouseout="this.className='dh_out'" onmouseover="this.className='dh_on'"></li></a>
<a href="http://lib.gcu.edu.cn" target="_blank" title="图书馆"><li class="lib_out" onmouseout="this.className='lib_out'" onmouseover="this.className='lib_on'"></li></a>
<a href="http://www.gcu.edu.cn" target="_blank" title="学院主页"><li class="gzauto_out" onmouseout="this.className='gzauto_out'" onmouseover="this.className='gzauto_on'"></li></a>
</ul>
</div>
</div>
<!--导航-->
<div class="nav">
<ul>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
?skin=gray" title="网站首页"><li class="index_out" onmouseout="this.className='index_out'" onmouseover="this.className='index_on'"></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
radio.php?skin=gray" title="音乐电台" target="_blank"><li class="radio_out" onmouseout="this.className='radio_out'" onmouseover="this.className='radio_on'"></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=all" title="本站资源"><li class="resources_out" onmouseout="this.className='resources_out'" onmouseover="this.className='resources_on'"></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all" title="音乐人介绍"><li class="musician_out" onmouseout="this.className='musician_out'" onmouseover="this.className='musician_on'"></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
introduce.php?skin=gray&action=all" title="音乐推荐文章"><li class="introduce_out" onmouseout="this.className='introduce_out'" onmouseover="this.className='introduce_on'"></li></a>
</ul>
<!--登录部分-->
<div class="login">
<ul>
<li><a href="#" title="亲！你还没有somethingmusic的帐号吗？赶快注册一个吧！">注册</a>
<a href="#" title="使用本站帐号登录">登录</a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
qq/?skin=gray" title="使用QQ帐号登录"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/qq.png" border="0" align="top"/></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
sina/?skin=gray" title="使用新浪微博帐号登录"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/sina.png" border="0" align="top"/></a></li>
</ul>
</div>
</div>