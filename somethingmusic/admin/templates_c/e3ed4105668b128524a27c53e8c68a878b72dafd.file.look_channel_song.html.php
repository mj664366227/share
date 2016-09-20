<?php /* Smarty version Smarty3-b8, created on 2016-04-13 09:19:27
         compiled from "E:\www\music/admin/templates/template/look_channel_song.html" */ ?>
<?php /*%%SmartyHeaderCode:29010570e0f1f787501-21991666%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'e3ed4105668b128524a27c53e8c68a878b72dafd' => 
    array (
      0 => 'E:\\www\\music/admin/templates/template/look_channel_song.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '29010570e0f1f787501-21991666',
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
<title>查看歌曲</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>电台一览</a></div>
  <table cellspacing="0">
    <tr>
      <td><?php  $_smarty_tpl->tpl_vars['c'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('channel')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['c']->key => $_smarty_tpl->tpl_vars['c']->value){
?> 
      <?php if ($_smarty_tpl->getVariable('ch')->value==$_smarty_tpl->getVariable('c')->value['en']){?><font><?php echo $_smarty_tpl->getVariable('c')->value['name'];?>
</font><?php }else{ ?>
      <a href="look_channel_song.php?action=index&channel=<?php echo $_smarty_tpl->getVariable('c')->value['en'];?>
"><?php echo $_smarty_tpl->getVariable('c')->value['name'];?>
</a><?php }?> 
      <?php }} ?></td>
    </tr>
  </table>
</div>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a><?php echo $_smarty_tpl->getVariable('ch')->value;?>
频道的歌曲</a></div>
  <table>
    <tr class="bold" align="center">
      <td width="10%">系统编号</td>
      <td width="40%">歌名</td>
      <td width="13%">操作</td>
      <td width="37%">加入其他频道</td>
    </tr>
    <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('res')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
    <tr align="center">
      <td><?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
</td>
      <td><?php echo $_smarty_tpl->getVariable('a')->value['artist'];?>
 - <?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
</td>
      <td><a href="javascript:void(0)" class="getout" id="<?php echo $_smarty_tpl->getVariable('ch')->value;?>
" songid="<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
" title="移出<?php echo $_smarty_tpl->getVariable('ch')->value;?>
频道">移出<?php echo $_smarty_tpl->getVariable('ch')->value;?>
频道</a></td>
      <td><form id="check_box">
          <?php  $_smarty_tpl->tpl_vars['c'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('channel')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['c']->key => $_smarty_tpl->tpl_vars['c']->value){
?>
          <?php if ($_smarty_tpl->getVariable('c')->value['en']!=$_smarty_tpl->getVariable('ch')->value){?>
          <input type="checkbox" songid="<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
" value="<?php echo $_smarty_tpl->getVariable('c')->value['en'];?>
"<?php if ($_smarty_tpl->getVariable('a')->value[$_smarty_tpl->getVariable('c')->value['en']]=="1"){?> checked="checked"<?php }?> class="check"/>
          &nbsp;<?php echo $_smarty_tpl->getVariable('c')->value['name'];?>
&nbsp;
          <?php }?>
          <?php }} ?>
        </form></td>
    </tr>
    <?php }} ?>
    <tr align="center">
      <td colspan="4"><!--<form>
          <input type="checkbox" class="allpage"/>
          一键操作
        </form>--><font>批量操作等我做完课程设计再搞！</font></td>
    </tr>
    <?php if ($_smarty_tpl->getVariable('p')->value!=''){?>
    <tr>
      <td colspan="4" class="showpage" align="center"><?php echo $_smarty_tpl->getVariable('p')->value;?>
</td>
    </tr>
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
input {
	border:1px solid #CCC
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	//移出
	$('.getout').click(function(){
		var id=$(this).attr('id');
		var songid=$(this).attr('songid');
		$.ajax({
				type:'post',
				url:'radio.php',
				data:'action='+id+'&type=false&id='+songid,
				success:function(){}
		})
		$(this).parent().parent().remove();
	})
	$('.check').click(function(){
	var action=$(this).attr('value');	
	var songid=$(this).attr('songid');
	var checked=$(this).attr('checked');
	if(checked)
		{
			$.ajax({
				type:'post',
				url:'radio.php',
				data:'action='+action+'&type=true&id='+songid,
				success:function(){}
			})
		}
		else
		{
			$.ajax({
				type:'post',
				url:'radio.php',
				data:'action='+action+'&type=false&id='+songid,
				success:function(){}
			})
		}
	})
	//一键操作
	$('.allpage').click(function(){
		var i=$('#check_box .check').length;
		$('#check_box .check').each(function(){
			alert($(this).attr('songid'));
		})
	})
})
</script>