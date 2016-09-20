<?php /* Smarty version Smarty3-b8, created on 2012-05-14 23:57:54
         compiled from "E:\wwwroot\smu/templates/template/gray\other_login.html" */ ?>
<?php /*%%SmartyHeaderCode:86384fb12b8223b704-94243440%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '1707558fd03727b4254b182727c5778c8439bc39' => 
    array (
      0 => 'E:\\wwwroot\\smu/templates/template/gray\\other_login.html',
      1 => 1335712291,
    ),
  ),
  'nocache_hash' => '86384fb12b8223b704-94243440',
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