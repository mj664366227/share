<?php /* Smarty version Smarty3-b8, created on 2016-09-20 21:24:49
         compiled from "D:\nginx/www/share.git/somethingmusic/templates/template/deafault\musician.html" */ ?>
<?php /*%%SmartyHeaderCode:3144857e138a11c5610-89798608%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '96ea09fbd5e82fa115e4f2d2bac7fd58af607a0c' => 
    array (
      0 => 'D:\\nginx/www/share.git/somethingmusic/templates/template/deafault\\musician.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '3144857e138a11c5610-89798608',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_truncate')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.truncate.php';
?><!--左边-->
<!--[if IE 6]><style>.right{ margin-left:-5px}</style><![endif]-->
<div class="left"> 
  <!--音乐人开始-->
  <div class="musician">
    <div class="title_div" style="width:575px;"><span class="title">音乐人</span></div>
    <ul>
      <?php if ($_smarty_tpl->getVariable('musician')->value!='0'){?>
      <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('musician')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
      <li>
        <div class="pic"> <a href="musician.php?skin=deafault&action=look&id=<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w184h184/admin/<?php echo $_smarty_tpl->getVariable('a')->value['pic'];?>
" border="0" width="135" height="135" alt="<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
"/></a> </div>
        <div class="singer_info"> <span class="h2"><a href="musician.php?skin=deafault&action=look&id=<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
" title="查看<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
的详细介绍"><?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
</a></span>
          <p><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('a')->value['introduce'],160);?>
</p>
        </div>
      </li>
      <?php }} ?>
      
      <?php }else{ ?>
      <li style="font-size:16px">暂无记录...</li>
      <?php }?>
    </ul>
    <?php if ($_smarty_tpl->getVariable('page')->value!=''){?> 
    <!--分页-->
    <div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div>
    <?php }?> </div>
  <!--音乐人结束--> 
</div>
<!--右边-->
<div class="right"> 
  <!--分类查找-->
  <div class="sort">
    <div class="title_div"><span class="title">分类查找</span></div>
    <span class="h2">按地区</span>
    <div class="type">
      <ul>
      <?php if ($_smarty_tpl->getVariable('action')->value=='all'){?>
      <li><a href="musician.php?skin=deafault&action=all" title="全部"><font color="#ff0066">全部</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all" title="全部">全部</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='1'){?>
      <li><a href="musician.php?skin=deafault&action=all&area=1" title="香港"><font color="#ff0066">香港</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all&area=1" title="香港">香港</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='2'){?>
      <li><a href="musician.php?skin=deafault&action=all&area=2" title="台湾"><font color="#ff0066">台湾</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all&area=2" title="台湾">台湾</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='3'){?>
      <li><a href="musician.php?skin=deafault&action=all&area=3" title="日本"><font color="#ff0066">日本</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all&area=3" title="日本">日本</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='4'){?>
      <li><a href="musician.php?skin=deafault&action=all&area=4" title="韩国"><font color="#ff0066">韩国</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all&area=4" title="韩国">韩国</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='5'){?>
      <li><a href="musician.php?skin=deafault&action=all&area=5" title="欧美"><font color="#ff0066">欧美</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all&area=5" title="欧美">欧美</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='6'){?>
      <li><a href="musician.php?skin=deafault&action=all&area=6" title="大陆"><font color="#ff0066">大陆</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all&area=6" title="大陆">大陆</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='8'){?>
      <li><a href="musician.php?skin=deafault&action=all&area=8" title="菲律宾"><font color="#ff0066">菲律宾</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all&area=8" title="菲律宾">菲律宾</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='9'){?>
      <li><a href="musician.php?skin=deafault&action=all&area=9" title="马来西亚"><font color="#ff0066">马来西亚</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all&area=9" title="马来西亚">马来西亚</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='10'){?>
      <li><a href="musician.php?skin=deafault&action=all&area=10" title="新加坡"><font color="#ff0066">新加坡</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all&area=10" title="新加坡">新加坡</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='11'){?>
      <li><a href="musician.php?skin=deafault&action=all&area=11" title="其他"><font color="#ff0066">其他</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all&area=11" title="其他">其他</a></li>
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
      <li><a href="musician.php?skin=deafault&action=all&letter=<?php echo $_smarty_tpl->getVariable('a')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value;?>
"><font color="#ff0066"><?php echo $_smarty_tpl->getVariable('a')->value;?>
</font></a></li>
      <?php }else{ ?>
      <li><a href="musician.php?skin=deafault&action=all&letter=<?php echo $_smarty_tpl->getVariable('a')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value;?>
"><?php echo $_smarty_tpl->getVariable('a')->value;?>
</a></li>
      <?php }?>
      <?php }} ?>
      </ul></div>
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
thumbs/?thumb=admin/<?php echo $_smarty_tpl->getVariable('a')->value['picture'];?>
&w=320&h=150&q=85" border="0" width="320" height="150" alt="<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
"/></a></li>
          <?php }?>
          <?php }} ?>
        </ul>
      </div>
    </div>
  </div>
  <!--广告结束--> 
</div>
<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
