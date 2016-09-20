<?php /* Smarty version Smarty3-b8, created on 2012-05-16 02:32:56
         compiled from "E:\wwwroot\smu/templates/template/gray\user.album_modify.html" */ ?>
<?php /*%%SmartyHeaderCode:109084fb2a158a2bea1-54511512%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '9a411eed93262a06eeb7721d97c24a4b5714a49c' => 
    array (
      0 => 'E:\\wwwroot\\smu/templates/template/gray\\user.album_modify.html',
      1 => 1337009353,
    ),
  ),
  'nocache_hash' => '109084fb2a158a2bea1-54511512',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include 'E:\wwwroot\smu\lib\Smarty\plugins\modifier.trim.php';
?><div class="user_album">
<div style="color:#d06e6f; font-size:16px; margin-bottom:10px; width:100%;border-bottom:#d6d6d6 1px dashed; line-height:40px">创建专辑</div>

<form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=modify" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="<?php echo $_smarty_tpl->getVariable('album')->value['id'];?>
"/>
<input type="hidden" name="old_pic" value="<?php echo $_smarty_tpl->getVariable('album')->value['pic'];?>
"/>
<table class="user_album_info" cellpadding="5" cellspacing="0" border="0">
<tr><td class="td">专辑名称</td><td><input value="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['name']);?>
" id="user_album_name" type="text" name="name" class="text" style="width:200px; margin-right:3px"/><font color="8ad1e9">类型</font>
<select name="category" id="user_album_category">
<option value="">=选择类型=</option>
<?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('rs')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?>
<?php if (smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['category'])==$_smarty_tpl->getVariable('r')->value['smallcategory']){?><option value="<?php echo $_smarty_tpl->getVariable('r')->value['smallcategory'];?>
" selected="selected"><?php echo $_smarty_tpl->getVariable('r')->value['smallcategory'];?>
</option>
<?php }else{ ?>
<option value="<?php echo $_smarty_tpl->getVariable('r')->value['smallcategory'];?>
"><?php echo $_smarty_tpl->getVariable('r')->value['smallcategory'];?>
</option>
<?php }?>
<?php }} ?>
</select>
</td><td rowspan="3" valign="top"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w200h200/<?php echo $_smarty_tpl->getVariable('album')->value['pic'];?>
" width="150" height="150" id="pic"/><br/><span style="font-size:12px; color:#CCC">不重新上传则不修改</span><br/><div class="input-file">上传封面<input type="file" name="pic" id="pic"></div></td></tr>
<tr><td valign="top" class="td">歌手名称</td><td><input value="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['artist']);?>
" id="user_album_artist" type="text" name="artist" class="text" style="width:330px;"/></td></tr>
<tr><td valign="top" class="td">专辑简介</td><td><textarea id="user_album_text" name="text" class="text" style="width:325px; height:130px"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['text']);?>
</textarea></td></tr>
<tr><td colspan="3" class="td" style="border-bottom:#d6d6d6 1px dashed">地址列表</td></tr>
<?php  $_smarty_tpl->tpl_vars['m'] = new Smarty_Variable;
 $_smarty_tpl->tpl_vars['key'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('list')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['m']->key => $_smarty_tpl->tpl_vars['m']->value){
 $_smarty_tpl->tpl_vars['key']->value = $_smarty_tpl->tpl_vars['m']->key;
?>
<tr class="tr"><td colspan="3">
<div><input value="<?php echo $_smarty_tpl->getVariable('m')->value['url'];?>
" type="text" name="song_url[]" class="text url" style="width:390px; margin-right:10px" autocomplete="off"/><input value="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['name']);?>
" type="text" name="song_title[]" class="text song_title" style="width:140px;" autocomplete="off"/></div><div class="close" title="删除本地址栏" onClick="this.parentNode.parentNode.removeChild(this.parentNode)">&nbsp;x</div></td></tr>
<?php }} ?>
</table>
<table width="99%" cellpadding="5" cellspacing="0" border="0" style="float:left"><tr><td align="right"><input type="button" value="增加" class="btn" id="add_btn"/ title="增加一个地址栏">&nbsp;&nbsp;&nbsp;<input type="submit" class="btn" value="完成" id="add_album_finish"/>&nbsp;&nbsp;&nbsp;</td></tr></table>
</form>
</div>
<?php $_template = new Smarty_Internal_Template("ad.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>


<div class="maybelike" style="margin-top:-120px; margin-left:30px">
  <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/maybelike.png" border="0" width="120" height="20" align="absmiddle"/></div>
  <div class="next"><a href="javascript:void(0)" id="maybelike" title="换一批"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/next.png" border="0"/></a></div>
  <ul><li class="onlyone">加载中...</li></ul>
</div>