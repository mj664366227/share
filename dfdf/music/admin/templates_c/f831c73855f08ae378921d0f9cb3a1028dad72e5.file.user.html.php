<?php /* Smarty version Smarty3-b8, created on 2013-03-02 14:11:41
         compiled from "F:/PHPNOW/vhosts/music/admin/templates/template/user.html" */ ?>
<?php /*%%SmartyHeaderCode:16895132089d1e7e73-16546414%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'f831c73855f08ae378921d0f9cb3a1028dad72e5' => 
    array (
      0 => 'F:/PHPNOW/vhosts/music/admin/templates/template/user.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '16895132089d1e7e73-16546414',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="templates/template/css.css" type="text/css" />
<title>用户管理</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>用户添加</a></div>
  <table cellspacing="0">
    <form action="user_add.php" method="post">
      <tr>
        <td class="table_left">用户名称：</td>
        <td><input type="text" name="username" class="text"/></td>
      </tr>
      <tr>
        <td class="table_left">用户密码：</td>
        <td><input type="password" name="userpassword1" class="text"/></td>
      </tr>
      <tr>
        <td class="table_left">再输一次：</td>
        <td><input type="password" name="userpassword2" class="text"/></td>
      </tr>
      <tr>
        <td class="table_left"></td>
        <td><input type="submit" class="button" value="提  交"/></td>
      </tr>
    </form>
  </table>
</div>

<!--
<div class="main_right">
<div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>用户搜索</a></div>
<table cellspacing="0">
<form action="user_search.php" method="post">
<tr><td class="table_left">用户名称：</td><td><input type="text" class="text" name="user_search"/></td></tr>
<tr><td class="table_left"></td><td><input type="submit" class="button" value="搜  索"/></td></tr>
</form>
</table>
</div>
-->

<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>用户列表</a></div>
  <table cellspacing="0" class="noticelist">
    <tr align="center" class="bold">
      <td width="20%">系统编号</td>
      <td width="30%">用户名称</td>
      <td width="30%">密码（已加密）</td>
      <td>操作</td>
    </tr>
    <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('user_array')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['name'] = 'sec';
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['show'] = true;
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['max'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['step'] = 1;
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['start'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['step'] > 0 ? 0 : $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop']-1;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['show']) {
    $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['total'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'];
    if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['total'] == 0)
        $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['show'] = false;
} else
    $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['total'] = 0;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['show']):

            for ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['start'], $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration'] = 1;
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration'] <= $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['total'];
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index'] += $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['step'], $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration']++):
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['rownum'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index_prev'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index'] - $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index_next'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index'] + $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['first']      = ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration'] == 1);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['last']       = ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration'] == $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['total']);
?>
    <tr align="center">
      <td><?php echo $_smarty_tpl->getVariable('user_array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
</td>
      <td><?php echo $_smarty_tpl->getVariable('user_array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['name'];?>
</td>
      <td><?php echo $_smarty_tpl->getVariable('user_array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['pwd'];?>
</td>
      <td><a href="user_modify.php?id=<?php echo $_smarty_tpl->getVariable('user_array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
">修改</a> <a href="user_delete.php?id=<?php echo $_smarty_tpl->getVariable('user_array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
" onclick="return confirm('你真的要删除吗？')">删除</a></td>
    </tr>
    <?php endfor; endif; ?>
    <?php if ($_smarty_tpl->getVariable('str')->value!=''){?>
    <tr>
      <td colspan="5" align="center" class="showpage"><?php echo $_smarty_tpl->getVariable('str')->value;?>
</td>
    </tr>
    <?php }?>
  </table>
</div>
</body>
</html>
