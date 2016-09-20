<?php /* Smarty version Smarty3-b8, created on 2012-09-06 17:16:33
         compiled from "F:\htdocs\smu/admin/templates/template/slide.html" */ ?>
<?php /*%%SmartyHeaderCode:322275048da71593873-63508547%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'cbcb8302ac1898b3d59dc3f8042190bc504cdeca' => 
    array (
      0 => 'F:\\htdocs\\smu/admin/templates/template/slide.html',
      1 => 1343982345,
    ),
  ),
  'nocache_hash' => '322275048da71593873-63508547',
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
</head><body>
<div class="main_right">
<div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>上传幻灯片图片</a></div>
<form action="slide.php?action=post" method="post" enctype="multipart/form-data">
<table cellspacing="0">
<tr><td>&nbsp;</td><td><a href="website.php" title="前往设置幻灯片图片数量">前往设置幻灯片图片数量</a></td></tr>
<tr><td align="right" width="150">图片名称：</td><td><input type="text" name="title" class="text"/></td></tr>
<tr><td align="right">上传图片：(jpeg or png)：<br><font>980*300</font></td><td><input type="file" name="pic" class="text"/></td></tr>
<tr><td>&nbsp;</td><td><input type="submit" value="提  交" class="button" /></td></tr>
</table>
</form>
</div>

<div class="main_right">
<div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>图片列表</a></div>
<table cellspacing="0">
<tr class="bold" align="center"><td width="100">编号</td><td>图片</td><td width="100">操作</td></tr>
<?php  $_smarty_tpl->tpl_vars['p'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('pic')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['p']->key => $_smarty_tpl->tpl_vars['p']->value){
?>
<tr><td><?php echo $_smarty_tpl->getVariable('p')->value['id'];?>
</td><td><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w930h300/admin/<?php echo $_smarty_tpl->getVariable('p')->value['url'];?>
" width="620" height="200"/></td><td><?php echo $_smarty_tpl->getVariable('p')->value['name'];?>
</td><td class="comment"><a href="slide.php?action=delete&id=<?php echo $_smarty_tpl->getVariable('p')->value['id'];?>
"  onclick="return confirm('你真的要删除吗？')">删除</a></td></tr>
<?php }} ?>
<?php if ($_smarty_tpl->getVariable('page')->value!=''){?>
    <tr><td colspan="4" class="showpage" align="center"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</td></tr>
    <?php }?>
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