<?php /* Smarty version Smarty3-b8, created on 2015-06-11 08:26:50
         compiled from "E:\www\music/admin/templates/template/top.html" */ ?>
<?php /*%%SmartyHeaderCode:93875579464ae68e12-99045881%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '887cf661f5fce5c249e85fa39aa96ae9f2c7e268' => 
    array (
      0 => 'E:\\www\\music/admin/templates/template/top.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '93875579464ae68e12-99045881',
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
</head>
<body>
<div class="top">
<div class="name">somethingmusic音乐电台后台管理</div>
<div class="content">当前IP：<?php echo $_smarty_tpl->getVariable('ip')->value;?>
。<?php echo $_smarty_tpl->getVariable('username')->value;?>
&nbsp;欢迎回来！现在共有&nbsp;<font><a href="error_info.php" title="立刻处理" target="right"><?php echo $_smarty_tpl->getVariable('question')->value;?>
</a></font>&nbsp;条错误尚未处理，请尽快处理！谢谢！</div>
</div>
</body>
</html>