<?php /* Smarty version Smarty3-b8, created on 2012-05-16 01:51:07
         compiled from "E:\wwwroot\smu/templates/template/gray\user.album.html" */ ?>
<?php /*%%SmartyHeaderCode:41194fb2978b118a88-89522434%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '3ec9c99c76f814e041f7db9ee55b74efbde62068' => 
    array (
      0 => 'E:\\wwwroot\\smu/templates/template/gray\\user.album.html',
      1 => 1337100738,
    ),
  ),
  'nocache_hash' => '41194fb2978b118a88-89522434',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<div class="user_album">
<div style="color:#d06e6f; font-size:16px; margin-bottom:10px; width:100%;border-bottom:#d6d6d6 1px dashed; line-height:40px">创建专辑</div>

<form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=album_submit" method="post" enctype="multipart/form-data">
<table class="user_album_info" cellpadding="5" cellspacing="0" border="0">
<tr><td class="td">专辑名称</td><td><input id="user_album_name" type="text" name="name" class="text" style="width:200px; margin-right:3px"/><font color="8ad1e9">类型</font>
<select name="category" id="user_album_category">
<option value="">=选择类型=</option>
<?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('rs')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?>
<option value="<?php echo $_smarty_tpl->getVariable('r')->value['smallcategory'];?>
"><?php echo $_smarty_tpl->getVariable('r')->value['smallcategory'];?>
</option>
<?php }} ?>
</select>
</td><td rowspan="3" valign="top"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/no_album_pic.png" width="150" height="150" id="pic"/><br/><div class="input-file">上传封面<input type="file" name="pic" id="pic"></div></td></tr>
<tr><td valign="top" class="td">歌手名称</td><td><input id="user_album_artist" type="text" name="artist" class="text" style="width:330px;"/></td></tr>
<tr><td valign="top" class="td">专辑简介</td><td><textarea id="user_album_text" name="text" class="text" style="width:325px; height:130px"></textarea></td></tr>
<tr><td colspan="3" class="td" style="border-bottom:#d6d6d6 1px dashed">地址列表</td></tr>
<tr><td colspan="3" style="color:#CCC">url格式：&nbsp;http://www.yoururl.com/mymp3.mp3(请保证该url能被下载！)</td></tr>
<?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']["i"]);
$_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['name'] = "i";
$_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['loop'] = is_array($_loop=5) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['show'] = true;
$_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['max'] = $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['loop'];
$_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['step'] = 1;
$_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['start'] = $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['step'] > 0 ? 0 : $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['loop']-1;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['show']) {
    $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['total'] = $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['loop'];
    if ($_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['total'] == 0)
        $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['show'] = false;
} else
    $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['total'] = 0;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['show']):

            for ($_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['index'] = $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['start'], $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['iteration'] = 1;
                 $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['iteration'] <= $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['total'];
                 $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['index'] += $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['step'], $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['iteration']++):
$_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['rownum'] = $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['iteration'];
$_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['index_prev'] = $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['index'] - $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['index_next'] = $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['index'] + $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['first']      = ($_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['iteration'] == 1);
$_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['last']       = ($_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['iteration'] == $_smarty_tpl->tpl_vars['smarty']->value['section']["i"]['total']);
?>
<tr class="tr"><td colspan="3">
<div><input type="text" name="song_url[]" class="text url" style="width:390px; margin-right:10px" value="请填写歌曲的url地址，目前只支持mp3文件..." onclick="this.value='http://'" autocomplete="off"/><input type="text" name="song_title[]" class="text song_title" style="width:140px;" value="请填写歌曲名称..." onclick="this.value=''" autocomplete="off"/></div><div class="close" title="删除本地址栏" onClick="this.parentNode.parentNode.removeChild(this.parentNode)">&nbsp;x</div></td></tr>
<?php endfor; endif; ?>
</table>
<table width="99%" cellpadding="5" cellspacing="0" border="0" style="float:left"><tr><td align="right"><input type="button" value="增加" class="btn" id="add_btn"/ title="增加一个地址栏">&nbsp;&nbsp;&nbsp;<input type="submit" class="btn" value="完成" id="add_album_finish"/>&nbsp;&nbsp;&nbsp;</td></tr></table>
</form>
</div>
<?php $_template = new Smarty_Internal_Template("ad.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>


<div class="maybelike" style="margin-left:30px">
  <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/maybelike.png" border="0" width="120" height="20" align="absmiddle"/></div>
  <div class="next"><a href="javascript:void(0)" id="maybelike" title="换一批"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/next.png" border="0"/></a></div>
  <ul><li class="onlyone">加载中...</li></ul>
</div>