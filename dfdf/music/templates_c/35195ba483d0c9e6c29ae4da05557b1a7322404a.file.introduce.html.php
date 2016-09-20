<?php /* Smarty version Smarty3-b8, created on 2013-03-11 12:59:10
         compiled from "F:/PHPNOW/vhosts/music/templates/template/deafault\introduce.html" */ ?>
<?php /*%%SmartyHeaderCode:16945513d649edae287-04960198%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '35195ba483d0c9e6c29ae4da05557b1a7322404a' => 
    array (
      0 => 'F:/PHPNOW/vhosts/music/templates/template/deafault\\introduce.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '16945513d649edae287-04960198',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include 'F:\PHPNOW\vhosts\music\lib\Smarty\plugins\modifier.trim.php';
if (!is_callable('smarty_modifier_truncate')) include 'F:\PHPNOW\vhosts\music\lib\Smarty\plugins\modifier.truncate.php';
?><style>
.left, .right {
	margin-left:20px
}
</style>
<!--[if IE 6]><style>.left{ margin-left:10px}</style><![endif]-->
<!--左边-->
<div class="left"> 

 <!--专题-->
 <!-- <div class="week">
    <div class="title_div"><span class="title">音乐专题列表</span><a style="float:right;font-size:12px;color:#666;text-decoration:none" href="introduce.php?skin=deafault&action=more_topic" title="更多音乐专题">更多</a></div>
    <div class="content">
      <ul>
        <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('topic')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
        <li><a href="introduce.php?skin=deafault&action=content_list&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['name'];?>
"><?php echo $_smarty_tpl->getVariable('s')->value['name'];?>
</a></li>
        <?php }} ?>
      </ul>
    </div>
  </div>-->
  <!--专题--> 
  
  
  <!--每周热点-->
  <div class="week">
    <div class="title_div"><span class="title">每周热点</span><a style="float:right;font-size:12px;color:#666;text-decoration:none" href="introduce.php?skin=deafault&action=list" title="更多音乐推荐文章">更多</a></div>
    <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('content')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
    <div class="content">
      <ul>
        <li class="title"><a href="content.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
</a></li>
        <li class="time"><?php echo $_smarty_tpl->getVariable('s')->value['time'];?>
</li>
        <li><a href="content.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"><img src="thumbs/thumbs/cache/w575h270/admin/<?php echo $_smarty_tpl->getVariable('s')->value['pic'];?>
" border="0" width="575" height="270" alt="<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"/></a></li>
        <li class="text"><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('s')->value['content'],165);?>
</li>
      </ul>
    </div>
    <?php }} ?> </div>
  <!--每周热点--> 
  
 
</div>
<!--右边-->
<div class="right"> 
  <!--随机专辑-->
  <div class="rand_album">
    <div class="title_div"><span class="title">随机专辑推荐</span></div>
    <ul>
      <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('random')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
      <li><a href="album.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
-<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
" border="0" width="100" height="100" alt="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
-<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"/></a></li>
      <?php }} ?>
    </ul>
  </div>
  
  <!--热门文章-->
  <div class="rand">
    <div class="title_div"><span class="title">热门文章</span></div>
    <ul>
      <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('hot')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
      <li><a href="content.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
》阅读次数：<?php echo $_smarty_tpl->getVariable('s')->value['click'];?>
"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
(<?php echo $_smarty_tpl->getVariable('s')->value['click'];?>
)</a></li>
      <?php }} ?>
    </ul>
  </div>
  
  <!--最新评论-->
  <div class="rand">
    <div class="title_div"><span class="title">最新评论</span></div>
    <ul>
      <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('read_comment')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
      <li><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['name']);?>
：<a href="content.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('s')->value['content_id'];?>
#ok<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['name'];?>
说：<?php echo $_smarty_tpl->getVariable('s')->value['text'];?>
"><?php echo smarty_modifier_trim(smarty_modifier_truncate($_smarty_tpl->getVariable('s')->value['text'],42));?>
</a></li>
      <?php }} ?>
    </ul>
  </div>
</div>
<?php $_template = new Smarty_Internal_Template('foot.html', $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
