<?php /* Smarty version Smarty3-b8, created on 2013-10-19 01:28:36
         compiled from "E:\htdocs\music/admin/templates/template/comment.html" */ ?>
<?php /*%%SmartyHeaderCode:147552616fc4ddcc33-47683858%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '04215e9134b02956b1618c700b588c65a1b894a6' => 
    array (
      0 => 'E:\\htdocs\\music/admin/templates/template/comment.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '147552616fc4ddcc33-47683858',
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
<title>评论管理</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>评论管理</a></div>
  <table cellspacing="0">
    <tr class="bold" align="center">
      <td>序号</td>
      <td>评论内容</td>
      <td>所属专辑</td>
      <td>评论者姓名</td>
      <td>评论者ip地址</td>
      <td>评论类型</td>
      <td>评论时间</td>
      <td>操作</td>
    </tr>
    <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('commentarr')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
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
      <td><?php echo $_smarty_tpl->getVariable('commentarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
</td>
      <td width="40%" class="comment"><a href="comment_look.php?id=<?php echo $_smarty_tpl->getVariable('commentarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
" title="点击查看本条内容"><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('commentarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['text'],100);?>
</a></td>
      <td><?php echo $_smarty_tpl->getVariable('commentarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['title'];?>
</td>
      <td><?php echo $_smarty_tpl->getVariable('commentarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['user'];?>
</td>
      <td><?php echo $_smarty_tpl->getVariable('commentarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['ip'];?>
</td>
      <td><?php if ($_smarty_tpl->getVariable('commentarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['type']==1){?><font>专辑</font><?php }?><?php if ($_smarty_tpl->getVariable('commentarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['type']==2){?><font style="color:#00F">文章</font><?php }?></td>
      <td><?php echo $_smarty_tpl->getVariable('commentarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['addtime'];?>
</td>
      <td class="comment"><a href="comment_look.php?id=<?php echo $_smarty_tpl->getVariable('commentarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
" title="查看本条内容">查看</a> <a href="comment_delete.php?id=<?php echo $_smarty_tpl->getVariable('commentarr')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
" onclick="return confirm('你真的要删除吗？')" title="删除本条内容">删除</a></td>
    </tr>
    <?php endfor; endif; ?>
    <?php if ($_smarty_tpl->getVariable('str')->value!=''){?>
    <tr>
      <td colspan="7" align="center" class="showpage"><?php echo $_smarty_tpl->getVariable('str')->value;?>
</td>
    </tr>
    <?php }?>
  </table>
</div>
</body>
</html>
