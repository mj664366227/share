<?php /* Smarty version Smarty3-b8, created on 2012-09-06 17:16:00
         compiled from "F:\htdocs\smu/admin/templates/template/top.html" */ ?>
<?php /*%%SmartyHeaderCode:225585048da5084cbf7-74982261%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'f40cfd2822d97c8743d0e2b2794dc000fa7b9db9' => 
    array (
      0 => 'F:\\htdocs\\smu/admin/templates/template/top.html',
      1 => 1343982345,
    ),
  ),
  'nocache_hash' => '225585048da5084cbf7-74982261',
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