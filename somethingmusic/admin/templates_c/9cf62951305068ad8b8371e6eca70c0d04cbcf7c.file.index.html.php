<?php /* Smarty version Smarty3-b8, created on 2013-09-09 18:45:45
         compiled from "E:\htdocs\music/admin/templates/template/index.html" */ ?>
<?php /*%%SmartyHeaderCode:7113522da6d9409062-04496269%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '9cf62951305068ad8b8371e6eca70c0d04cbcf7c' => 
    array (
      0 => 'E:\\htdocs\\music/admin/templates/template/index.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '7113522da6d9409062-04496269',
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
<script type="text/javascript" src="templates/template/jquery-1.4a2.js"></script>
<title>somethingmusic音乐电台后台管理</title>
</head>
<body class="index_bg"><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<h1 align="center">somethingmusic音乐电台后台管理</h1>
<div class="index">
<center>登&nbsp;&nbsp;&nbsp;&nbsp;录</center><br>
<form action="login.php" class="index_form" method="post">
用户名：<input type="text" class="input" name="username" id="username"/><br><br>
密&nbsp;&nbsp;码：<input type="password" class="input" name="pwd" id="pwd"/><br><br>
验证码：<input type="text" class="input" name="yanzhengma" style="width:50px" id="yanzhengma"/> <script language="javascript">document.write("<img src='../yanzhengma.php?'+Math.random()+'' onclick='this.src=this.src+Math.random();' title='看不清？点击更换' align='absmiddle' style='cursor:pointer'>");</script><br><br>
&nbsp;<input type="submit" class="login" value="登录"/>
</form>
</div>
</body>
</html>
<script type="text/javascript">
$(function(){
	$('.login').click(function(){
		if($('#username').val()=='')
		{
			alert('用户名为空！');
			return false;
		}	
		if($('#pwd').val()=='')
		{
			alert('密码为空！');
			return false;
		}	
		if($('#yanzhengma').val()=='')
		{
			alert('验证码为空！');
			return false;
		}
		if($('#yanzhengma').val().length!=4)
		{
			alert('验证码长度不对！');
			return false;
		}		
	})
	
})
</script>