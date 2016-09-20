<?php /* Smarty version Smarty3-b8, created on 2016-09-20 21:22:20
         compiled from "D:\nginx/www/share.git/somethingmusic/templates/template/gray\musician.html" */ ?>
<?php /*%%SmartyHeaderCode:2125957e1380c01a833-72912356%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '73efe5bb9759e397e7ceb33985cbfb0ab936b7ef' => 
    array (
      0 => 'D:\\nginx/www/share.git/somethingmusic/templates/template/gray\\musician.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '2125957e1380c01a833-72912356',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_truncate')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.truncate.php';
if (!is_callable('smarty_modifier_trim')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.trim.php';
?><div class="musician_list">
  <ul>
    <?php if ($_smarty_tpl->getVariable('musician')->value!='0'){?>
    <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('musician')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
    <li>
      <div class="pic"> <a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=look&id=<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w184h184/admin/<?php echo $_smarty_tpl->getVariable('a')->value['pic'];?>
" border="0" width="140" height="140" alt="<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
"/></a> </div>
      <div class="singer_info"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=look&id=<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
" title="查看<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
的详细介绍"><?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
</a> <br>
        <p><?php echo smarty_modifier_trim(smarty_modifier_truncate($_smarty_tpl->getVariable('a')->value['introduce'],200));?>
</p>
      </div>
    </li>
    <?php }} ?>
    <?php }else{ ?>
    <li style="font-size:14px">暂无记录...</li>
    <?php }?>
  </ul>
  
  <?php if ($_smarty_tpl->getVariable('page')->value!=''){?> 
  <div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div>
  <?php }?>
</div>
<div class="musician_sort">
  <ul>
    <li>
      <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/area.png" width="30" height="15"/></div>
      <div class="type">
      <ul>
      <?php if ($_smarty_tpl->getVariable('action')->value=='all'){?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all" title="全部"><font color="#e7999a">全部</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all" title="全部">全部</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='1'){?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=1" title="香港"><font color="#e7999a">香港</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=1" title="香港">香港</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='2'){?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=2" title="台湾"><font color="#e7999a">台湾</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=2" title="台湾">台湾</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='3'){?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=3" title="日本"><font color="#e7999a">日本</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=3" title="日本">日本</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='4'){?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=4" title="韩国"><font color="#e7999a">韩国</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=4" title="韩国">韩国</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='5'){?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=5" title="欧美"><font color="#e7999a">欧美</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=5" title="欧美">欧美</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='6'){?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=6" title="大陆"><font color="#e7999a">大陆</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=6" title="大陆">大陆</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='8'){?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=8" title="菲律宾"><font color="#e7999a">菲律宾</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=8" title="菲律宾">菲律宾</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='9'){?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=9" title="马来西亚"><font color="#e7999a">马来西亚</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=9" title="马来西亚">马来西亚</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='10'){?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=10" title="新加坡"><font color="#e7999a">新加坡</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=10" title="新加坡">新加坡</a></li>
      <?php }?>
      <?php if ($_smarty_tpl->getVariable('action')->value=='11'){?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=11" title="其他"><font color="#e7999a">其他</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&area=11" title="其他">其他</a></li>
      <?php }?>
      </ul>
    </div>
    </li>
    <li>
      <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
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
musician.php?skin=gray&action=all&letter=<?php echo $_smarty_tpl->getVariable('a')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value;?>
"><font color="#e7999a"><?php echo $_smarty_tpl->getVariable('a')->value;?>
</font></a></li>
      <?php }else{ ?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all&letter=<?php echo $_smarty_tpl->getVariable('a')->value;?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value;?>
"><?php echo $_smarty_tpl->getVariable('a')->value;?>
</a></li>
      <?php }?>
      <?php }} ?>
      </ul></div>
    </li>
  </ul>
</div>
<?php $_template = new Smarty_Internal_Template("ad.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
