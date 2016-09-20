<?php /* Smarty version Smarty3-b8, created on 2012-09-06 17:10:04
         compiled from "F:\htdocs\smu/admin/templates/template/db.html" */ ?>
<?php /*%%SmartyHeaderCode:250935048d8ecbf1cc2-35242735%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '622207951a324b690f38709a0e8bba6390f10e13' => 
    array (
      0 => 'F:\\htdocs\\smu/admin/templates/template/db.html',
      1 => 1343982345,
    ),
  ),
  'nocache_hash' => '250935048d8ecbf1cc2-35242735',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_size')) include 'F:\htdocs\smu\lib\Smarty\plugins\modifier.size.php';
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="templates/template/css.css" type="text/css" />
</head><body>
<div class="main_right">
<div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>查看表信息</a></div>
<table cellspacing="0">
<tr class="bold" align="center"><td>表名</td><td>行数</td><td>类型</td><td>大小</td><td>操作</td></tr>
<?php  $_smarty_tpl->tpl_vars['t'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('table')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['t']->key => $_smarty_tpl->tpl_vars['t']->value){
?>
<tr align="center"><td class="table_left"><strong><?php echo $_smarty_tpl->getVariable('t')->value['Name'];?>
</strong></td><td><?php echo $_smarty_tpl->getVariable('t')->value['Rows'];?>
</td><td><?php echo $_smarty_tpl->getVariable('t')->value['Engine'];?>
</td><td><?php echo smarty_modifier_size($_smarty_tpl->getVariable('t')->value['Data_length']);?>
</td><td class="comment"><a href="youhua.php?table=<?php echo $_smarty_tpl->getVariable('t')->value['Name'];?>
">优化表</a></td></tr>
<?php }} ?>
<tr><td class="comment" align="center" colspan="5"><a href="youhua.php?table=all">优化所有表</a></td></tr>
</table>
</div>
</body>
</html>