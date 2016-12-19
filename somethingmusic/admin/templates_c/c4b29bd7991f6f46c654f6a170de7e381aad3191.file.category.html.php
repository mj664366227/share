<?php /* Smarty version Smarty3-b8, created on 2013-03-01 14:40:58
         compiled from "F:/PHPNOW/vhosts/music/admin/templates/template/category.html" */ ?>
<?php /*%%SmartyHeaderCode:17895130bdfa7bc7a1-10203495%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'c4b29bd7991f6f46c654f6a170de7e381aad3191' => 
    array (
      0 => 'F:/PHPNOW/vhosts/music/admin/templates/template/category.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '17895130bdfa7bc7a1-10203495',
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
<title>添加音乐类型</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>添加一级标题</a></div>
  <table cellpadding="0">
    <form action="add_catagory.php" method="post">
      <tr>
        <td class="table_left">一级标题名称：</td>
        <td width="30%"><input class="text" type="text" name="bigcatagory"/></td>
        <td><input type="submit" class="button" value="添   加"/></td>
      </tr>
    </form>
  </table>
</div>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>添加音乐类型</a></div>
  <table cellpadding="0">
    <form action="add_small_catagory.php" method="post">
      <tr>
        <td class="table_left">音乐类型：</td>
        <td><input class="text" type="text" name="smallcatagory"/></td>
      </tr>
      <tr>
        <td class="table_left">一级标题：</td>
        <td><select name="father" style="width:auto">
            
        <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('bigcategory')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
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
            <option value="<?php echo $_smarty_tpl->getVariable('bigcategory')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['bigcategory'];?>
"><?php echo $_smarty_tpl->getVariable('bigcategory')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['bigcategory'];?>
</option>
            
        <?php endfor; endif; ?>
          </select></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" class="button" value="添   加"/></td>
      </tr>
    </form>
  </table>
</div>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>音乐类型列表（点击即删除该项！）</a></div>
  <table cellpadding="0" class="look">
    <tr style="font-weight:bold">
      <td>一级标题</td>
      <td>二级标题</td>
    </tr>
    <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('bigcategory')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['name'] = 'sec2';
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['show'] = true;
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['max'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['loop'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['step'] = 1;
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['start'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['step'] > 0 ? 0 : $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['loop']-1;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['show']) {
    $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['total'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['loop'];
    if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['total'] == 0)
        $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['show'] = false;
} else
    $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['total'] = 0;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['show']):

            for ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['index'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['start'], $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['iteration'] = 1;
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['iteration'] <= $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['total'];
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['index'] += $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['step'], $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['iteration']++):
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['rownum'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['iteration'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['index_prev'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['index'] - $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['index_next'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['index'] + $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['first']      = ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['iteration'] == 1);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['last']       = ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['iteration'] == $_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['total']);
?>
    <tr>
      <td width="30%"><a href="category_delete_big.php?bigcategory=<?php echo $_smarty_tpl->getVariable('bigcategory')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec2']['index']]['bigcategory'];?>
" onclick="return confirm('你真的要删除本分类及其所有的子分类吗？')"><?php echo $_smarty_tpl->getVariable('bigcategory')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec2']['index']]['bigcategory'];?>
</a></td>
      <td width="70%" align="left"><?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('smallarray')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['name'] = 'sec3';
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['show'] = true;
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['max'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['loop'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['step'] = 1;
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['start'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['step'] > 0 ? 0 : $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['loop']-1;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['show']) {
    $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['total'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['loop'];
    if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['total'] == 0)
        $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['show'] = false;
} else
    $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['total'] = 0;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['show']):

            for ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['index'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['start'], $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['iteration'] = 1;
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['iteration'] <= $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['total'];
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['index'] += $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['step'], $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['iteration']++):
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['rownum'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['iteration'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['index_prev'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['index'] - $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['index_next'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['index'] + $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['first']      = ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['iteration'] == 1);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['last']       = ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['iteration'] == $_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['total']);
?><?php if ($_smarty_tpl->getVariable('smallarray')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec3']['index']]['bigcategory']==$_smarty_tpl->getVariable('bigcategory')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec2']['index']]['bigcategory']){?> <a href="category_delete_small.php?smallcategory=<?php echo $_smarty_tpl->getVariable('smallarray')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec3']['index']]['smallcategory'];?>
" onclick="return confirm('你真的要删除本分类吗？')"><?php echo $_smarty_tpl->getVariable('smallarray')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec3']['index']]['smallcategory'];?>
</a> <?php }?><?php endfor; endif; ?></td>
    </tr>
    <?php endfor; endif; ?>
  </table>
</div>
</body>
</html>
<style>
table tr td a {
	color:#09F;
	text-decoration:underline;
	line-height:20px
}
table tr td a:hover {
	color:#F00;
	font-weight:bold;
	text-decoration:none
}
.look {
	text-align:center
}
</style>