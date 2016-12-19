<?php /* Smarty version Smarty3-b8, created on 2013-10-16 20:49:30
         compiled from "E:\htdocs\music/admin/templates/template/website.html" */ ?>
<?php /*%%SmartyHeaderCode:13094525e8b5a454f06-99211856%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '20da1a897d29d1e3bb34b5ded0c42ab73405dfcf' => 
    array (
      0 => 'E:\\htdocs\\music/admin/templates/template/website.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '13094525e8b5a454f06-99211856',
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
<title>站点信息设置</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>站点信息设置</a></div>
  <table cellspacing="0">
    <form action="website_infor.php" method="post">
      <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('array')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
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
      <tr>
        <td class="table_left">网站名称：</td>
        <td><input type="text" value="<?php echo $_smarty_tpl->getVariable('array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['webtitle'];?>
" name="webtitle" class="text"/></td>
      </tr>
      <tr>
        <td class="table_left">域名：</td>
        <td><input type="text" value="<?php echo $_smarty_tpl->getVariable('array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['yuming'];?>
" name="yuming" class="text"/>&nbsp;&nbsp;<font>亲，请确保域名的正确性，否则页面无法正确显示！</font></td>
      </tr>
      <tr>
        <td class="table_left">关键字：</td>
        <td><textarea cols="80" rows="7"  name="seo"><?php echo $_smarty_tpl->getVariable('array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['seo'];?>
</textarea></td>
      </tr>
      <tr>
        <td class="table_left">网站描述：</td>
        <td><textarea cols="80" rows="7"  name="description"><?php echo $_smarty_tpl->getVariable('array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['description'];?>
</textarea></td>
      </tr>
      <tr>
        <td class="table_left">网站运行状态：</td>
        <td><label class="label"><input type="radio" name="status" value="1" <?php if ($_smarty_tpl->getVariable('array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['status']==1){?>checked<?php }?>>
          运行</label>
          <label class="label"><input type="radio" name="status" value="0" <?php if ($_smarty_tpl->getVariable('array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['status']==0){?>checked<?php }?>>
          关闭</label> </td>
      </tr>
      <tr><td class="table_left">首页幻灯片数量：</td>
      <td>
      <select name="slide">
      <?php  $_smarty_tpl->tpl_vars['n'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('num')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['n']->key => $_smarty_tpl->tpl_vars['n']->value){
?>
      <option value="<?php echo $_smarty_tpl->getVariable('n')->value;?>
"<?php if ($_smarty_tpl->getVariable('n')->value==$_smarty_tpl->getVariable('array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['slide']){?> selected="selected"<?php }?>>&nbsp;<?php echo $_smarty_tpl->getVariable('n')->value;?>
&nbsp;</option>
      <?php }} ?>
      </select>
      </td>
      </tr>
      <tr>
      <td class="table_left">页面模板：<br><br><font>亲，换模板后记得清缓存！</font></td>
      <td>
      <ul>
      <?php  $_smarty_tpl->tpl_vars['f'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('file')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['f']->key => $_smarty_tpl->tpl_vars['f']->value){
?>
     <label class="label" title="<?php echo $_smarty_tpl->getVariable('f')->value;?>
"><li><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
../templates/template/<?php echo $_smarty_tpl->getVariable('f')->value;?>
/pic.png"/><br>
      <input type="radio" name="template" value="<?php echo $_smarty_tpl->getVariable('f')->value;?>
"<?php if ($_smarty_tpl->getVariable('f')->value==$_smarty_tpl->getVariable('array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['template']){?> checked="checked"<?php }?>/>
      <?php echo $_smarty_tpl->getVariable('f')->value;?>
</li></label>
      <?php }} ?>
      </ul>
      </td>
      </tr>
      <tr>
        <td class="table_left">暂停理由：</td>
        <td><textarea cols="80" rows="7"  name="reason"><?php echo $_smarty_tpl->getVariable('array')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['reason'];?>
</textarea></td>
      </tr>
      <?php endfor; endif; ?>
      <tr>
        <td class="table_left"></td>
        <td><input type="submit" class="button" value="提  交"/></td>
      </tr>
    </form>
  </table>
</div>
</body>
</html>
<style>
ul{ list-style:none; padding:0 }
ul li{ float:left; width:155px; text-align:center }
</style>