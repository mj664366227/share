<?php /* Smarty version Smarty3-b8, created on 2012-09-06 17:16:07
         compiled from "F:\htdocs\smu/admin/templates/template/doc.html" */ ?>
<?php /*%%SmartyHeaderCode:202075048da5780a4a5-43230839%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '7311d3cee40609581b0e17229f99927e673e6e61' => 
    array (
      0 => 'F:\\htdocs\\smu/admin/templates/template/doc.html',
      1 => 1343982345,
    ),
  ),
  'nocache_hash' => '202075048da5780a4a5-43230839',
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
<title>接口文档</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>接口文档</a></div>
  <table cellspacing="0">
  <tr id="catalog"><td class="bold" colspan="2">
  目录：<br/>
  <ol>
  <li><a href="#libmill">飞车坊认证类</a></li>
  <li><a href="#msg">信息配置</a></li>
  </ol>
  </td></tr>
  <tr class="bold" align="center" id="libmill"><td colspan="2">飞车坊认证类</td></tr>
    <tr><td>登录：</td><td>http://lovemusic.cc/libmill/Libmill.php?action=login<br/><br/>
    						<span class="bold">参数：</span><br/><br/>
                            uc_key UCenter应用的key<br/><br/> 
                            url 此应用的http访问地址<br/><br/> 
                            appid 就是UCenter给你的appid咯<br/><br/> 
                            type 应用的类型，这是成功登录后分配post和get权限的。<br/><br/> 
<pre>
type 可选类型(我们自己做的就选soft或website)

UCHOME => UCenter Home 
XSPACE => X-Space 
DISCUZ => Discuz! 
SUPESITE => SupeSite 
SUPEV => SupeV 
ECSHOP => ECShop 
ECMALL => ECMall 
soft => 软件 <font>get</font>
website => 网站 <font>post</font>
OTHER => 其他 
</pre>
                            <br/><br/> 
                            登录成功后返回你的appid和一个临时的token，这两个参数就用来保证应用与平台之间的连接是唯一的。
                            <br/><br/> 
                            成功调用例子：{ "appid":"4","token":"2b5b43ab27079649932337634340d4e6" }
                            <br/><br/> 
                            错误调用例子：{ "appid":"4","msg":"1000" }
                            </td></tr>
    <tr><td>认证：</td><td>http://lovemusic.cc/libmill/Libmill.php?action=auth<br/><br/>
    						<span class="bold">参数：</span><br/><br/>
                            key&nbsp;&nbsp;登录成功后返回的token，仅当次登录(当前会话)有效。<br/><br/>
                            key = md5(uc_key + url + appid + type + 当前时间戳)
                             <br/><br/> 
                            成功调用例子： 暂时没想好返回什么
                            <br/><br/> 
                            错误调用例子：{ "msg":"1003" }
                            </td></tr>
<tr align="center"><td class="bold" colspan="2"><a href="#catalog">返回目录</a></td></tr>
  </table>
</div>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>信息配置</a></div>
  <table cellspacing="0">
    <tr id="msg"><td><pre><?php echo $_smarty_tpl->getVariable('doc')->value;?>
</pre></td></tr>
    <tr align="center"><td class="bold"><a href="#catalog">返回目录</a></td></tr>
  </table>
</div>
</body>
</html>
<style>

table tr td a {

	color:#09F;

	text-decoration:underline;

	line-height:20px

}

table tr td a:hover {

	color:#F00;

	font-weight:bold;

	text-decoration:none

}

</style>