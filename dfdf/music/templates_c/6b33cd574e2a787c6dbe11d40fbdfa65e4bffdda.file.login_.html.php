<?php /* Smarty version Smarty3-b8, created on 2013-03-02 22:01:40
         compiled from "F:/PHPNOW/vhosts/music/templates/template/gray\login_.html" */ ?>
<?php /*%%SmartyHeaderCode:332851320644d6d7c8-97989209%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '6b33cd574e2a787c6dbe11d40fbdfa65e4bffdda' => 
    array (
      0 => 'F:/PHPNOW/vhosts/music/templates/template/gray\\login_.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '332851320644d6d7c8-97989209',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!--登录-->
<div class="register">
<div class="title">登录</div>
<form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
login.php?skin=gray" method="post">
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr><td align="right" width="100">帐号</td><td><input type="text" name="username" class="text" id="login_username" autocomplete="off"/></td></tr>
<tr><td align="right">密码</td><td><input type="password" name="pwd" class="text" id="login_pwd" autocomplete="off"/></td></tr>
<tr><td></td><td align="right"><input type="submit" value="登录" class="btn" name="submit" id="login_submit"/></td></tr>
</table>
</form>
</div>
<?php $_template = new Smarty_Internal_Template("other_login.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>

<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
