<?php /* Smarty version Smarty3-b8, created on 2012-02-29 01:48:22
         compiled from "/home/libmill/domains/lovemusic.cc/public_html/templates/template/gray/introduce.html" */ ?>
<?php /*%%SmartyHeaderCode:7161172824f4d136669d0c8-01575872%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '506b9b51f6bafcc64dff81892a1ae4f93ccbf0f3' => 
    array (
      0 => '/home/libmill/domains/lovemusic.cc/public_html/templates/template/gray/introduce.html',
      1 => 1330451128,
    ),
  ),
  'nocache_hash' => '7161172824f4d136669d0c8-01575872',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include '/home/libmill/domains/lovemusic.cc/public_html/lib/Smarty/plugins/modifier.trim.php';
if (!is_callable('smarty_modifier_truncate')) include '/home/libmill/domains/lovemusic.cc/public_html/lib/Smarty/plugins/modifier.truncate.php';
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
" border="0" width="575" height="270" alt="<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"/></a>
<br/><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('s')->value['content'],190);?>
</li>
<?php }} ?>
</ul>
<div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div>
</div>
<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
