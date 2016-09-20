<?php /* Smarty version Smarty3-b8, created on 2012-09-06 17:16:25
         compiled from "F:\htdocs\smu/admin/templates/template/radio_channel.html" */ ?>
<?php /*%%SmartyHeaderCode:52705048da695d4281-73144599%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '62fd155f97bf47ac974fbcb7eac4772084b3b4bc' => 
    array (
      0 => 'F:\\htdocs\\smu/admin/templates/template/radio_channel.html',
      1 => 1343982345,
    ),
  ),
  'nocache_hash' => '52705048da695d4281-73144599',
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
<script type="text/javascript" src="templates/template/jquery-1.4a2.js"></script>
<title>添加音乐类型</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>添加电台频道</a></div>
  <table cellpadding="0">
    <form action="set_channel.php?action=add" method="post">
      <tr>
        <td width="30%" align="center">电台频道名称<font>*</font>：</td>
        <td><input class="text" type="text" name="name" id="name"/></td>
      </tr>
      <tr>
        <td align="center">数据库字段名称<font>*</font>：<br/>
          （<font>用作传递参数，建议设置简短，见文知义，不要重复！</font>）</td>
        <td><input class="text" type="text" name="en_name" id="en_name"/></td>
      </tr>
      <tr>
        <td align="center">显示序号（越小越前）<font>*</font>：<br/></td>
        <td><input class="text" type="text" name="order" id="order"/></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" class="button" value="提  交" id="button"/></td>
      </tr>
    </form>
  </table>
</div>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>已有频道一览（后面的英文为数据库字段名，打勾即为已经开通的频道，括号内为该频道的点播次数，点击频道名字可删除，此显示顺序是前台显示的顺序。）</a></div>
  <table cellpadding="0">
    <form>
      <tr>
        <td> <?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('radio_channel_set')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?>
          <input type="checkbox"<?php if ($_smarty_tpl->getVariable('r')->value['show']=="1"){?> checked="checked"<?php }?> value="<?php echo $_smarty_tpl->getVariable('r')->value['en_name'];?>
" class="r" id="<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
"/>
          <?php if ($_smarty_tpl->getVariable('r')->value['en_name']=='ktv'||$_smarty_tpl->getVariable('r')->value['en_name']=='new'||$_smarty_tpl->getVariable('r')->value['en_name']=='old'||$_smarty_tpl->getVariable('r')->value['en_name']=='less'){?>
 <?php echo $_smarty_tpl->getVariable('r')->value['order'];?>
：<?php echo $_smarty_tpl->getVariable('r')->value['name'];?>
<?php echo $_smarty_tpl->getVariable('r')->value['en_name'];?>
（<?php echo $_smarty_tpl->getVariable('r')->value['count'];?>
）
          <?php }else{ ?>
          <a href="set_channel.php?action=delete&id=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
&en_name=<?php echo $_smarty_tpl->getVariable('r')->value['en_name'];?>
" onclick="return confirm('你真的要删除 <?php echo $_smarty_tpl->getVariable('r')->value['name'];?>
 吗？');"><?php echo $_smarty_tpl->getVariable('r')->value['order'];?>
：<?php echo $_smarty_tpl->getVariable('r')->value['name'];?>
<?php echo $_smarty_tpl->getVariable('r')->value['en_name'];?>
（<?php echo $_smarty_tpl->getVariable('r')->value['count'];?>
）</a><?php }?> <?php }} ?> </td>
      </tr>
    </form>
  </table>
</div>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>频道顺序修改</a></div>
  <table cellpadding="0">
    <tr class="bold" align="center">
      <td width="10%">频道名称</td>
      <td width="10%">原序号</td>
      <td align="left">新序号（<font>不要和现有的序号一样！</font>）</td>
    </tr>
    <?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('radio_channel_set')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?>
    <tr align="center">
      <td><?php echo $_smarty_tpl->getVariable('r')->value['name'];?>
</td>
      <td><?php echo $_smarty_tpl->getVariable('r')->value['order'];?>
</td>
      <td align="left"><form action="set_channel.php?action=modify" method="post">
          <input type="text" name="new_order" id="new_order"/>
          <input type="hidden" name="order" value="<?php echo $_smarty_tpl->getVariable('r')->value['order'];?>
"/>
          <input type="hidden" name="en_name" value="<?php echo $_smarty_tpl->getVariable('r')->value['en_name'];?>
"/>
          <input type="submit" value="提 交" class="button" id="modify_order"/>
        </form></td>
    </tr>
    <?php }} ?>
  </table>
</div>
</body>
</html>
<style>
table tr td a {
	color:#000;
	text-decoration:none;
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
<script type="text/javascript">
$(document).ready(function(){
	$('#name').val('');
	$('#en_name').val('');
	$('#order').val('');
	$('#new_order').val('');
	$('#button').click(function(){
		
		if($('#name').val()==''||$('#en_name').val()==''||$('#order').val()=='')
		{
			alert('输入不能为空！');
			return false;
		}	
		
		if(isFinite($('#order').val())==false)
		{
			alert('请输入数字！');
			return false;
		}
	})	
	
	$('#modify_order').click(function(){
		if($('#new_order').val()=='')
		{
			alert('输入不能为空！');
			return false;
		}
		if(isFinite($('#new_order').val())==false)
		{
			alert('请输入数字！');
			return false;
		}
	})
	
	$('.r').click(function(){
		var id=$(this).attr('id');
		var checked=$(this).attr('checked');
		if(checked)
		{
			$.ajax({
				type:'post',
				url:'set_channel.php',
				data:'action=ajax&type=true&id='+id,
				success:function(){}
			})
		}
		else
		{
			$.ajax({
				type:'post',
				url:'set_channel.php',
				data:'action=ajax&type=false&id='+id,
				success:function(){}
			})
		}
	})
})
</script>