<?php /* Smarty version Smarty3-b8, created on 2013-09-08 09:08:52
         compiled from "E:\htdocs\music/templates/template/gray\other_login.html" */ ?>
<?php /*%%SmartyHeaderCode:18384522bce24547092-27406782%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '5f92a7facab33f8db35a952b3baa39ed85a0d918' => 
    array (
      0 => 'E:\\htdocs\\music/templates/template/gray\\other_login.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '18384522bce24547092-27406782',
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