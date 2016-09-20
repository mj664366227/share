<?php /* Smarty version Smarty3-b8, created on 2016-09-20 21:24:49
         compiled from "D:\nginx/www/share.git/somethingmusic/templates/template/deafault\resources.html" */ ?>
<?php /*%%SmartyHeaderCode:1228257e138a1efd028-43563020%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'dc1657975a1aa34e946ff104fb871f3d351e4c00' => 
    array (
      0 => 'D:\\nginx/www/share.git/somethingmusic/templates/template/deafault\\resources.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '1228257e138a1efd028-43563020',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.trim.php';
?><!--资源列表-->

<div class="left">
  <div class="resources">
    <div class="title_div"><span class="title"><?php if ($_smarty_tpl->getVariable('type')->value=='search'){?>搜索结果<?php }else{ ?>资源列表<?php }?></span>
      <div class="special">
<a href="resources.php?action=all" title="查看全部专辑"><?php if ($_smarty_tpl->getVariable('action')->value=='all'){?><font color="#ff0066">全部</font><?php }else{ ?>全部<?php }?></a> | 
<a href="resources.php?action=time" title="按发行时间排序"><?php if ($_smarty_tpl->getVariable('action')->value=='time'){?><font color="#ff0066">发行时间</font><?php }else{ ?>发行时间<?php }?></a> | 
<a href="resources.php?action=hot" title="按专辑热度排序"><?php if ($_smarty_tpl->getVariable('action')->value=='hot'){?><font color="#ff0066">专辑热度</font><?php }else{ ?>专辑热度<?php }?></a></div>
    </div>
    <ul>
      <?php if ($_smarty_tpl->getVariable('album')->value!='0'){?><?php if ($_smarty_tpl->getVariable('no_album')->value=='0'&&$_smarty_tpl->getVariable('type')->value=='search'){?>
      <li>查无结果...</li>
      <?php }?>
      <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('album')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?><li><a href="album.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['artist']);?>
-<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
" border="0" width="135" height="135" alt="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['artist']);?>
-<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
"/></a></li><?php }} ?>
      <?php }else{ ?>
      <li>暂无记录...</li>
      <?php }?>
    </ul>
    <?php if ($_smarty_tpl->getVariable('page')->value!=''){?> 
    <!--分页--> 
    <!--[if IE 6]><style>.right{ margin-left:0px}</style><![endif]-->
    <div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div>
    <?php }?> </div>
</div>
<div class="right"> 
  <!--分类查找-->
  <div class="sort">
    <div class="title_div"><span class="title">分类查找</span></div>
    <span class="h2">按地区</span>
    <div class="type">
      <ul>
        <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('area')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
        <?php if ($_smarty_tpl->getVariable('action')->value==$_smarty_tpl->getVariable('s')->value['id']){?>
        <li><a href="resources.php?skin=deafault&action=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['bigcategory'];?>
"><font color="#ff0066"><?php echo $_smarty_tpl->getVariable('s')->value['bigcategory'];?>
</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['bigcategory'];?>
"><?php echo $_smarty_tpl->getVariable('s')->value['bigcategory'];?>
</a></li>
        <?php }?>
        <?php }} ?>
      </ul>
    </div>
    <span class="h2">按语种</span>
    <div class="type">
      <ul>
        <?php if ($_smarty_tpl->getVariable('action')->value=='a'){?>
        <li><a href="resources.php?skin=deafault&action=a" title="粤语"><font color="#ff0066">粤语</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=a" title="粤语">粤语</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='b'){?>
        <li><a href="resources.php?skin=deafault&action=b" title="国语"><font color="#ff0066">国语</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=b" title="国语">国语</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='c'){?>
        <li><a href="resources.php?skin=deafault&action=c" title="英语"><font color="#ff0066">英语</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=c" title="英语">英语</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='d'){?>
        <li><a href="resources.php?skin=deafault&action=d" title="日语"><font color="#ff0066">日语</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=d" title="日语">日语</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='e'){?>
        <li><a href="resources.php?skin=deafault&action=e" title="韩语"><font color="#ff0066">韩语</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=e" title="韩语">韩语</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='f'){?>
        <li><a href="resources.php?skin=deafault&action=f" title="其他"><font color="#ff0066">其他</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=f" title="其他">其他</a></li>
        <?php }?>
      </ul>
    </div>
    <span class="h2">按风格</span>
    <div class="type">
      <ul>
        <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('category')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
        <?php if ($_smarty_tpl->getVariable('s')->value['smallcategory']==$_smarty_tpl->getVariable('action')->value){?>
        <li><a href="resources.php?skin=deafault&action=<?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
"><font color="#ff0066"><?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=<?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
"><?php echo $_smarty_tpl->getVariable('s')->value['smallcategory'];?>
</a></li>
        <?php }?>
        <?php }} ?>
      </ul>
    </div>
    <span class="h2">按年份</span>
    <div class="type">
      <ul>
        <?php  $_smarty_tpl->tpl_vars['t'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('time')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['t']->key => $_smarty_tpl->tpl_vars['t']->value){
?>
        <?php if ($_smarty_tpl->getVariable('t')->value=='00-05'){?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='2005'){?>
        <li><a href="resources.php?skin=deafault&action=year&year=2005" title="<?php echo $_smarty_tpl->getVariable('t')->value;?>
年"><font color="#ff0066"><?php echo $_smarty_tpl->getVariable('t')->value;?>
</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=year&year=2005" title="<?php echo $_smarty_tpl->getVariable('t')->value;?>
年"><?php echo $_smarty_tpl->getVariable('t')->value;?>
</a></li>
        <?php }?>
        <?php }else{ ?>
        <?php if ($_smarty_tpl->getVariable('action')->value==$_smarty_tpl->getVariable('t')->value){?>
        <li><a href="resources.php?skin=deafault&action=year&year=<?php echo $_smarty_tpl->getVariable('t')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('t')->value;?>
年"><font color="#ff0066"><?php echo $_smarty_tpl->getVariable('t')->value;?>
</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=year&year=<?php echo $_smarty_tpl->getVariable('t')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('t')->value;?>
年"><?php echo $_smarty_tpl->getVariable('t')->value;?>
</a></li>
        <?php }?>
        <?php }?>
        <?php }} ?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='90'){?>
        <li><a href="resources.php?skin=deafault&action=year&year=90" title="90年代"><font color="#ff0066">90年代</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=year&year=90" title="90年代">90年代</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='80'){?>
        <li><a href="resources.php?skin=deafault&action=year&year=80" title="80年代"><font color="#ff0066">80年代</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=year&year=80" title="80年代">80年代</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='70'){?>
        <li><a href="resources.php?skin=deafault&action=year&year=70" title="70年代"><font color="#ff0066">70年代</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=year&year=70" title="70年代">70年代</a></li>
        <?php }?>
        <?php if ($_smarty_tpl->getVariable('action')->value=='60'){?>
        <li><a href="resources.php?skin=deafault&action=year&year=60" title="60年代"><font color="#ff0066">60年代</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=year&year=60" title="60年代">60年代</a></li>
        <?php }?>
      </ul>
    </div>
    <span class="h2">按字母</span>
    <div class="type">
      <ul>
        <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('a_z')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
        <?php if ($_smarty_tpl->getVariable('action')->value==$_smarty_tpl->getVariable('a')->value){?>
        <li><a href="resources.php?skin=deafault&action=all&letter=<?php echo $_smarty_tpl->getVariable('a')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value;?>
"><font color="#ff0066"><?php echo $_smarty_tpl->getVariable('a')->value;?>
</font></a></li>
        <?php }else{ ?>
        <li><a href="resources.php?skin=deafault&action=all&letter=<?php echo $_smarty_tpl->getVariable('a')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value;?>
"><?php echo $_smarty_tpl->getVariable('a')->value;?>
</a></li>
        <?php }?>
        <?php }} ?>
      </ul>
    </div>
  </div>
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
</div>
<?php $_template = new Smarty_Internal_Template('foot.html', $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
