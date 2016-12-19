<?php /* Smarty version Smarty3-b8, created on 2013-10-16 20:49:29
         compiled from "E:\htdocs\music/admin/templates/template/gonggao.html" */ ?>
<?php /*%%SmartyHeaderCode:5744525e8b596f1be9-70302249%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'f98f9f5569ae080298b2e977f997c30a00bcc0fd' => 
    array (
      0 => 'E:\\htdocs\\music/admin/templates/template/gonggao.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '5744525e8b596f1be9-70302249',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_truncate')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.truncate.php';
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="templates/template/css.css" type="text/css" />
<title>发布公告</title>
</head>

<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>发布公告</a></div>
  <table cellspacing="0">
    <form action="adgonggao.php" method="post">
      <tr>
        <td class="table_left">公告内容：</td>
        <td><textarea cols="80" rows="12"  name="adgonggao">点击这里添加公告...</textarea></td>
      </tr>
      <tr>
        <td class="table_left"></td>
        <td><input type="submit" class="button" value="添  加"/></td>
      </tr>
    </form>
  </table>
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>已添加的公告</a></div>
  <table cellspacing="0" class="noticelist">
    <tr align="center" class="bold">
      <td>显示序号</td>
      <td width="50%">公告内容</td>
      <td>添加时间</td>
      <td>首页状态</td>
      <td>操作</td>
    </tr>
    <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('gonggaoarr')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
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
      <td><?php echo $_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
</td>
      <td><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['gonggao'],100);?>
</td>
      <td><?php echo $_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['time'];?>
</td>
      <td><?php if ($_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['show']==0){?><font style="color:#00F">隐藏</font><?php }elseif($_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['show']==1){?><font>显示</font><?php }?></td>
      <td><a href="gonggao_modify.php?id=<?php echo $_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
">修改</a> <a href="gonggao_delete.php?id=<?php echo $_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
" onclick="return confirm('确认删除吗？')">删除</a> <?php if ($_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['show']==1){?><a href="hidegonggao.php?id=<?php echo $_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
&&show=<?php echo $_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['show'];?>
" title="在前台隐藏此公告">隐藏</a><?php }?><?php if ($_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['show']==0){?><a href="showgonggao.php?id=<?php echo $_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
&&show=<?php echo $_smarty_tpl->getVariable('gonggaoarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['show'];?>
" title="在前台显示此公告">显示</a><?php }?></td>
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
</html>