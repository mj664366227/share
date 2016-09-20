<?php /* Smarty version Smarty3-b8, created on 2013-03-10 18:54:06
         compiled from "F:/PHPNOW/vhosts/music/templates/template/deafault\musician_look.html" */ ?>
<?php /*%%SmartyHeaderCode:31191513c664e1c5301-92214073%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '83415752fcf40099cdaee148d63bfa548f09fe5c' => 
    array (
      0 => 'F:/PHPNOW/vhosts/music/templates/template/deafault\\musician_look.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '31191513c664e1c5301-92214073',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!--左边-->

<?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('singer')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
<div class="left"> 
  <!--<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
开始-->
  <div class="singer">
    <div class="title_div" style="width:575px;"><span class="title"><?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
</span></div>
    <div class="pic"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w184h184/admin/<?php echo $_smarty_tpl->getVariable('a')->value['pic'];?>
" border="0" width="190" height="190" alt="<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
"/></div>
    <div class="info"><?php echo $_smarty_tpl->getVariable('a')->value['introduce'];?>
</div>
  </div>
  <!--<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
结束--> 
  
  <!--发行专辑开始-->
  <div class="zhuanji">
    <div class="title_div" style="width:580px;"><span class="title">发行专辑</span></div>
    <ul>
    <?php if ($_smarty_tpl->getVariable('no_album')->value=='0'){?><li>我们正在努力添加中...</li>
    <?php }else{ ?>
      <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('album')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
      <li><a href="album.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value['artist'];?>
-<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
"><img src="thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('a')->value['picture'];?>
" border="0" width="135" height="135" alt="<?php echo $_smarty_tpl->getVariable('a')->value['artist'];?>
-<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
"/></a></li>
      <?php }} ?>
      <?php }?>
    </ul>
  </div>
  
  <!--发行专辑结束--> 
</div>
<?php }} ?> 
<!--右边-->
<div class="right"> 
  <!--广告开始-->
  <div class="ad">
    <div class="slide">
      <div class="slide_content">
        <div class="slide_nav"> <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('ad')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
          <?php if ($_smarty_tpl->getVariable('a')->value['show']==1){?>
          <dd class="ddd" title="<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
"><?php echo $_smarty_tpl->getVariable('a')->value['show_id'];?>
</dd>
          <?php }?>
          <?php }} ?> </div>
      </div>
      <div class="slide_content_list">
        <ul>
          <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('ad')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
          <?php if ($_smarty_tpl->getVariable('a')->value['show']==1){?>
          <li><a<?php if ($_smarty_tpl->getVariable('a')->value['url']!=''){?> href="<?php echo $_smarty_tpl->getVariable('a')->value['url'];?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
" target="_blank"<?php }?>><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w320h150/admin/<?php echo $_smarty_tpl->getVariable('a')->value['picture'];?>
" border="0" width="320" height="150" alt="<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
"/></a></li>
          <?php }?>
          <?php }} ?>
        </ul>
      </div>
    </div>
  </div>
  <!--广告结束--> 
  
  <!--其他歌手开始-->
  <div class="other_artist">
    <div class="title_div" style="width:320px;"><span class="title">相关音乐人</span></div>
    <div class="clear"></div>
    <ul>
      <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('other')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
      <li> <a href="musician.php?skin=deafault&action=look&id=<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
"><img src="thumbs/thumbs/cache/w184h184/admin/<?php echo $_smarty_tpl->getVariable('a')->value['pic'];?>
" border="0" width="100" height="100" alt="<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
"/></a> </li>
      <?php }} ?>
    </ul>
  </div>
  <!--其他歌手结束--> 
</div>
<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
