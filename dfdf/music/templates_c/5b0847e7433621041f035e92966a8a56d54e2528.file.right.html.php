<?php /* Smarty version Smarty3-b8, created on 2013-09-08 16:03:40
         compiled from "E:\htdocs\music/templates/template/gray\right.html" */ ?>
<?php /*%%SmartyHeaderCode:25157522c2f5cbc06c4-75825586%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '5b0847e7433621041f035e92966a8a56d54e2528' => 
    array (
      0 => 'E:\\htdocs\\music/templates/template/gray\\right.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '25157522c2f5cbc06c4-75825586',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.trim.php';
if (!is_callable('smarty_modifier_truncate')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.truncate.php';
?><div class="maybelike" id="_maybelike" style="height:375px">
  <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/maybelike.png" border="0" width="120" height="20" align="absmiddle"/></div>
  <div class="next"><a href="javascript:void(0)" id="maybelike_" title="换一批"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/next.png" border="0"/></a></div>
  <ul><li class="onlyone">加载中...</li></ul>
</div>


<?php if ($_smarty_tpl->getVariable('comment')->value!=''){?>
<div class="comment" id="hot_c">
<div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/hot_content.png" border="0" width="60" height="20" align="absmiddle"/></div>
<ul class="ls">
<?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('hot')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
<li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
content.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
》阅读次数：<?php echo $_smarty_tpl->getVariable('s')->value['click'];?>
"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;阅读次数：<?php echo $_smarty_tpl->getVariable('s')->value['click'];?>
</a></li>
<?php }} ?>
</ul>
</div>
<?php }?>

<div class="comment" id="comment_">
<div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/comment.png" border="0" width="60" height="20" align="absmiddle"/></div>
<ul class="ls">
<?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('read_comment')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
<li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
content.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('s')->value['content_id'];?>
#ok<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['name'];?>
说：<?php echo $_smarty_tpl->getVariable('s')->value['text'];?>
"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['name']);?>
：<?php echo smarty_modifier_trim(smarty_modifier_truncate($_smarty_tpl->getVariable('s')->value['text'],30));?>
</a></li>
<?php }} ?>
</ul>
</div>

<?php $_template = new Smarty_Internal_Template("ad.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
