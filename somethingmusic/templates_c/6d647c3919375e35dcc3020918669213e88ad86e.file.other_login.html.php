<?php /* Smarty version Smarty3-b8, created on 2016-09-20 22:18:52
         compiled from "D:\nginx/www/share.git/somethingmusic/templates/template/gray\other_login.html" */ ?>
<?php /*%%SmartyHeaderCode:2891357e1454c241172-19007261%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '6d647c3919375e35dcc3020918669213e88ad86e' => 
    array (
      0 => 'D:\\nginx/www/share.git/somethingmusic/templates/template/gray\\other_login.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '2891357e1454c241172-19007261',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<div class="other_login">
<div class="title">其他方式登录</div>
<ul>
<li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
qq/oauth/qq_login.php?skin=gray"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/sina_login.png" border="0"/></a></li>
<li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
sina/?skin=gray"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/qq_login.png" border="0"/></a></li>
</ul>
</div>