<?php /* Smarty version Smarty3-b8, created on 2016-09-20 21:22:26
         compiled from "D:\nginx/www/share.git/somethingmusic/templates/template/deafault\index.html" */ ?>
<?php /*%%SmartyHeaderCode:1297657e138124cec65-37197614%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'bdc8810f7ee3b652dda0f6416cbd613ca4e7d27e' => 
    array (
      0 => 'D:\\nginx/www/share.git/somethingmusic/templates/template/deafault\\index.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '1297657e138124cec65-37197614',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_truncate')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.truncate.php';
if (!is_callable('smarty_modifier_trim')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.trim.php';
if (!is_callable('smarty_modifier_stripslashes')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.stripslashes.php';
if (!is_callable('smarty_modifier_strip')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.strip.php';
?><!--热点推荐-->
<div class="slide">
  <div class="slide_content">
    <div class="title_div"><span class="title">热点推荐</span></div>
    <div class="slide_nav"><?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('suggest')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?><dd class="dddd">&nbsp;</dd><?php }} ?></div>
    <div class="slide_content_list">
      <ul>
        <?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('suggest')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?>
        <li><img src="thumbs/thumbs/cache/w575h270/admin/<?php echo $_smarty_tpl->getVariable('r')->value['pic'];?>
" border="0" width="575" height="270" alt="<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
"/><span>
          <dd><a href="content.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
"><?php echo smarty_modifier_trim(smarty_modifier_truncate($_smarty_tpl->getVariable('r')->value['title'],36));?>
</a></dd>
         <dt><a href="content.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('r')->value['content']);?>
...</a></dt>
          </span></li>
        <?php }} ?>
      </ul>
    </div>
  </div>
</div>
<!--站内公告-->
<div class="notice">
  <div class="title_div"><span class="title">站内公告</span></div>
  <?php  $_smarty_tpl->tpl_vars['n'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('notice')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['n']->key => $_smarty_tpl->tpl_vars['n']->value){
?>
  <?php if ($_smarty_tpl->getVariable('n')->value['show']==1){?>
  <p>&nbsp;&nbsp;&nbsp;&nbsp;<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('n')->value['gonggao']);?>
</p>
  <?php }?>
  <?php }} ?></div>
<!--最近更新-->
<div class="music_introduction">
<div class="title_div"><span class="title">最近更新</span></div>
<ul class="music_introduction_list">


  <?php  $_smarty_tpl->tpl_vars['m'] = new Smarty_Variable;
 $_smarty_tpl->tpl_vars['k'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('album')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['m']->key => $_smarty_tpl->tpl_vars['m']->value){
 $_smarty_tpl->tpl_vars['k']->value = $_smarty_tpl->tpl_vars['m']->key;
?>
  <li id="on<?php echo $_smarty_tpl->getVariable('k')->value+1;?>
"><a href="album.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('m')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('m')->value['artist'];?>
-<?php echo smarty_modifier_stripslashes($_smarty_tpl->getVariable('m')->value['title']);?>
"><img src="thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('m')->value['picture'];?>
" border="0" width="190" height="190" alt="<?php echo $_smarty_tpl->getVariable('m')->value['artist'];?>
-<?php echo smarty_modifier_stripslashes($_smarty_tpl->getVariable('m')->value['title']);?>
"/><span id="show<?php echo $_smarty_tpl->getVariable('k')->value+1;?>
">
    <dd><?php echo smarty_modifier_trim(smarty_modifier_truncate($_smarty_tpl->getVariable('m')->value['artist'],14));?>
</dd>
    <dt><?php echo smarty_modifier_stripslashes(smarty_modifier_trim(smarty_modifier_truncate($_smarty_tpl->getVariable('m')->value['title'],14)));?>
</dt>
    </span></a></li>
  <?php }} ?>
  <li><img src="templates/template/deafault/images/white.png" border="0" width="575" height="65"/></li>
  <li style="width:575px;">
  </div>
  </li>
</ul>
<!--热门排行-->
<div class="hot">
  <div class="title_div"><span class="title">热门歌曲</span></div>
  <ul>
    <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('hot')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
    <li jplayer="<?php echo $_smarty_tpl->getVariable('s')->value['url'];?>
" id="<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
">
      <a href="album.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('s')->value['albumid'];?>
#<?php echo $_smarty_tpl->getVariable('s')->value[0];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
&nbsp;<?php echo $_smarty_tpl->getVariable('s')->value[3];?>
 专辑：《<?php echo $_smarty_tpl->getVariable('s')->value['album'];?>
》，点播次数：<?php echo $_smarty_tpl->getVariable('s')->value['have_played'];?>
"><div class="info"><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('s')->value['artist'],14);?>
&nbsp;<?php echo smarty_modifier_trim(smarty_modifier_truncate(smarty_modifier_strip($_smarty_tpl->getVariable('s')->value[3]),14));?>
<br/>
        专辑：《<?php echo smarty_modifier_trim(smarty_modifier_truncate(smarty_modifier_strip($_smarty_tpl->getVariable('s')->value['album']),16));?>
》</div></a>
      <div class="img"></div>
      <div class="pause"></div>
      <div class="play"></div>
    </li>
    <?php }} ?>
  </ul>
</div>
<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
