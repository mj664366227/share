<?php /* Smarty version Smarty3-b8, created on 2013-09-22 00:06:03
         compiled from "E:\htdocs\music/templates/template/gray\reg.html" */ ?>
<?php /*%%SmartyHeaderCode:950523dc3eb59d1e3-10535340%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '6e629b59ff66e12e05a421798a8e10c0fc76e9cf' => 
    array (
      0 => 'E:\\htdocs\\music/templates/template/gray\\reg.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '950523dc3eb59d1e3-10535340',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!--注册-->
<div class="register">
<div class="title">注册很简单</div>
<form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
reg.php?skin=gray" method="post">
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr><td align="right" width="100">你的名字</td><td><input type="text" name="username" class="text" id="reg_username" autocomplete="off"/></td></tr>
<tr><td align="right">你的E-Mail地址</td><td><input type="text" name="email" class="text" id="reg_email" autocomplete="off"/></td></tr>
<tr><td align="right">密码</td><td><input type="password" name="pwd1" class="text" id="reg_pwd1" autocomplete="off"/></td></tr>
<tr><td align="right">重复密码</td><td><input type="password" name="pwd2" class="text" id="reg_pwd2" autocomplete="off"/></td></tr>
<tr><td></td><td align="right"><input type="submit" value="注册" class="btn" name="submit" id="reg_submit"/></td></tr>
</table>
</form>
</div>
<?php $_template = new Smarty_Internal_Template("other_login.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>

<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
