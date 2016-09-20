<?php /* Smarty version Smarty3-b8, created on 2012-05-14 23:58:02
         compiled from "E:\wwwroot\smu/templates/template/gray\msg.html" */ ?>
<?php /*%%SmartyHeaderCode:684fb12b8a8a5549-37153283%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '3842d1b8b9d41fb8715ace65de0c63c235a19ffa' => 
    array (
      0 => 'E:\\wwwroot\\smu/templates/template/gray\\msg.html',
      1 => 1334932643,
    ),
  ),
  'nocache_hash' => '684fb12b8a8a5549-37153283',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<div class="msg"><a href="<?php echo $_smarty_tpl->getVariable('url')->value;?>
" title="你可以点此链接直接跳转"><?php echo $_smarty_tpl->getVariable('msg')->value;?>
页面在<span><?php echo $_smarty_tpl->getVariable('second')->value;?>
</span>秒后自动跳转！</a></div>
<script>
var start = 0;
function count()
{
	$('.msg span').html(<?php echo $_smarty_tpl->getVariable('second')->value;?>
-start);
	start += 1;
	if(start >= <?php echo $_smarty_tpl->getVariable('second')->value;?>
){
		window.location='<?php echo $_smarty_tpl->getVariable('url')->value;?>
';
	}
	setTimeout('count()',1000);
}
window.onload = count;
</script>
<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
