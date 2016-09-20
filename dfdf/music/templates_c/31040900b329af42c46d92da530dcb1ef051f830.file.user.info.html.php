<?php /* Smarty version Smarty3-b8, created on 2012-05-16 01:32:00
         compiled from "E:\wwwroot\smu/templates/template/gray\user.info.html" */ ?>
<?php /*%%SmartyHeaderCode:59084fb29310907561-33290322%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '31040900b329af42c46d92da530dcb1ef051f830' => 
    array (
      0 => 'E:\\wwwroot\\smu/templates/template/gray\\user.info.html',
      1 => 1337102854,
    ),
  ),
  'nocache_hash' => '59084fb29310907561-33290322',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<div class="step">
<ul class="step_nav">
<li><?php if ($_smarty_tpl->getVariable('step')->value==1){?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=info&step=1"><font color="8ad1e9">第一步</font></a><?php }else{ ?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=info&step=1">第一步</a><?php }?></li>
<li><?php if ($_smarty_tpl->getVariable('step')->value==2){?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=info&step=2"><font color="8ad1e9" title="你选择的音乐风格和歌手将会出现在你的私人电台中">第二步</font></a><?php }else{ ?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=info&step=2" title="你选择的音乐风格和歌手将会出现在你的私人电台中">第二步</a><?php }?></li>
<li><?php if ($_smarty_tpl->getVariable('step')->value==3){?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=info&step=3"><font color="8ad1e9">第三步</font></a><?php }else{ ?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=info&step=3">第三步</a><?php }?></li>
<li><font color="d06e6f">个人设置</font></li>
</ul>
<div class="step_body">
<?php if ($_smarty_tpl->getVariable('step')->value==1){?>
<form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=info" method="post">
<table cellpadding="0" cellspacing="15">
<tr><td width="100" align="right">昵称<font color="#FF0000">*</font></td><td><input type="text" class="text" name="nick" autocomplete="off" id="nick" value="<?php echo $_smarty_tpl->getVariable('step1')->value['nick'];?>
"/></td></tr>
<tr><td align="right">真实姓名</td><td><input type="text" class="text" name="realname" autocomplete="off" id="realname" value="<?php echo $_smarty_tpl->getVariable('step1')->value['realname'];?>
"/></td></tr>
<tr><td align="right">系别</td><td><input type="text" class="text" name="xi" autocomplete="off" id="xi" value="<?php echo $_smarty_tpl->getVariable('step1')->value['xi'];?>
"/></td></tr>
<tr><td align="right">班级</td><td><input type="text" class="text" name="classname" autocomplete="off" id="classname" value="<?php echo $_smarty_tpl->getVariable('step1')->value['classname'];?>
"/></td></tr>
<tr><td></td><td align="right"><input type="submit" value="下一步" class="btn" name="step1" id="step1" title="下一步设置你私人电台中的选择的音乐风格和歌手"/></td></tr>
</table>
</form>
<?php }elseif($_smarty_tpl->getVariable('step')->value==2){?>
<div class="style">喜欢的音乐风格</div>
<form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=info&step=2" method="post">
<div id="music_style">
<?php  $_smarty_tpl->tpl_vars['ss'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('style_list')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['ss']->key => $_smarty_tpl->tpl_vars['ss']->value){
?>
<?php if ($_smarty_tpl->getVariable('ss')->value){?>
<label style="color:#d06e6f;font-size:14px;margin:0 10px 0 0"><input checked="checked" name="style[]" class="checkbox" type="checkbox" style="display:none" value="<?php echo $_smarty_tpl->getVariable('ss')->value;?>
"/><?php echo $_smarty_tpl->getVariable('ss')->value;?>
<img src="templates/template/gray/images/x.png" width="12" height="12" style="cursor:pointer" title="取消<?php echo $_smarty_tpl->getVariable('ss')->value;?>
" onClick="this.parentNode.parentNode.removeChild(this.parentNode)"/></label><?php }?>
<?php }} ?>
<style>#music_style{ width:100%; height:auto}</style>
</div>
<ul class="list">
<?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('style')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
<li><a href="javascript:void(0)" title="<?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
" class="music_style_btn"><?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
</a></li>
<?php }} ?>
</ul>
<div class="style">喜欢的歌手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" style="text-decoration:none; color:#d06e6f" title="换一批" id="next_artist_">换一批</a></div>
<div id="music_srtist">
<?php  $_smarty_tpl->tpl_vars['sss'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('artist_list')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['sss']->key => $_smarty_tpl->tpl_vars['sss']->value){
?>
<?php if ($_smarty_tpl->getVariable('sss')->value){?>
<label style="color:#d06e6f;font-size:14px;margin:0 10px 0 0"><input checked="checked" name="artist[]" class="checkbox" type="checkbox" style="display:none" value="<?php echo $_smarty_tpl->getVariable('sss')->value;?>
"/><?php echo $_smarty_tpl->getVariable('sss')->value;?>
<img src="templates/template/gray/images/x.png" width="12" height="12" style="cursor:pointer" title="取消<?php echo $_smarty_tpl->getVariable('ss')->value;?>
" onClick="this.parentNode.parentNode.removeChild(this.parentNode)"/></label><?php }?>
<?php }} ?>
<style>#music_srtist{ width:100%; height:auto}</style>
</div>
<ul class="list" id="music_srtist_list">
<?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('artist')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
<li style="width:170px; text-align:left"><a href="javascript:void(0)" title="<?php echo $_smarty_tpl->getVariable('a')->value['artist'];?>
" class="music_srtist_btn"><?php echo $_smarty_tpl->getVariable('a')->value['artist'];?>
</a></li>
<?php }} ?>
</ul>
<div style="width:100%; text-align:right; margin:10px 0 0 0"><input type="submit" value="下一步" class="btn" name="step2" id="step2" title="下一步设置你的个人头像"/></div>
</form>
<?php }else{ ?>
<?php echo $_smarty_tpl->getVariable('avatar')->value;?>

<div style="width:100%; text-align:right; margin:10px 0 0 0"><form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=info&step=3" method="post"><input type="submit" value="完成" class="btn" name="step3" id="step3"/></form></div>
<?php }?>
</div>
</div>
<?php $_template = new Smarty_Internal_Template("ad.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
