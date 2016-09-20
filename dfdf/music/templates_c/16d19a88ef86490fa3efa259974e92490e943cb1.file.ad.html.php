<?php /* Smarty version Smarty3-b8, created on 2012-02-29 01:48:02
         compiled from "/home/libmill/domains/lovemusic.cc/public_html/templates/template/gray/ad.html" */ ?>
<?php /*%%SmartyHeaderCode:2712139274f4d1352ef43c7-39643303%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '16d19a88ef86490fa3efa259974e92490e943cb1' => 
    array (
      0 => '/home/libmill/domains/lovemusic.cc/public_html/templates/template/gray/ad.html',
      1 => 1330451121,
    ),
  ),
  'nocache_hash' => '2712139274f4d1352ef43c7-39643303',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<div class="slide" style="width:370px; margin-left:20px;float:left"><div class="slide_content"><div class="slide_nav"><?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('ad')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?><?php if ($_smarty_tpl->getVariable('a')->value['show']==1){?><dd class="dddd">&nbsp;</dd><?php }?><?php }} ?></div><div class="slide_content_list"><ul><?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('ad')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?><?php if ($_smarty_tpl->getVariable('a')->value['show']==1){?><li><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w320h150/admin/<?php echo $_smarty_tpl->getVariable('a')->value['picture'];?>
" border="0" width="370" height="160" alt="<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
"/></li><?php }?><?php }} ?></ul></div></div></div>