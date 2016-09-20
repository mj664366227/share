<?php /* Smarty version Smarty3-b8, created on 2013-10-19 01:28:50
         compiled from "E:\htdocs\music/admin/templates/template/server.html" */ ?>
<?php /*%%SmartyHeaderCode:797752616fd2404c81-18515850%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '78d819d11c07b1cdd716a2f0f1e8329f9893ac41' => 
    array (
      0 => 'E:\\htdocs\\music/admin/templates/template/server.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '797752616fd2404c81-18515850',
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
<title>添加服务器</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>添加服务器</a></div>
  <table cellpadding="0">
    <form action="server_add.php" method="post">
      <tr>
        <td>服务器地址：</td>
        <td><input type="text" class="text" name="server_address" value="http://"/></td>
      </tr>
      <tr>
        <td>服务器名称：</td>
        <td><input type="text" class="text" name="server_name" /></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" class="button" value="提  交"/></td>
      </tr>
    </form>
  </table>
</div>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>服务器列表</a></div>
  <table cellpadding="0">
    <tr align="center" class="bold">
      <td width="30%">服务器地址</td>
      <td width="30%">服务器名称</td>
      <td>操作</td>
    </tr>
    <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('server')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
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
    <tr class="comment">
      <td align="center"><?php echo $_smarty_tpl->getVariable('server')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['server'];?>
</td>
      <td align="center"><?php echo $_smarty_tpl->getVariable('server')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['name'];?>
</td>
      <td align="center"><a href="server_modify.php?id=<?php echo $_smarty_tpl->getVariable('server')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
">修改</a>     <a href="server_delete.php?id=<?php echo $_smarty_tpl->getVariable('server')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
" onclick="return confirm('你真的要删除吗？')">删除</a></td>
    </tr>
    <?php endfor; endif; ?>
    <?php if ($_smarty_tpl->getVariable('str')->value!=''){?>
    <tr>
      <td colspan="3" align="center" class="showpage"><?php echo $_smarty_tpl->getVariable('str')->value;?>
</td>
    </tr>
    <?php }?>
  </table>
</div>
</body>
</html>
