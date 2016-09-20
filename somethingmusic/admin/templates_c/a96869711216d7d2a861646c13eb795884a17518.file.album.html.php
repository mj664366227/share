<?php /* Smarty version Smarty3-b8, created on 2013-03-01 14:41:00
         compiled from "F:/PHPNOW/vhosts/music/admin/templates/template/album.html" */ ?>
<?php /*%%SmartyHeaderCode:305545130bdfc1c5078-39318318%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'a96869711216d7d2a861646c13eb795884a17518' => 
    array (
      0 => 'F:/PHPNOW/vhosts/music/admin/templates/template/album.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '305545130bdfc1c5078-39318318',
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
<title>专辑列表</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>专辑搜索</a></div>
  <form action="album.php?action=search" method="post">

    <table>

      <tr>

        <td width="10%" align="right">搜索关键字：</td><td><input type="text" class="text" name="search" id="search_text"/>&nbsp;<input type="submit" value="搜 索" class="button" id="search_button"/></td>

      </tr>

    </table>

  </form>
  </div>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>专辑列表</a></div>
  <table cellspacing="0">
    <tr align="center" class="bold">
      <td width="10%">编号</td>
      <td>专辑名称</td>
      <td width="40">添加者</td>
      <td width="75">类型</td>
      <td width="60">专辑热度</td>
      <td width="40">操作</td>
    </tr>
    <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['s']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['s']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('album')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$_smarty_tpl->tpl_vars['smarty']->value['section']['s']['name'] = 's';
$_smarty_tpl->tpl_vars['smarty']->value['section']['s']['show'] = true;
$_smarty_tpl->tpl_vars['smarty']->value['section']['s']['max'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['loop'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['s']['step'] = 1;
$_smarty_tpl->tpl_vars['smarty']->value['section']['s']['start'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['step'] > 0 ? 0 : $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['loop']-1;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['s']['show']) {
    $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['total'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['loop'];
    if ($_smarty_tpl->tpl_vars['smarty']->value['section']['s']['total'] == 0)
        $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['show'] = false;
} else
    $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['total'] = 0;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['s']['show']):

            for ($_smarty_tpl->tpl_vars['smarty']->value['section']['s']['index'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['start'], $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['iteration'] = 1;
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['iteration'] <= $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['total'];
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['index'] += $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['step'], $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['iteration']++):
$_smarty_tpl->tpl_vars['smarty']->value['section']['s']['rownum'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['iteration'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['s']['index_prev'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['index'] - $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['s']['index_next'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['index'] + $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['s']['first']      = ($_smarty_tpl->tpl_vars['smarty']->value['section']['s']['iteration'] == 1);
$_smarty_tpl->tpl_vars['smarty']->value['section']['s']['last']       = ($_smarty_tpl->tpl_vars['smarty']->value['section']['s']['iteration'] == $_smarty_tpl->tpl_vars['smarty']->value['section']['s']['total']);
?>
    <tr align="center" class="comment">
      <td><?php echo $_smarty_tpl->getVariable('album')->value[$_smarty_tpl->getVariable('smarty')->value['section']['s']['index']]['id'];?>
</td>
      <td class="comment"><a href="album_look.php?id=<?php echo $_smarty_tpl->getVariable('album')->value[$_smarty_tpl->getVariable('smarty')->value['section']['s']['index']]['id'];?>
"><?php echo $_smarty_tpl->getVariable('album')->value[$_smarty_tpl->getVariable('smarty')->value['section']['s']['index']]['artist'];?>
 - <?php echo $_smarty_tpl->getVariable('album')->value[$_smarty_tpl->getVariable('smarty')->value['section']['s']['index']]['title'];?>
</a></td>
      <td><?php echo $_smarty_tpl->getVariable('album')->value[$_smarty_tpl->getVariable('smarty')->value['section']['s']['index']]['author'];?>
</td>
      <td><?php echo $_smarty_tpl->getVariable('album')->value[$_smarty_tpl->getVariable('smarty')->value['section']['s']['index']]['category'];?>
</td>
      <td><?php echo $_smarty_tpl->getVariable('album')->value[$_smarty_tpl->getVariable('smarty')->value['section']['s']['index']]['hit'];?>
</td>
      <td><a href="album_delete.php?id=<?php echo $_smarty_tpl->getVariable('album')->value[$_smarty_tpl->getVariable('smarty')->value['section']['s']['index']]['id'];?>
" title="点击删除《<?php echo $_smarty_tpl->getVariable('album')->value[$_smarty_tpl->getVariable('smarty')->value['section']['s']['index']]['title'];?>
》" onclick="return confirm('真的要删除本专辑以及其包括的所有歌曲吗？')">删除</a></td>
    </tr>
    <?php endfor; endif; ?>
    <?php if ($_smarty_tpl->getVariable('str')->value!=''){?>
    <tr><td colspan="5" class="showpage" align="center"><?php echo $_smarty_tpl->getVariable('str')->value;?>
</td></tr>
    <?php }?>
  </table>
</div>
</body>
</html>