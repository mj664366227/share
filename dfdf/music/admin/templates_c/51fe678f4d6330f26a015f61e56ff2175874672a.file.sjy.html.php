<?php /* Smarty version Smarty3-b8, created on 2012-09-06 17:16:24
         compiled from "F:\htdocs\smu/admin/templates/template/sjy.html" */ ?>
<?php /*%%SmartyHeaderCode:22115048da6868fc90-86920226%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '51fe678f4d6330f26a015f61e56ff2175874672a' => 
    array (
      0 => 'F:\\htdocs\\smu/admin/templates/template/sjy.html',
      1 => 1343982345,
    ),
  ),
  'nocache_hash' => '22115048da6868fc90-86920226',
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
<title>电台首卷语</title>
</head>
<script type="text/javascript" src="templates/template/jquery-1.4a2.js"></script>
<script type="text/javascript">
$(document).ready(function(){
$('.button').click(function(){
	var a=$('#text').val().length;
	if(a<=1)
	{
		alert('1个字都写不出吗！');
		return false;
	}
	})
})
</script>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>添加电台首卷语（100-200字）</a></div>
  <table cellspacing="0">
    <form action="shoujuanyu.php?action=post" method="post">
      <tr>
        <td class="table_left">首卷语内容：</td>
        <td><textarea cols="80" rows="12"  name="text" id="text"></textarea></td>
      </tr>
      <tr>
        <td class="table_left"></td>
        <td><input type="submit" class="button" value="添  加"/></td>
      </tr>
    </form>
  </table>
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>已有的电台首卷语</a></div>
  <table cellspacing="0" class="noticelist">
    <tr align="center" class="bold">
      <td>显示序号</td>
      <td width="70%">首卷语内容</td>
      <td>操作</td>
    </tr>
    <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('sjy')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
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
      <td><?php echo $_smarty_tpl->getVariable('sjy')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
</td>
      <td style="text-align:left"><?php echo $_smarty_tpl->getVariable('sjy')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['text'];?>
</td>
      <td><a href="shoujuanyu.php?action=modify&id=<?php echo $_smarty_tpl->getVariable('sjy')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
">修改</a>  <a href="shoujuanyu.php?action=delete&id=<?php echo $_smarty_tpl->getVariable('sjy')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
" onclick="return confirm('你真的要删除吗？');return false;">删除</a></td>
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