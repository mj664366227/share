<?php /* Smarty version Smarty3-b8, created on 2012-02-17 22:58:18
         compiled from "/home/libmill/domains/lovemusic.cc/public_html/templates/template/deafault/user.index.html" */ ?>
<?php /*%%SmartyHeaderCode:11291353254f3e6b0a071591-92781625%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'abef94f5b271322e6bb5eb1e4e350458a03ab04b' => 
    array (
      0 => '/home/libmill/domains/lovemusic.cc/public_html/templates/template/deafault/user.index.html',
      1 => 1329490448,
    ),
  ),
  'nocache_hash' => '11291353254f3e6b0a071591-92781625',
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