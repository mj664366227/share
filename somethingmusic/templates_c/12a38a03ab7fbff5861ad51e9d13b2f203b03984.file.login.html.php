<?php /* Smarty version Smarty3-b8, created on 2016-09-20 21:21:14
         compiled from "D:\nginx/www/share.git/somethingmusic/templates/template/gray\login.html" */ ?>
<?php /*%%SmartyHeaderCode:3033457e137ca712971-56582584%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '12a38a03ab7fbff5861ad51e9d13b2f203b03984' => 
    array (
      0 => 'D:\\nginx/www/share.git/somethingmusic/templates/template/gray\\login.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '3033457e137ca712971-56582584',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!--登录部分-->
<div class="login" uid="<?php echo $_smarty_tpl->getVariable('uid')->value;?>
">
<?php if ($_smarty_tpl->getVariable('libmill')->value){?><?php if ($_smarty_tpl->getVariable('active')->value!='api'){?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=index" title="进入我的后台">进入后台</a>&nbsp;<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
logout.php?skin=gray" title="退出" onclick="return confirm('亲，你真的要退出吗？')">退出</a><?php }?><?php }else{ ?><?php if ($_smarty_tpl->getVariable('active')->value!='reg'){?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
reg.php?skin=gray" title="亲！你还没有somethingmusic的帐号吗？赶快注册一个吧！">注册</a><?php }else{ ?><font color="#d06e6f">注册</font><?php }?>
&nbsp;
<?php if ($_smarty_tpl->getVariable('active')->value!='login'){?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
login.php?skin=gray" title="使用本站帐号登录">登录</a><?php }else{ ?><font color="#d06e6f">登录</font><?php }?>

<?php }?>

<?php if ($_smarty_tpl->getVariable('active')->value=='api'){?>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=index" title="个人主页">个人主页</a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=album_list" title="音乐分享">音乐分享</a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
logout.php?skin=gray" title="退出" onclick="return confirm('亲，你真的要退出吗？')">退出</a>
<?php }?>
<!--
<?php if (!$_smarty_tpl->getVariable('qq')->value&&!$_smarty_tpl->getVariable('libmill')->value&&!$_smarty_tpl->getVariable('sina')->value){?>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
qq/oauth/qq_login.php?skin=gray" title="使用QQ帐号登录"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/qq.png" border="0" align="top" width="76" height="23"/></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
sina/?skin=gray" title="使用新浪微博帐号登录"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/sina.png" border="0" align="top" width="105" height="23"/></a>
<?php }else{ ?>

<?php if (!$_smarty_tpl->getVariable('sync_qq')->value){?>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
qq/oauth/qq_login.php?skin=gray" title="同步QQ帐号"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/qq_sync.png" border="0" align="top" width="76" height="23"/></a>
<?php }?>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
sina/?skin=gray" title="同步新浪微博"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/sina_sync.png" border="0" align="top" width="105" height="23"/></a>
<?php }?>
-->

<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
index.php?skin=deafault">旧版皮肤</a>

</div>