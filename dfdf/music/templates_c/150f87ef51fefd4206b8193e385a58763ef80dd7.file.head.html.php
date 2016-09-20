<?php /* Smarty version Smarty3-b8, created on 2012-03-30 02:12:10
         compiled from "E:\wwwroot\smu/templates/template/green\head.html" */ ?>
<?php /*%%SmartyHeaderCode:289664f75167a8e9dc2-99041644%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '150f87ef51fefd4206b8193e385a58763ef80dd7' => 
    array (
      0 => 'E:\\wwwroot\\smu/templates/template/green\\head.html',
      1 => 1328198408,
    ),
  ),
  'nocache_hash' => '289664f75167a8e9dc2-99041644',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('system')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="keywords" content="<?php echo $_smarty_tpl->getVariable('s')->value['seo'];?>
"/>
<meta http-equiv="description" content="<?php echo $_smarty_tpl->getVariable('s')->value['description'];?>
"/>
<link rel="stylesheet" href="templates/template/green/css/css.css" type="text/css" />
<link rel="stylesheet" href="templates/template/green/css/jquery.fancybox-1.3.1.css" type="text/css" />
<!--[if IE]><link rel="stylesheet" href="templates/template/green/css/jquery.fancybox-1.3.1-ie.css" type="text/css" /><![endif]-->
<!--[if IE]><link rel="stylesheet" href="templates/template/green/css/all_ie.css" type="text/css" /><![endif]-->
<!--[if IE 6]><link rel="stylesheet" href="templates/template/green/css/ie6.css" type="text/css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="templates/template/green/css/ie7.css" type="text/css" /><![endif]-->
<!--[if IE 6]><script type="text/javascript" src="templates/template/green/js/DD_belatedPNG_0.0.8a.js"></script>
<script type="text/javascript">
DD_belatedPNG.fix('*');
</script>
<![endif]-->
<!--<script type="text/javascript" src="templates/template/green/js/jquery.easing-1.3.pack.js"></script>
<script type="text/javascript" src="templates/template/green/js/jquery.mousewheel-3.0.2.pack.js"></script>-->
<script type="text/javascript">
/*$(document).ready(function(){
$(".user_control").fancybox({

				'height'			: '27%',
				'autoScale'			: false,
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'type'				: 'iframe',
			});	
});*/
</script>
<title><?php echo $_smarty_tpl->getVariable('s')->value['webtitle'];?>
</title>
</head>
<?php }} ?>
<body>
<div class="head">
<div class="logo"></div>
<div class="rightside">
<div class="up">
<div class="l"></div>
<div class="m">
<div class="nav">
<ul>
<li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
" title="首页">首页</a></li>
<li><a href="radio.php" title="电台">电台</a></li>
<li><a href="resources.php" title="资源">资源</a></li>
<li><a href="#" title="推荐">推荐</a></li>
</ul>
</div>
<div class="search">
<form action=""><input type="text" class="searching"/>
<input type="image" src="pic.php?src=templates/template/green/images/search_btn.png&w=22&h=20&zc=0&q=100" width="22" height="20" class="search_btn"/>
</form>
</div>
</div>
<div class="r"></div>
</div>
<div class="down"><div class="title"></div></div>
</div>
<!--
<div class="user_center"> 
<a href="templates/template/green/user_register.html" class="user_control">注册</a>
<a href="templates/template/green/user_login.html" class="user_control">登录</a>
</div>
-->
</div>
<div class="main">