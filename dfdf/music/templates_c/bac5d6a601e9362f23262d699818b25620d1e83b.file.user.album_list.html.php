<?php /* Smarty version Smarty3-b8, created on 2012-05-16 01:32:07
         compiled from "E:\wwwroot\smu/templates/template/gray\user.album_list.html" */ ?>
<?php /*%%SmartyHeaderCode:23414fb2931723f931-34257232%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'bac5d6a601e9362f23262d699818b25620d1e83b' => 
    array (
      0 => 'E:\\wwwroot\\smu/templates/template/gray\\user.album_list.html',
      1 => 1337100818,
    ),
  ),
  'nocache_hash' => '23414fb2931723f931-34257232',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_truncate')) include 'E:\wwwroot\smu\lib\Smarty\plugins\modifier.truncate.php';
?><div class="user_album">
<div style="color:#d06e6f; font-size:16px; margin-bottom:10px; width:100%;border-bottom:#d6d6d6 1px dashed; line-height:40px">用户专辑列表<input type="button" class="btn" value="我要分享" style=" position:relative; left:410px" title="添加我喜欢的专辑" onclick="window.location='<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=album'"/></div>
<ul style="color:#969696">
<?php  $_smarty_tpl->tpl_vars['l'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('list')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['l']->key => $_smarty_tpl->tpl_vars['l']->value){
?>
<li><div style="width:190px; height:190px; float:left;"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=album_info&album_id=<?php echo $_smarty_tpl->getVariable('l')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('l')->value['name'];?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w200h200/<?php echo $_smarty_tpl->getVariable('l')->value['pic'];?>
" width="190" width="190" border="0"/></a></div>
<div style="margin-left:10px; width:390px; float:left; line-height:25px">
<font color="8ad1e9">添&nbsp;&nbsp;加&nbsp;&nbsp;者：</font><?php if ($_smarty_tpl->getVariable('my')->value==$_smarty_tpl->getVariable('l')->value['uid']){?>我自己<div class="others"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=album_info&album_id=<?php echo $_smarty_tpl->getVariable('l')->value['id'];?>
&uid=<?php echo $_smarty_tpl->getVariable('l')->value['uid'];?>
">修改</a>&nbsp;&nbsp;<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=delete&id=<?php echo $_smarty_tpl->getVariable('l')->value['id'];?>
&page=<?php echo $_smarty_tpl->getVariable('cur_page')->value;?>
" onclick="confirm('你确定要删除《<?php echo $_smarty_tpl->getVariable('l')->value['name'];?>
》吗？')">删除</a></div><?php }else{ ?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=index&look=true&uid=<?php echo $_smarty_tpl->getVariable('l')->value['uid'];?>
" title="点击查看<?php echo $_smarty_tpl->getVariable('l')->value['nick'];?>
的主页"><?php echo $_smarty_tpl->getVariable('l')->value['nick'];?>
</a><?php }?><br/>
<font color="8ad1e9">专辑名称：</font><?php echo $_smarty_tpl->getVariable('l')->value['name'];?>
<br/>
<font color="8ad1e9">音乐风格：</font><?php echo $_smarty_tpl->getVariable('l')->value['category'];?>
<br/>
<font color="8ad1e9">专辑介绍：</font><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('l')->value['text'],54);?>

</div></li>
<?php }} ?>
</ul>
<?php if ($_smarty_tpl->getVariable('page')->value){?><div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div><?php }?>
</div>
<?php $_template = new Smarty_Internal_Template("ad.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>

<?php if ($_smarty_tpl->getVariable('friend_introduce')->value){?>
<div class="friend_introduce">
<div style="color:#d06e6f; font-size:16px; margin-bottom:10px; width:100%;border-bottom:#d6d6d6 1px dashed; line-height:40px">好友推荐
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" style="color:#d06e6f; text-decoration:none; cursor:pointer;font-size:14px;" id="next_friend_introduce">换一批</a>
</div>
<ul>
<?php  $_smarty_tpl->tpl_vars['f'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('friend_introduce')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['f']->key => $_smarty_tpl->tpl_vars['f']->value){
?>
<li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=index&look=true&uid=<?php echo $_smarty_tpl->getVariable('f')->value['id'];?>
" title="点击查看<?php echo $_smarty_tpl->getVariable('f')->value['nick'];?>
的主页"><img src="<?php echo $_smarty_tpl->getVariable('f')->value['avatar'];?>
" width="70" height="70" border="0"/></a><br/><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=add_friend&uid=<?php echo $_smarty_tpl->getVariable('f')->value['id'];?>
&from=album_list" title="加为<?php echo $_smarty_tpl->getVariable('f')->value['nick'];?>
好友">加好友</a></li>
<?php }} ?>
</ul>
</div>
<?php }?>
