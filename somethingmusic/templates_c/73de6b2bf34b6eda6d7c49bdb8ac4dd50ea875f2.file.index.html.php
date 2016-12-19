<?php /* Smarty version Smarty3-b8, created on 2016-09-20 21:21:14
         compiled from "D:\nginx/www/share.git/somethingmusic/templates/template/gray\index.html" */ ?>
<?php /*%%SmartyHeaderCode:201557e137ca7ba8a5-91619631%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '73de6b2bf34b6eda6d7c49bdb8ac4dd50ea875f2' => 
    array (
      0 => 'D:\\nginx/www/share.git/somethingmusic/templates/template/gray\\index.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '201557e137ca7ba8a5-91619631',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_stripslashes')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.stripslashes.php';
if (!is_callable('smarty_modifier_truncate')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.truncate.php';
if (!is_callable('smarty_modifier_trim')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.trim.php';
if (!is_callable('smarty_modifier_strip')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.strip.php';
?><!--幻灯片-->
<div class="slide">
<div class="slide_content">
<div class="slide_nav"><?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('slide')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?><dd class="dddd">&nbsp;</dd><?php }} ?></div>
<div class="slide_content_list">
<ul>
<?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('slide')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?>
<li><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w930h300/admin/<?php echo $_smarty_tpl->getVariable('r')->value['url'];?>
" border="0" width="980" height="300" alt="<?php echo $_smarty_tpl->getVariable('r')->value['name'];?>
"/></li>
<?php }} ?>
</ul></div></div></div>
<!--最近更新-->
<div class="new">
<div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/new.png" border="0"/></div>
<div class="more"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=time" title="更多..."><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/more.png" border="0"/></a></div>
<ul><?php  $_smarty_tpl->tpl_vars['m'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('album')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['m']->key => $_smarty_tpl->tpl_vars['m']->value){
?><li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
album.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('m')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('m')->value['artist'];?>
-<?php echo smarty_modifier_stripslashes($_smarty_tpl->getVariable('m')->value['title']);?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('m')->value['picture'];?>
" border="0" width="190" height="190" alt="<?php echo $_smarty_tpl->getVariable('m')->value['artist'];?>
-<?php echo smarty_modifier_stripslashes($_smarty_tpl->getVariable('m')->value['title']);?>
"/></a><br/><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
album.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('m')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('m')->value['artist'];?>
-<?php echo smarty_modifier_stripslashes($_smarty_tpl->getVariable('m')->value['title']);?>
" id="artist"><?php echo smarty_modifier_trim(smarty_modifier_truncate($_smarty_tpl->getVariable('m')->value['artist'],16));?>
</a><br/><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
album.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('m')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('m')->value['artist'];?>
-<?php echo smarty_modifier_stripslashes($_smarty_tpl->getVariable('m')->value['title']);?>
" id="title"><?php echo smarty_modifier_stripslashes(smarty_modifier_trim(smarty_modifier_truncate($_smarty_tpl->getVariable('m')->value['title'],16)));?>
</a></li><?php }} ?></ul>
</div>
<!--热门歌曲-->
<div class="hot">
<div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/hot.png" border="0"/></div>
<ul>
<?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_smarty_tpl->tpl_vars['k'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('hot')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
 $_smarty_tpl->tpl_vars['k']->value = $_smarty_tpl->tpl_vars['s']->key;
?>
<li jplayer="<?php echo $_smarty_tpl->getVariable('s')->value['url'];?>
" id="<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
">
<div class="info">
<div class="info_number"><?php echo $_smarty_tpl->getVariable('k')->value+1;?>
</div>
<div class="info_img">
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
album.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('s')->value['albumid'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
&nbsp;<?php echo $_smarty_tpl->getVariable('s')->value[3];?>
 专辑：《<?php echo $_smarty_tpl->getVariable('s')->value['album'];?>
》，点播次数：<?php echo $_smarty_tpl->getVariable('s')->value['have_played'];?>
">
<img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
" width="52" height="52" border="0" alt="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
"/></a></div>
<div class="info_artist"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
album.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('s')->value['albumid'];?>
#<?php echo $_smarty_tpl->getVariable('s')->value[0];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
&nbsp;<?php echo $_smarty_tpl->getVariable('s')->value[3];?>
 专辑：《<?php echo $_smarty_tpl->getVariable('s')->value['album'];?>
》，点播次数：<?php echo $_smarty_tpl->getVariable('s')->value['have_played'];?>
"><?php echo smarty_modifier_trim(smarty_modifier_truncate(smarty_modifier_strip($_smarty_tpl->getVariable('s')->value[3]),28));?>
</a></div>
<div class="info_song"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
album.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('s')->value['albumid'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
&nbsp;<?php echo $_smarty_tpl->getVariable('s')->value[3];?>
 专辑：《<?php echo $_smarty_tpl->getVariable('s')->value['album'];?>
》，点播次数：<?php echo $_smarty_tpl->getVariable('s')->value['have_played'];?>
"><?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
  专辑《<?php echo smarty_modifier_trim(smarty_modifier_truncate(smarty_modifier_strip($_smarty_tpl->getVariable('s')->value['album']),28));?>
》</a></div>
<div class="img"></div>
<div class="pause"></div>
<div class="play"></div></div></li>
<?php }} ?>
</ul>
</div>
<!--广告-->
<div class="ad"><?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('ad')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?><a<?php if ($_smarty_tpl->getVariable('a')->value['url']!=''){?> href="<?php echo $_smarty_tpl->getVariable('a')->value['url'];?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
" target="_blank"<?php }?> title="<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
" target="_blank"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w320h150/admin/<?php echo $_smarty_tpl->getVariable('a')->value['picture'];?>
" width="350" height="150" border="0"/></a><?php }} ?>
<!--热点推荐-->
<div class="tui">
<div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/tui.png" border="0"/></div>
<div class="more"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
introduce.php?skin=gray&action=all" title="更多..."><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/more.png" border="0"/></a></div><br><br>
<?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('suggest')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
content.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
"><?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
</a><br><br>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
content.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w575h270/admin/<?php echo $_smarty_tpl->getVariable('r')->value['pic'];?>
" border="0" width="360" height="200" alt="<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
" align="top" id="tui_img"/></a><br><br><span><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('r')->value['content']);?>
...</span><br><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
content.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
" id="detail">>> 查看详细</a>
<?php }} ?>
</div></div>
<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
