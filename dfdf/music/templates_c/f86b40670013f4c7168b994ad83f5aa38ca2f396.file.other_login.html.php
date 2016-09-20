<?php /* Smarty version Smarty3-b8, created on 2014-02-18 14:43:02
         compiled from "E:\www\music/templates/template/gray\other_login.html" */ ?>
<?php /*%%SmartyHeaderCode:20841530300f608cd66-51655464%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'f86b40670013f4c7168b994ad83f5aa38ca2f396' => 
    array (
      0 => 'E:\\www\\music/templates/template/gray\\other_login.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '20841530300f608cd66-51655464',
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