<?php /* Smarty version Smarty3-b8, created on 2013-05-08 02:53:36
         compiled from "F:/PHPNOW/vhosts/music/templates/template/gray\user.index.html" */ ?>
<?php /*%%SmartyHeaderCode:196715189be3090bdf2-58960815%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '6f6c2de6745e1242d7f9a6ab4f31cbce0191aedd' => 
    array (
      0 => 'F:/PHPNOW/vhosts/music/templates/template/gray\\user.index.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '196715189be3090bdf2-58960815',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include 'F:\PHPNOW\vhosts\music\lib\Smarty\plugins\modifier.trim.php';
?><div class="user">
<div class="left">
<div class="user_card">
<img src="<?php echo $_smarty_tpl->getVariable('avatar')->value;?>
" width="160" height="160" <?php if ($_smarty_tpl->getVariable('other')->value){?>title="我的头像"<?php }else{ ?>title="<?php echo $_smarty_tpl->getVariable('nick')->value;?>
的头像"<?php }?> border="0"/>
<div class="username"><?php echo $_smarty_tpl->getVariable('nick')->value;?>
</div>
<ul>
<li><?php if ($_smarty_tpl->getVariable('other')->value){?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=info&step=3" title="更换我的头像">更换头像</a>&nbsp;&nbsp;
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=info" title="修改我的资料">修改资料</a><?php }else{ ?><?php if (!$_smarty_tpl->getVariable('is_friend')->value){?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=add_friend&uid=<?php echo $_smarty_tpl->getVariable('uid')->value;?>
&from=index">加好友</a><?php }?></li>
<?php }?>
</ul>
<div class="sign">
<?php if (!$_smarty_tpl->getVariable('sign')->value){?><?php echo $_smarty_tpl->getVariable('pms')->value['sign'];?>
<?php }else{ ?><?php echo $_smarty_tpl->getVariable('sign')->value;?>
<?php }?>
</div>
<div class="sign_area">
<textarea id="sign_content"><?php echo $_smarty_tpl->getVariable('sign')->value;?>
</textarea>
</div>
<?php if ($_smarty_tpl->getVariable('other')->value){?>
<div class="edit_sign" title="修改签名">修改签名</div>
<div class="edit_sign_submit" title="提交">提交</div>
<?php }?>
</div>
<?php if ($_smarty_tpl->getVariable('other')->value){?>
<div class="friend">
<?php if ($_smarty_tpl->getVariable('friend_list')->value){?>
<ul>
<?php  $_smarty_tpl->tpl_vars['f'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('friend_list')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['f']->key => $_smarty_tpl->tpl_vars['f']->value){
?>
<li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=index&look=true&uid=<?php echo $_smarty_tpl->getVariable('f')->value['friendid'];?>
" title="点击查看<?php echo $_smarty_tpl->getVariable('f')->value['nick'];?>
的主页"><img src="<?php echo $_smarty_tpl->getVariable('f')->value['avatar'];?>
" width="60" height="60" alt="<?php echo $_smarty_tpl->getVariable('f')->value['friendid'];?>
" border="0"/></a></li>
<?php }} ?>
</ul>
<?php if ($_smarty_tpl->getVariable('friend_list_num')->value>=6){?>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=friend" style="float:right; margin:-10px 15px 15px 0; font-size:12px" title="更多好友...">更多好友...</a>
<?php }?>
<?php }else{ ?>
<?php echo $_smarty_tpl->getVariable('pms')->value['no_friend'];?>

<?php }?>
</div>

<?php }?>
</div>

<div class="user_new_action">
<div class="user_title"><?php if ($_smarty_tpl->getVariable('other')->value){?>最新动态<?php }else{ ?><?php echo $_smarty_tpl->getVariable('nick')->value;?>
的动态<?php }?>
<?php if ($_smarty_tpl->getVariable('other')->value){?>
<br/>
<?php if ($_smarty_tpl->getVariable('type')->value==1){?><span>所有动态</span><?php }else{ ?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=index&type=1" title="查看所有动态">所有动态</a><?php }?>
<?php if ($_smarty_tpl->getVariable('has_friend')->value){?><?php if ($_smarty_tpl->getVariable('type')->value==2){?>&nbsp;<span>好友动态</span><?php }else{ ?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=index&type=2" title="查看好友动态">好友动态</a><?php }?><?php }?>&nbsp;
<?php if ($_smarty_tpl->getVariable('type')->value==3){?><span>我的动态</span><?php }else{ ?><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=index&type=3" title="查看我的动态">我的动态</a><?php }?>
<?php }?>
</div>
<ul>
<?php if ($_smarty_tpl->getVariable('action')->value){?>
<?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('action')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
<li>
<div class="user_avatar"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=index&look=true&uid=<?php echo $_smarty_tpl->getVariable('a')->value['uid'];?>
" title="查看<?php echo $_smarty_tpl->getVariable('a')->value['nick'];?>
的主页"><img src="<?php echo $_smarty_tpl->getVariable('a')->value['avatar'];?>
" width="80" height="80" title="<?php echo $_smarty_tpl->getVariable('a')->value['nick'];?>
" border="0"/></a></div>
<div class="title"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
api.php?skin=gray&action=user&mod=index&look=true&uid=<?php echo $_smarty_tpl->getVariable('a')->value['uid'];?>
" title="查看<?php echo $_smarty_tpl->getVariable('a')->value['nick'];?>
的主页"><?php echo $_smarty_tpl->getVariable('a')->value['nick'];?>
</a>&nbsp;&nbsp;<?php echo $_smarty_tpl->getVariable('a')->value['time'];?>
</div>
<div class="action"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('a')->value['action']);?>
</div>
</li>
<?php }} ?>
<?php }else{ ?>
<br/>这家伙很懒，神马都没留下。
<?php }?>
</ul>
<?php if ($_smarty_tpl->getVariable('page')->value){?>
<div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div>
<?php }?>
</div>
</div>