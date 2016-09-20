<?php /* Smarty version Smarty3-b8, created on 2016-09-20 21:22:19
         compiled from "D:\nginx/www/share.git/somethingmusic/templates/template/gray\resources.html" */ ?>
<?php /*%%SmartyHeaderCode:3087157e1380b0c15b6-53097280%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '98f3430d2c58458ba713e7f54e984ef9ab9fa017' => 
    array (
      0 => 'D:\\nginx/www/share.git/somethingmusic/templates/template/gray\\resources.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '3087157e1380b0c15b6-53097280',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.trim.php';
if (!is_callable('smarty_modifier_stripslashes')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.stripslashes.php';
if (!is_callable('smarty_modifier_truncate')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.truncate.php';
?><!--分类查询-->
<div class="sort">
<ul>
<li><div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/area.png" width="30" height="15"/></div>
<div class="type">
      <ul>
        <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('area')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
        <?php if ($_smarty_tpl->getVariable('action')->value==$_smarty_tpl->getVariable('s')->value['id']){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['bigcategory'];?>
"><font color="#e7999a"><?php echo $_smarty_tpl->getVariable('s')->value['bigcategory'];?>
</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['bigcategory'];?>
"><?php echo $_smarty_tpl->getVariable('s')->value['bigcategory'];?>
</a></li>
        <?php }?>
        <?php }} ?>
      </ul>
</li>
<li><div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/language.png" width="30" height="15"/></div>
<div class="type">
      <ul>
        <?php if ($_smarty_tpl->getVariable('action')->value=='a'){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=a" title="粤语"><font color="#ff0066">粤语</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=a" title="粤语">粤语</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='b'){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=b" title="国语"><font color="#ff0066">国语</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=b" title="国语">国语</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='c'){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=c" title="英语"><font color="#ff0066">英语</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=c" title="英语">英语</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='d'){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=d" title="日语"><font color="#ff0066">日语</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=d" title="日语">日语</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='e'){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=e" title="韩语"><font color="#ff0066">韩语</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=e" title="韩语">韩语</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='f'){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=f" title="其他"><font color="#ff0066">其他</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=f" title="其他">其他</a></li>
        <?php }?>
      </ul>
    </div>
</li>
<li><div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/category.png" width="30" height="15"/></div>
<div class="type">
      <ul>
        <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('category')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
        <?php if ($_smarty_tpl->getVariable('s')->value['smallcategory']==$_smarty_tpl->getVariable('action')->value){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=<?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
"><font color="#e7999a"><?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=<?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
"><?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
</a></li>
        <?php }?>
        <?php }} ?>
      </ul>
    </div>
</li>
<li><div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/year.png" width="30" height="15"/></div>
<div class="type">
      <ul>
        <?php  $_smarty_tpl->tpl_vars['t'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('time')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['t']->key => $_smarty_tpl->tpl_vars['t']->value){
?>
        <?php if ($_smarty_tpl->getVariable('t')->value=='00-05'){?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='2005'){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=2005" title="<?php echo $_smarty_tpl->getVariable('t')->value;?>
年"><font color="#e7999a"><?php echo $_smarty_tpl->getVariable('t')->value;?>
</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=2005" title="<?php echo $_smarty_tpl->getVariable('t')->value;?>
年"><?php echo $_smarty_tpl->getVariable('t')->value;?>
</a></li>
        <?php }?>
        <?php }else{ ?>
        <?php if ($_smarty_tpl->getVariable('action')->value==$_smarty_tpl->getVariable('t')->value){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=<?php echo $_smarty_tpl->getVariable('t')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('t')->value;?>
年"><font color="#e7999a"><?php echo $_smarty_tpl->getVariable('t')->value;?>
</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=<?php echo $_smarty_tpl->getVariable('t')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('t')->value;?>
年"><?php echo $_smarty_tpl->getVariable('t')->value;?>
</a></li>
        <?php }?>
        <?php }?>
        <?php }} ?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='90'){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=90" title="90年代"><font color="#e7999a">90年代</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=90" title="90年代">90年代</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='80'){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=80" title="80年代"><font color="#e7999a">80年代</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=80" title="80年代">80年代</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='70'){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=70" title="70年代"><font color="#e7999a">70年代</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=70" title="70年代">70年代</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='60'){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=60" title="60年代"><font color="#e7999a">60年代</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=year&year=60" title="60年代">60年代</a></li>
        <?php }?>
      </ul>
    </div>
</li>
<li><div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/letter.png" width="30" height="15"/></div>
<div class="type">
      <ul>
        <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('a_z')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
        <?php if ($_smarty_tpl->getVariable('action')->value==$_smarty_tpl->getVariable('a')->value){?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=all&letter=<?php echo $_smarty_tpl->getVariable('a')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value;?>
"><font color="#e7999a"><?php echo $_smarty_tpl->getVariable('a')->value;?>
</font></a></li>
        <?php }else{ ?>
        <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=all&letter=<?php echo $_smarty_tpl->getVariable('a')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value;?>
"><?php echo $_smarty_tpl->getVariable('a')->value;?>
</a></li>
        <?php }?>
        <?php }} ?>
      </ul>
    </div></li>
</ul>
</div>
<!--专辑列表-->
<div class="new">
 <div class="special">
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=all" title="查看全部专辑"><?php if ($_smarty_tpl->getVariable('action')->value=='all'){?><font color="#e7999a">全部</font><?php }else{ ?>全部<?php }?></a> <span>|</span> 
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=time" title="按发行时间排序"><?php if ($_smarty_tpl->getVariable('action')->value=='time'){?><font color="#e7999a">发行时间</font><?php }else{ ?>发行时间<?php }?></a> <span>|</span> 
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=hot" title="按专辑热度排序"><?php if ($_smarty_tpl->getVariable('action')->value=='hot'){?><font color="#e7999a">专辑热度</font><?php }else{ ?>专辑热度<?php }?></a></div>
<ul>
      <?php if ($_smarty_tpl->getVariable('album')->value!='0'){?><?php if ($_smarty_tpl->getVariable('no_album')->value=='0'&&$_smarty_tpl->getVariable('type')->value=='search'){?>
      <li>查无结果...</li>
      <?php }?>
      <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('album')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?><li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
album.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['artist']);?>
-<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
" border="0" width="190" height="190" alt="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['artist']);?>
-<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
"/></a><br/><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
album.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
-<?php echo smarty_modifier_stripslashes($_smarty_tpl->getVariable('s')->value['title']);?>
" id="artist"><?php echo smarty_modifier_trim(smarty_modifier_truncate($_smarty_tpl->getVariable('s')->value['artist'],16));?>
</a><br/><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
album.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
-<?php echo smarty_modifier_stripslashes($_smarty_tpl->getVariable('s')->value['title']);?>
" id="title"><?php echo smarty_modifier_stripslashes(smarty_modifier_trim(smarty_modifier_truncate($_smarty_tpl->getVariable('s')->value['title'],16)));?>
</a></li><?php }} ?>
      <?php }else{ ?>
      <li>暂无记录...</li>
      <?php }?>
    </ul>
    <?php if ($_smarty_tpl->getVariable('page')->value!=''){?> 
    <!--分页--> 
    <div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div>
    <?php }?>
</div>
<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
