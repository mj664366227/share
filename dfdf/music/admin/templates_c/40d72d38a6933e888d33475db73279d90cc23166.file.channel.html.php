<?php /* Smarty version Smarty3-b8, created on 2013-10-16 20:49:07
         compiled from "E:\htdocs\music/admin/templates/template/channel.html" */ ?>
<?php /*%%SmartyHeaderCode:7206525e8b43d595b1-43390138%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '40d72d38a6933e888d33475db73279d90cc23166' => 
    array (
      0 => 'E:\\htdocs\\music/admin/templates/template/channel.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '7206525e8b43d595b1-43390138',
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
<title>电台频道设置</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>歌曲类别（括号是歌曲总数）</a></div>
  <table cellspacing="0">
    <tr>
      <td> <?php  $_smarty_tpl->tpl_vars['c'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('category')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['c']->key => $_smarty_tpl->tpl_vars['c']->value){
?> 
        <?php if ($_smarty_tpl->getVariable('action')->value==$_smarty_tpl->getVariable('c')->value['category']){?> <font><?php echo $_smarty_tpl->getVariable('c')->value['category'];?>
(<?php echo $_smarty_tpl->getVariable('c')->value['count'];?>
)</font> <?php }else{ ?> <a href="channel.php?action=<?php echo $_smarty_tpl->getVariable('c')->value['category'];?>
"><?php echo $_smarty_tpl->getVariable('c')->value['category'];?>
(<?php echo $_smarty_tpl->getVariable('c')->value['count'];?>
)</a> <?php }?>
        <?php }} ?> </td>
    </tr>
  </table>
</div>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>歌曲搜索</a></div>
  <form action="channel.php?action=<?php echo $_smarty_tpl->getVariable('type')->value;?>
&do=search" method="post">
    <table>
      <tr>
        <td width="10%" align="right">搜索关键字：</td><td><input type="text" class="text" name="search" id="search_text"/>&nbsp;<input type="submit" value="搜 索" class="button" id="search_button"/></td>
      </tr>
    </table>
  </form>
</div>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>选择频道（已打上勾即是该频道的歌曲，没有则不是。）</a></div>
  <table cellspacing="0" class="noticelist">
    <tr align="center" class="bold">
      <td width="7%">系统编号</td>
      <td width="30%">歌名</td>
      <td width="63%">加入的频道</td>
    </tr>
    <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('song')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
    <tr align="center">
      <td><?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
</td>
      <td align="left"><?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
 - <?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
</td>
      <form>
        <td align="left"> <?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('radio_channel_set')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?>
          <?php if ($_smarty_tpl->getVariable('r')->value['en_name']!='new'){?>
          <input type="checkbox" songid="<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" value="<?php echo $_smarty_tpl->getVariable('r')->value['en_name'];?>
"<?php if ($_smarty_tpl->getVariable('s')->value[$_smarty_tpl->getVariable('r')->value['en_name']]=="1"){?> checked="checked"<?php }?> class="check"/>
          &nbsp;<?php echo $_smarty_tpl->getVariable('r')->value['name'];?>
&nbsp;<?php }?><?php }} ?></td>
      </form>
    </tr>
    <?php }} ?>
    <?php if ($_smarty_tpl->getVariable('page')->value!=''){?>
    <tr>
      <td colspan="5" align="center" class="showpage"><?php echo $_smarty_tpl->getVariable('page')->value;?>
<br/>
       <?php if ($_smarty_tpl->getVariable('show')->value==1){?>
        <br/>
        <form action="channel.php?action=<?php echo $_smarty_tpl->getVariable('type')->value;?>
" method="post">
          每页显示&nbsp;
          <input type="text" name="perpage" style="width:30px" id="perpage"/>
          &nbsp;页&nbsp;
          <input type="submit" id="submitperpage" style="cursor:pointer"/>
        </form>
        <br/>
        <br/>
        <?php }?></td>
    </tr>
    <tr>
      <td colspan="5" align="center"><a href="#">返回顶部</a></td>
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
	$('#search_button').click(function(){
		if($('#search_text').val().trim()=='')
		{
			alert('搜索关键字为空！');
			return false;
		}
	})
	
	$('#submitperpage').click(function(){
		var length=$('.showpage a').length;
		var str=$('.showpage a:eq('+(length-1)+')').attr('href');
		var the_max=str.slice(str.indexOf('page=')+5,str.length);
		if($('#perpage').val()=='') 
		{
			alert('输入为空！');
			return false;
		}
		if(isFinite($('#perpage').val())==false) 
		{
			alert('输入的必须为数字！');
			return false;
		}
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
})
</script>