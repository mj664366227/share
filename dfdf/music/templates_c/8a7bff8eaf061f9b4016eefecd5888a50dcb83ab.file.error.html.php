<?php /* Smarty version Smarty3-b8, created on 2013-09-29 13:16:30
         compiled from "E:\htdocs\music/templates/template/deafault\error.html" */ ?>
<?php /*%%SmartyHeaderCode:81025247b7aee0d8a9-87356517%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '8a7bff8eaf061f9b4016eefecd5888a50dcb83ab' => 
    array (
      0 => 'E:\\htdocs\\music/templates/template/deafault\\error.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '81025247b7aee0d8a9-87356517',
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
<link rel="stylesheet" href="templates/template/deafault/css/css.css" type="text/css" />
<!--[if IE]><link rel="stylesheet" href="templates/template/deafault/css/jquery.fancybox-1.3.1-ie.css" type="text/css" /><![endif]-->
<!--[if IE]><link rel="stylesheet" href="templates/template/deafault/css/all_ie.css" type="text/css" /><![endif]-->
<!--[if IE 6]><link rel="stylesheet" href="templates/template/deafault/css/ie6.css" type="text/css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="templates/template/deafault/css/ie7.css" type="text/css" /><![endif]-->
<title><?php echo $_smarty_tpl->getVariable('s')->value['webtitle'];?>
</title>
</head>
<?php }} ?>
<body>
<!--初始化播放器-->
<div id="jquery_jplayer"></div>
<!--头部-->
<div class="header">
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['webtitle'];?>
"><img src="pic.php?src=templates/template/deafault/images/logo.png&w=107&h=35&zc=0&q=100" width="107" height="35" alt="logo" border="0"/></a>
<ul>
<li><a href="http://dh.gzauto.edu.cn/vod" target="_blank">点播系统</a></li>
<li><a href="http://qcc.gzauto.edu.cn" target="_blank">汽车文化</a></li>
<li><a href="#">飞车坊▼</a></li>
</ul>
</div>
<!--导航栏-->
<div class="nav">
<ul>
<li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
" title="网站首页"><?php if ($_smarty_tpl->getVariable('active')->value=='index'){?><font color="#ff0066">首页</font><?php }else{ ?>首页<?php }?></a></li>
<li><a href="radio.php" title="音乐电台"><?php if ($_smarty_tpl->getVariable('active')->value=='radio'){?><font color="#ff0066">电台</font><?php }else{ ?>电台<?php }?></a></li>
<li><a href="resources.php" title="本站资源"><?php if ($_smarty_tpl->getVariable('active')->value=='resources'||$_smarty_tpl->getVariable('active')->value=='album'){?><font color="#ff0066">资源</font><?php }else{ ?>资源<?php }?></a></li>
<li><a href="musician.php?action=all" title="音乐人介绍"><?php if ($_smarty_tpl->getVariable('active')->value=='musician'){?><font color="#ff0066">音乐人</font><?php }else{ ?>音乐人<?php }?></a></li>
<li><a href="introduce.php?action=all" title="音乐推荐文章"><?php if ($_smarty_tpl->getVariable('active')->value=='introduce'||$_smarty_tpl->getVariable('active')->value=='content'){?><font color="#ff0066">推荐</font><?php }else{ ?>推荐<?php }?></a></li>
</ul>
<!--搜索栏-->
<div class="search">
<form action="">
<input type="text" class="search_input"/>
<div class="submit" title="搜！"><input type="image" src="templates/template/deafault/images/search.png" width="18" height="18"/></div>
</form></div>
</div>
<style>
.s a{ color:#ff0066; text-decoration:none}
.s a:hover { text-decoration:underline}
</style>
<!--网站主体-->
<div class="body">
<div class="main" style="height:700px">
<?php if ($_smarty_tpl->getVariable('type')->value=='error_yzm'){?>

<div style="width:100%; text-align:center; font-size:14px; margin-top:300px" class="s"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/deafault/images/error.png" width="30" height="30" alt="验证码错误" align="absmiddle" style="margin-right:10px"/><?php echo $_smarty_tpl->getVariable('str')->value;?>
您可以点击&nbsp;<a href="content.php?id=<?php echo $_smarty_tpl->getVariable('sss')->value;?>
#say">后退</a>&nbsp;重新填写，或者返回&nbsp;<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
">首页</a>&nbsp;。</div>
<?php }elseif($_smarty_tpl->getVariable('type')->value=='not_found'){?>

<?php }elseif($_smarty_tpl->getVariable('type')->value=='topic'){?>
<div style="width:100%; text-align:center; font-size:14px; margin-top:300px" class="s"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/deafault/images/error.png" width="30" height="30" alt="验证码错误" align="absmiddle" style="margin-right:10px"/><?php echo $_smarty_tpl->getVariable('str')->value;?>
您可以点击&nbsp;<a href="<?php echo $_smarty_tpl->getVariable('sss')->value;?>
#say">后退</a>&nbsp;重新填写，或者返回&nbsp;<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
">首页</a>&nbsp;。</div>
<?php }?>
<?php $_template = new Smarty_Internal_Template('foot.html', $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
