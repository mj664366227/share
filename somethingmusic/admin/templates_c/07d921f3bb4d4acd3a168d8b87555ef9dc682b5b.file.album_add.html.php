<?php /* Smarty version Smarty3-b8, created on 2013-03-01 14:41:18
         compiled from "F:/PHPNOW/vhosts/music/admin/templates/template/album_add.html" */ ?>
<?php /*%%SmartyHeaderCode:49275130be0e5adb16-09857541%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '07d921f3bb4d4acd3a168d8b87555ef9dc682b5b' => 
    array (
      0 => 'F:/PHPNOW/vhosts/music/admin/templates/template/album_add.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '49275130be0e5adb16-09857541',
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
.highlight{ border:#F00 1px solid}
</style>
<script type="text/javascript" src="templates/template/jquery-1.4a2.js"></script>
<script type="text/javascript" src="../kindeditor/kindeditor.js"></script>
<script type="text/javascript">
$(document).ready(function(){
/*
$('#title').keyup(function(){
	var title=$(this).val();
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=check&title='+title,
		success:function(data)
		{
			if(data!='')
			{
				alert(data+'的《'+title+'》已经存在！');
				return false;
			}
		}
	})		
})
*/	
$('#seturl').click(function(){
	var s = $('.s').val();
	var server = $('.server').val();
	var folder = $('.folder').val()+'/';
	if($('#server').val()==null)
	{
		$('#server').addClass('highlight');
		alert('没有服务器！请先添加服务器！');
		return false;
	}
	if(s=='') 
	{
		$('.s').addClass('highlight');
		alert('请输入歌曲数目！');
		return false;
	}
	if(isFinite(s)==false) 
	{
		$('.s').addClass('highlight');
		alert('输入的必须为数字！');
		return false;
	}
	if(s<=0) 
	{
		$('.s').addClass('highlight');
		alert('曲目数不可能为0！');
		return false;
	}
	if(s>=100) 
	{
		$('.s').addClass('highlight');
		alert('曲目数太大！');
		return false;
	}
	if(folder=='/') 
	{
		$('.folder').addClass('highlight');
		alert('请输入专辑地址！');
		return false;
	}
	for(i=0;i<s;i++)
	{
		html='<div class="musicplay"><input type="text" name="url[]" size="70" value='+server+folder+(i+1)+'.mp3'+'>&nbsp;&nbsp;名称：<input type="text" name="songtitle[]" size="20" id="songtitle_'+i+'">&nbsp;&nbsp;顺序：<input type="text" name="sort[]" size="3" value='+(i+1)+'>&nbsp;&nbsp;<input name="Submit4" type="button" value="删除" onClick="this.parentNode.parentNode.removeChild(this.parentNode)" class="addmusicbutton" /></div>';
		$('#urldata').append(html);
	}
});
//提交前检查
$('#submit').click(function(){ 
    if($('#title').val()=='')
	{
		$('#title').addClass('highlight');
		alert('专辑名称未填写！');
		return false;
	}
	if($('#category').val()==null)
	{
		$('#category').addClass('highlight');
		alert('专辑类型未选择！请先添加专辑类型，再添加专辑！');
		return false;
	}
	if($('#pic').val()=='')
	{
		$('#pic').addClass('highlight');
		alert('专辑封面未上传！');
		return false;
	}
	if($('#artist').val()=='')
	{
		$('#artist').addClass('highlight');
		alert('歌手名称未填写！');
		return false;
	}
	if($('#company').val()=='')
	{
		$('#company').addClass('highlight');
		alert('唱片公司未填写！');
		return false;
	}
	if($('#time').val()=='')
	{
		$('#time').addClass('highlight');
		alert('发行时间未填写！');
		return false;
	}
	if($('#time').val()!='')
	{
		var date=$('#time').val();
		var d1=date.charAt(4);
		var d2=date.charAt(7);
		if(d1!='-'||d2!='-')
		{
			$('#time').addClass('highlight');
			alert('发行时间格式错误！');
			return false;
		}
	}
	if($('input:checked').length<=0&&$('#language1').val()=='')
	{
		$('#l').addClass('highlight');
		//$('#a').addClass('highlight');
		alert('语言种类未选择！');
		return false;
	}
	if($('input:checked').length<2&&$('#area1').val()=='')
	{
		//$('#l').addClass('highlight');
		$('#a').addClass('highlight');
		alert('来原地区未选择！');
		return false;
	}
	var s = $('.s').val();
	if(s=='')
	{
		$('.s').addClass('highlight');
		alert('请填写歌曲地址！');
		return false;
	}
	for(i=0;i<s;i++)
	{
		//alert($('#songtitle_'+i).val());
		if($('#songtitle_'+i).val()=='')
		{
			//$('#songtitle_'+i).addClass('highlight');这个暂时不会做
			alert('第'+(i+1)+'首歌名未填写！');
			return false;
		}
	}
	if($('#content').val()==''||$('#content').val()=='请输入文字...')
	{
		alert('专辑介绍未填写！');
		return false;
	}
});
//删除添加的类
	removeMyClass('#title');
	removeMyClass('#category');
	removeMyClass('#pic');
	removeMyClass('#artist');
	removeMyClass('#company');
	removeMyClass('#l');
	removeMyClass('#a');
	removeMyClass('.s');
	removeMyClass('#server');
	removeMyClass('.folder');
	removeMyClass('#time');

});
function removeMyClass(id_or_class)
{
	$(id_or_class).click(function(){
		$(this).removeClass('highlight');
		});
}
//编辑器初始化
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
<title>添加专辑</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>添加专辑</a></div>
  <table cellspacing="0">
    <form action="album_adding.php" method="post" enctype="multipart/form-data" id="form">
      <tr>
        <td align="right">专辑名称<font>*</font>：</td>
        <td><input type="text" class="text" name="title" id="title"/></td>
      </tr>
      <tr>
        <td align="right">专辑类型<font>*</font>：</td>
        <td><select name="category" id="category">
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
          </select></td>
      </tr>
      <tr>
        <td align="right">专辑封面<font>*</font>：</td>
        <td><input type="file" name="pic" id="pic" style="cursor:pointer"/></td>
      </tr>
      <tr><td align="right">歌手名称<font>*</font>：</td><td><input type="text" class="text" name="artist" id="artist"/></td></tr>
      <tr><td align="right">唱片公司<font>*</font>：</td><td><input type="text" class="text" name="company" id="company"/></td></tr>
       <tr><td align="right">发行时间<font>*</font>：</td><td><input type="text" class="text" name="time" id="time"/><font>(格式：yyyy-mm-dd)</font></td></tr>
      <tr><td align="right">语言种类<font>*</font>：</td><td id="l">
      <input type="checkbox" name="language[]" value="国语" id="language">&nbsp;国语&nbsp;
      <input type="checkbox" name="language[]" value="粤语" id="language">&nbsp;粤语&nbsp;
      <input type="checkbox" name="language[]" value="英语" id="language">&nbsp;英语&nbsp;
      <input type="checkbox" name="language[]" value="日语" id="language">&nbsp;日语&nbsp;
      <input type="checkbox" name="language[]" value="韩语" id="language">&nbsp;韩语&nbsp;
          其他：<input type="text" name="language[]" size="5" id="language1"/>
      </td></tr>
        <tr><td align="right">来原地区<font>*</font>：</td>
        <td id="a"> 
        <input type="checkbox" name="area[]" value="欧美" id="area" >&nbsp;欧美&nbsp;
      <input type="checkbox" name="area[]" value="大陆" id="area" >&nbsp;大陆&nbsp;
      <input type="checkbox" name="area[]" value="港台" id="area" >&nbsp;港台&nbsp;
      <input type="checkbox" name="area[]" value="日韩" id="area" >&nbsp;日韩&nbsp;
          其他：<input type="text" name="area[]" size="5" id="area1"/></td></tr>
         <tr><td align="right">服务器<font>*</font>：</td><td>
         <select name="server" class="server" id="server">
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
         <td>本专辑有&nbsp;<input class="s" type="text" size="5"/>&nbsp;首歌曲，专辑地址：&nbsp;<input class="folder" type="text" size="50"/>
                  <input name="Submit3" type="button" class="addmusicbutton" value="生成" id="seturl"/>
                  <br />
                  <div id="urldata" style="float:left; width:790px"></div></td>
         </tr>
         <tr><td align="right">专辑介绍<font>*</font>：</td><td><textarea name="content" id="content" style="width:900px;height:300px;display:block;">请输入文字...</textarea></td></tr>
         <tr><td></td>
         <td><input type="submit" class="button" value=" 添 加 " id="submit" />
         <input class="button" value=" 清空 " type="reset"/>
         <input type="button" class="button" value=" 返回顶部 " onClick="window.scrollTo(0,0)" />
         </td></tr>
    </form>
  </table>
</div>
</body>
</html>
