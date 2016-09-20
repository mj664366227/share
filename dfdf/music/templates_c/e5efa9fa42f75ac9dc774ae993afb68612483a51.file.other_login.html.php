<?php /* Smarty version Smarty3-b8, created on 2012-08-30 21:35:34
         compiled from "F:\htdocs\smu/templates/template/gray\other_login.html" */ ?>
<?php /*%%SmartyHeaderCode:19717503f6c260d64e2-67974452%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'e5efa9fa42f75ac9dc774ae993afb68612483a51' => 
    array (
      0 => 'F:\\htdocs\\smu/templates/template/gray\\other_login.html',
      1 => 1343982379,
    ),
  ),
  'nocache_hash' => '19717503f6c260d64e2-67974452',
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