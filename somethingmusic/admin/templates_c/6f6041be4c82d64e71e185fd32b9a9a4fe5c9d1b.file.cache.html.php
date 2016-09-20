<?php /* Smarty version Smarty3-b8, created on 2013-10-16 20:49:25
         compiled from "E:\htdocs\music/admin/templates/template/cache.html" */ ?>
<?php /*%%SmartyHeaderCode:32619525e8b55ab4c99-77771754%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '6f6041be4c82d64e71e185fd32b9a9a4fe5c9d1b' => 
    array (
      0 => 'E:\\htdocs\\music/admin/templates/template/cache.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '32619525e8b55ab4c99-77771754',
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
<title>缓存管理</title>
</head>
<body>
<div class="main_right">
<div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>缓存管理</a></div>
<table cellpadding="0" width="100%">
<form action="cahce_clear.php" method="post">
<tr><td class="table_left"><input type="checkbox" value="1" name="cahce_clear1" checked /></td><td>清理后台缓存</td></tr>
<tr><td class="table_left"><input type="checkbox" value="2" name="cahce_clear2" checked /></td><td>清理前台缓存</td></tr>
<tr><td class="table_left"></td><td><input type="submit" class="button" value="开始清理"/></td></tr>
</form>
</table>
</div>
</body>
</html>
