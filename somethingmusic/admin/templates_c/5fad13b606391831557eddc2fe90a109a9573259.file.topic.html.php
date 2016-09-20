<?php /* Smarty version Smarty3-b8, created on 2012-09-06 17:16:34
         compiled from "F:\htdocs\smu/admin/templates/template/topic.html" */ ?>
<?php /*%%SmartyHeaderCode:135335048da721c0d13-52693187%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '5fad13b606391831557eddc2fe90a109a9573259' => 
    array (
      0 => 'F:\\htdocs\\smu/admin/templates/template/topic.html',
      1 => 1343982345,
    ),
  ),
  'nocache_hash' => '135335048da721c0d13-52693187',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="templates/template/css.css" type="text/css" />
<script type="text/javascript" src="../kindeditor/kindeditor.js"></script>
<title>音乐专题制作</title>
</head>
<script type="text/javascript">
KE.show({
				id : 'topic_introduce',
				items : [
		'source',  'undo', 'redo', 'cut', 'copy', 'paste',
		'plainpaste', 'wordpaste','justifyleft', 'justifycenter', 'justifyright',
		'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
		'superscript', 
		'title', 'fontname', 'fontsize','textcolor', 'bgcolor', 'bold',
		'italic', 'underline', 'strikethrough', 'removeformat','image',
		'flash', 'media'
	],
				 
				afterCreate : function(id) {
					KE.event.ctrl(document, 13, function() {
						KE.util.setData(id);
						document.forms['example'].submit();
					});
					KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {
						KE.util.setData(id);
						document.forms['example'].submit();
					});
				}
			});

</script>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>添加新专题</a></div>
  <table cellpadding="0">
    <form action="topic.php?action=add_topic" method="post">
      <tr>
        <td class="table_left">专题名称：</td>
        <td><input class="text" type="text" name="topic_name"/></td>
      </tr>
      <tr>
        <td class="table_left">专题介绍：</td>
        <td><textarea name="topic_introduce" style="width:100%;height:170px;display:block;"></textarea></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" class="button" value="添   加"/></td>
      </tr>
    </form>
  </table>
</div>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>已有专题</a></div>
  <table cellspacing="0">
    <tr class="bold" align="center">
      <td>专题编号</td>
      <td width="50%">专题名称</td>
      <td>发布时间</td>
      <td>操作</td>
    </tr>
    <?php unset($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'] = is_array($_loop=$_smarty_tpl->getVariable('topic')->value) ? count($_loop) : max(0, (int)$_loop); unset($_loop);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['name'] = 'sec';
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['show'] = true;
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['max'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['step'] = 1;
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['start'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['step'] > 0 ? 0 : $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop']-1;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['show']) {
    $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['total'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['loop'];
    if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['total'] == 0)
        $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['show'] = false;
} else
    $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['total'] = 0;
if ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['show']):

            for ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['start'], $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration'] = 1;
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration'] <= $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['total'];
                 $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index'] += $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['step'], $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration']++):
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['rownum'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index_prev'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index'] - $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index_next'] = $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['index'] + $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['step'];
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['first']      = ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration'] == 1);
$_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['last']       = ($_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['iteration'] == $_smarty_tpl->tpl_vars['smarty']->value['section']['sec']['total']);
?>
    <tr align="center" class="comment">
      <td><?php echo $_smarty_tpl->getVariable('topic')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
</td>
      <td><a href="topic.php?action=look&id=<?php echo $_smarty_tpl->getVariable('topic')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
"><?php echo $_smarty_tpl->getVariable('topic')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['name'];?>
</a></td>
      <td><?php echo $_smarty_tpl->getVariable('topic')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['time'];?>
</td>
      <td><a href="topic.php?action=look&id=<?php echo $_smarty_tpl->getVariable('topic')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
">查看</a> <a href="topic.php?action=delete&id=<?php echo $_smarty_tpl->getVariable('topic')->value[$_smarty_tpl->getVariable('smarty')->value['section']['sec']['index']]['id'];?>
" onclick="return confirm('你真的要删除本专题及其所有文章吗？')">删除</a></td>
    </tr>
    <?php endfor; endif; ?>
    <?php if ($_smarty_tpl->getVariable('str')->value!=''){?>
    <tr>
      <td colspan="6" class="showpage" align="center"><?php echo $_smarty_tpl->getVariable('str')->value;?>
</td>
    </tr>
    <?php }?>
  </table>
</div>
</body>
</html>
