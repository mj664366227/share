<?php /* Smarty version Smarty3-b8, created on 2013-03-02 12:39:13
         compiled from "F:/PHPNOW/vhosts/music/admin/templates/template/album_look.html" */ ?>
<?php /*%%SmartyHeaderCode:86075131f2f12bc258-07171871%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '91842e53d5af7aadc198d4b55db8130efd92b29a' => 
    array (
      0 => 'F:/PHPNOW/vhosts/music/admin/templates/template/album_look.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '86075131f2f12bc258-07171871',
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
<style>
input{ border:#bbbbbb 1px solid;}
</style>
<script type="text/javascript" src="templates/template/jquery-1.4a2.js"></script>
<script type="text/javascript" src="../kindeditor/kindeditor.js"></script>
<script type="text/javascript">
$(document).ready(function() {
$('#seturl').click(function(){
	var s = $('.s').val();
	var server = $('.server').val();
	if(s=='') 
	{
		alert('请输入歌曲数目！');
		return false;
	}
	if(isFinite(s)==false) 
	{
		alert('输入的必须为数字！');
		return false;
	}
	if(s<=0) 
	{
		alert('曲目数不可能为0！');
		return false;
	}
	if(s>=100) 
	{
		alert('曲目数太大！');
		return false;
	}
	for(i=0;i<s;i++)
	{
		html = '<div class="musicplay"><input type="text" name="url[]" size="90" value='+server+'>&nbsp;&nbsp;名称：<input type="text" name="songtitle[]" size="20" >&nbsp;&nbsp;顺序：<input type="text" name="sort[]" size="3" value='+(i+1)+'>&nbsp;&nbsp;<input name="Submit4" type="button" value="删除" onClick="this.parentNode.parentNode.removeChild(this.parentNode)" class="addmusicbutton" /></div>';
		$('#urldata').append(html);
	}
});
});
KE.show({
				id : 'content',
				items : [
		'source',  'undo', 'redo', 'cut', 'copy', 'paste',
		'plainpaste', 'wordpaste','justifyleft', 'justifycenter', 'justifyright',
		'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
		'superscript', 
		'title', 'fontname', 'fontsize','textcolor', 'bgcolor', 'bold',
		'italic', 'underline', 'strikethrough', 'removeformat','image',
		'flash', 'media', 'advtable', 'link', 'unlink'
	],
				 
				afterCreate : function(id) {
					KE.event.ctrl(document, 13, function() {
						KE.util.setData(id);
						document.forms['example'].submit();
					});
					KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {
						KE.util.setData(id);
						document.forms['example'].submit();
					});
				}
			});

</script>
<title>修改专辑</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>修改专辑</a></div>
  <table cellspacing="0">
    <form action="album_modify.php" method="post" enctype="multipart/form-data">
      <tr><?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('album_info')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['name'] = 'sec1';
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['show'] = true;
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['max'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['loop'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['step'] = 1;
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['start'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['step'] > 0 ? 0 : $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['loop']-1;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['show']) {
    $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['total'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['loop'];
    if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['total'] == 0)
        $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['show'] = false;
} else
    $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['total'] = 0;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['show']):

            for ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['index'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['start'], $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['iteration'] = 1;
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['iteration'] <= $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['total'];
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['index'] += $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['step'], $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['iteration']++):
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['rownum'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['iteration'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['index_prev'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['index'] - $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['index_next'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['index'] + $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['first']      = ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['iteration'] == 1);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['last']       = ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['iteration'] == $_smarty_tpl->tpl_vars['smarty']->value['section']['sec1']['total']);
?>
      
        <td align="right">专辑名称<font>*</font>：</td><input type="hidden" name="id" value="<?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['id'];?>
"/>
        <td><input type="text" class="text" name="title" value="<?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['title'];?>
"/></td>
      </tr>
      <tr>
        <td align="right">专辑类型<font>*</font>：</td>
       <td><select name="category" style="float:left;">
            <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('big')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
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
            <optgroup label="<?php echo $_smarty_tpl->getVariable('big')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['bigcategory'];?>
">
            <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec3']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('small')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
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
?>
            <?php if ($_smarty_tpl->getVariable('small')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec3']['index']]['bigcategory']==$_smarty_tpl->getVariable('big')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['bigcategory']){?>
            <option value="<?php echo $_smarty_tpl->getVariable('small')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec3']['index']]['smallcategory'];?>
">
            <?php echo $_smarty_tpl->getVariable('small')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec3']['index']]['smallcategory'];?>

            </option>
            <?php }?><?php endfor; endif; ?>
            </optgroup>
            <?php endfor; endif; ?>
          </select>
          <div style="float:left; margin-left:5px;"><?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['category'];?>

          <input type="text" value="<?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['category'];?>
" name="category1" type="hidden"/>
          <a href="#" onClick="this.parentNode.parentNode.removeChild(this.parentNode)">删除</a>
          </div>
          </td>
      </tr>
      <tr>
        <td align="right">专辑封面<font>*</font>：</td>
        <td><input type="file" name="pic"  style="float:left;"/><br><br><div style="float:left; margin-left:5px;">
        <img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['picture'];?>
" alt="<?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['title'];?>
" width="80" height="80"/>
          <input type="text" value="<?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['picture'];?>
" style="display:none" name="pic"/>
          <a href="#" onClick="this.parentNode.parentNode.removeChild(this.parentNode)">删除</a>
          </div></td>
      </tr>
      <tr><td align="right">歌手名称<font>*</font>：</td><td><input type="text" class="text" name="artist" value="<?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['artist'];?>
"/></td></tr>
      <tr><td align="right">唱片公司<font>*</font>：</td><td><input type="text" class="text" name="company" value="<?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['company'];?>
"/></td></tr>
       <tr><td align="right">发行时间<font>*</font>：</td><td><input type="text" class="text" name="time" value="<?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['time'];?>
"/></td></tr>
      <tr><td align="right">语言种类<font>*</font>：</td><td>
      <div style="margin-left:5px; float:left"><input type="checkbox" name="language[]" value="国语" >&nbsp;国语&nbsp;
      <input type="checkbox" name="language[]" value="粤语" >&nbsp;粤语&nbsp;
      <input type="checkbox" name="language[]" value="英语" >&nbsp;英语&nbsp;
      <input type="checkbox" name="language[]" value="日语" >&nbsp;日语&nbsp;
      <input type="checkbox" name="language[]" value="韩语" >&nbsp;韩语&nbsp;
          其他：<input type="text" name="language[]" size="5"/></div>
      <div style="margin-left:5px; float:left"><?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['language'];?>

          <input value="<?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['language'];?>
" type="hidden" name="language"/>
          <a href="#" onClick="this.parentNode.parentNode.removeChild(this.parentNode)">删除</a>
          </div>
      </td></tr>
        <tr><td align="right">来原地区<font>*</font>：</td>
        <td> <div style="margin-left:5px; float:left">
        <input type="checkbox" name="area[]" value="欧美" >&nbsp;欧美&nbsp;
      <input type="checkbox" name="area[]" value="大陆" >&nbsp;大陆&nbsp;
      <input type="checkbox" name="area[]" value="港台" >&nbsp;港台&nbsp;
      <input type="checkbox" name="area[]" value="日韩" >&nbsp;日韩&nbsp;
          其他：<input type="text" name="area[]" size="5"/></div> 
          <div style="margin-left:5px; float:left"><?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['area'];?>

          <input type="text" value="<?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['area'];?>
" style="display:none" name="area"/>
          <a href="#" onClick="this.parentNode.parentNode.removeChild(this.parentNode)">删除</a>
          </div></td></tr>
         <tr><td align="right">服务器<font>*</font>：</td><td>
         <select name="server" class="server">
         <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['serv']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('server')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['name'] = 'serv';
$_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['show'] = true;
$_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['max'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['loop'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['step'] = 1;
$_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['start'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['step'] > 0 ? 0 : $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['loop']-1;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['show']) {
    $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['total'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['loop'];
    if ($_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['total'] == 0)
        $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['show'] = false;
} else
    $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['total'] = 0;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['show']):

            for ($_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['index'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['start'], $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['iteration'] = 1;
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['iteration'] <= $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['total'];
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['index'] += $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['step'], $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['iteration']++):
$_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['rownum'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['iteration'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['index_prev'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['index'] - $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['index_next'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['index'] + $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['first']      = ($_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['iteration'] == 1);
$_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['last']       = ($_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['iteration'] == $_smarty_tpl->tpl_vars['smarty']->value['section']['serv']['total']);
?>
         <option value="<?php echo $_smarty_tpl->getVariable('server')->value[$_smarty_tpl->getVariable('smarty')->value['section']['serv']['index']]['server'];?>
"><?php echo $_smarty_tpl->getVariable('server')->value[$_smarty_tpl->getVariable('smarty')->value['section']['serv']['index']]['name'];?>
 | <?php echo $_smarty_tpl->getVariable('server')->value[$_smarty_tpl->getVariable('smarty')->value['section']['serv']['index']]['server'];?>
</option>
         <?php endfor; endif; ?>
         </select>
         </td></tr>
         <tr><td align="right">地址列表<font>*</font>：</td>
         <td>本专辑有&nbsp;<input class="s" type="text" size="5"/>&nbsp;首歌曲
                  <input name="Submit3" type="button" class="addmusicbutton" value="生成" id="seturl"/>
                  <br />
                  <div id="urldata" style="float:left; width:790px">
                  <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec2']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('ablum_music')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
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
                  <div class="musicplay">
                  <input type="text" name="url[]" size="70" value='<?php echo $_smarty_tpl->getVariable('ablum_music')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec2']['index']]['url'];?>
'>&nbsp;&nbsp;名称：<input type="text" name="songtitle[]" size="20" value="<?php echo $_smarty_tpl->getVariable('ablum_music')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec2']['index']]['title'];?>
" >&nbsp;&nbsp;顺序：<input type="text" name="sort[]" size="3" value='<?php echo $_smarty_tpl->getVariable('ablum_music')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec2']['index']]['pid'];?>
'>&nbsp;&nbsp;<input name="Submit4" type="button" value="删除" onClick="this.parentNode.parentNode.removeChild(this.parentNode)" class="addmusicbutton" /></div><?php endfor; endif; ?>
                  </div></td>
         </tr>
         <tr><td align="right">专辑介绍<font>*</font>：</td><td><textarea name="content" style="width:900px;height:300px;display:block;"><?php echo $_smarty_tpl->getVariable('album_info')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec1']['index']]['text'];?>
</textarea></td></tr>
         <tr><td></td>
         <td><input type="submit" class="button" value=" 添 加 " />
         <input class="button" value=" 清空 " type="reset"/>
         <input type="button" class="button" value=" 置顶 " onClick="window.scrollTo(0,0)" />
         </td></tr>
    </form><?php endfor; endif; ?>
  </table>
</div>
</body>
</html>
