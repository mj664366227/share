<?php /* Smarty version Smarty3-b8, created on 2012-03-27 03:17:44
         compiled from "E:\wwwroot\smu/templates/template/deafault\introduce_more.html" */ ?>
<?php /*%%SmartyHeaderCode:282894f713158747307-27992712%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '046344481df0c902f2d64972272d0c62576ebbec' => 
    array (
      0 => 'E:\\wwwroot\\smu/templates/template/deafault\\introduce_more.html',
      1 => 1329490434,
    ),
  ),
  'nocache_hash' => '282894f713158747307-27992712',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include 'E:\wwwroot\smu\lib\Smarty\plugins\modifier.trim.php';
if (!is_callable('smarty_modifier_truncate')) include 'E:\wwwroot\smu\lib\Smarty\plugins\modifier.truncate.php';
?><style>
.left, .right {
	margin-left:20px
}
</style>
<!--[if IE 6]><style>.left{ margin-left:10px}</style><![endif]-->
<!--左边-->
<div class="left">
 <?php if ($_smarty_tpl->getVariable('type')->value=='content'){?> 
   
  <!--每周热点-->
  <div class="week">
    <div class="title_div"><span class="title">更多每周热点</span></div>
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
  <div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div>
  <?php }?>
  
  <?php if ($_smarty_tpl->getVariable('type')->value=='topic'){?> 
     
  <!--专题列表-->
  <div class="week">
    <div class="title_div"><span class="title">更多音乐专题</span></div>
    <div class="content">
     <ul>
        <?php  $_smarty_tpl->tpl_vars['aa'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('content')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['aa']->key => $_smarty_tpl->tpl_vars['aa']->value){
?>
        <li><a href="introduce.php?skin=deafault&action=content_list&id=<?php echo $_smarty_tpl->getVariable('aa')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('aa')->value['name'];?>
"><?php echo $_smarty_tpl->getVariable('aa')->value['name'];?>
</a></li>
        <li style="margin-bottom:20px"><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('aa')->value['introduce'],154);?>
</li>
        <?php }} ?>
      </ul>
    </div>
   </div>
  <!--每周热点-->
  <div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div>
  <?php }?> </div>
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
      <li><?php echo $_smarty_tpl->getVariable('s')->value['name'];?>
：<a href="content.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('s')->value['content_id'];?>
#ok<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['name'];?>
说：<?php echo $_smarty_tpl->getVariable('s')->value['text'];?>
"><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('s')->value['text'],42);?>
</a></li>
      <?php }} ?>
    </ul>
  </div>
</div>
<?php $_template = new Smarty_Internal_Template('foot.html', $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
