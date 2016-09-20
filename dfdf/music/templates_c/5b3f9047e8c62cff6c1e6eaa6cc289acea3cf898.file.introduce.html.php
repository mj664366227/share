<?php /* Smarty version Smarty3-b8, created on 2012-04-12 07:44:09
         compiled from "E:\wwwroot\smu/templates/template/gray\introduce.html" */ ?>
<?php /*%%SmartyHeaderCode:318334f8687c9e846d1-84355527%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '5b3f9047e8c62cff6c1e6eaa6cc289acea3cf898' => 
    array (
      0 => 'E:\\wwwroot\\smu/templates/template/gray\\introduce.html',
      1 => 1334159004,
    ),
  ),
  'nocache_hash' => '318334f8687c9e846d1-84355527',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include 'E:\wwwroot\smu\lib\Smarty\plugins\modifier.trim.php';
if (!is_callable('smarty_modifier_truncate')) include 'E:\wwwroot\smu\lib\Smarty\plugins\modifier.truncate.php';
?><div class="content">
<div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/rdtj.png" width="60" height="20"/></div>
<ul class="ul">
<?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('content')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
<li>
<div class="info"><div class="content_title"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
content.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
</a></div>
<div class="time"><?php echo $_smarty_tpl->getVariable('s')->value['time'];?>
</div></div>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
content.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w575h270/admin/<?php echo $_smarty_tpl->getVariable('s')->value['pic'];?>
" border="0" width="550" height="260" alt="<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"/></a>
<br/><div  class="sss"><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('s')->value['content'],190);?>
</div></li>
<?php }} ?>
</ul>
<div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div>
</div>
<?php $_template = new Smarty_Internal_Template("right.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>

<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
