<?php /* Smarty version Smarty3-b8, created on 2012-04-11 17:30:15
         compiled from "E:\wwwroot\smu/templates/template/deafault\user.index.html" */ ?>
<?php /*%%SmartyHeaderCode:221394f85bfa735ead2-38805873%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'cd70c51e0cecb824f1163d73d20b98125c308544' => 
    array (
      0 => 'E:\\wwwroot\\smu/templates/template/deafault\\user.index.html',
      1 => 1329490448,
    ),
  ),
  'nocache_hash' => '221394f85bfa735ead2-38805873',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?action=user&mod=reg" method="post">
<table>
<tr><td>用户名：</td><td><input type="text" name="username"/></td></tr>
<tr><td>密  码：</td><td><input type="password" name="pwd"/></td></tr>
<tr><td>邮  箱：</td><td><input type="text" name="email"/></td></tr>
<tr><td></td><td><input type="submit" value="提交"/></td></tr>
</table>
</form>