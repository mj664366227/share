<?php /* Smarty version Smarty3-b8, created on 2012-09-06 17:16:30
         compiled from "F:\htdocs\smu/admin/templates/template/music_introduce.html" */ ?>
<?php /*%%SmartyHeaderCode:189055048da6e018929-07361681%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'f57df6b63408866379c03004e2d0d22f1f5a2f30' => 
    array (
      0 => 'F:\\htdocs\\smu/admin/templates/template/music_introduce.html',
      1 => 1343982345,
    ),
  ),
  'nocache_hash' => '189055048da6e018929-07361681',
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
<title>发表音乐推荐</title>
</head>
<body>
<script type="text/javascript">
KE.show({
				id : 'content',
				items : [
		'source',  'undo', 'redo', 'cut', 'copy', 'paste',
		'plainpaste', 'wordpaste','justifyleft', 'justifycenter', 'justifyright',
		'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
		'superscript', 
		'title', 'fontname', 'fontsize','textcolor', 'bgcolor', 'bold',
		'italic', 'underline', 'strikethrough', 'removeformat','image',
		'flash', 'media', 'advtable', 'link', 'unlink'
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
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>发表音乐推荐</a></div>
  <form action="music_introduce_post.php" method="post" enctype="multipart/form-data">
    <table cellspacing="0">
      <tr>
        <td width="12%" align="right">文章标题：</td>
        <td><input type="text" name="title" class="text"/></td>
      </tr>
      <tr>
        <td width="12%" align="center">文章缩略图<br>
          (jpeg or png)：<br><font>575*270</font></td>
        <td><input type="file" name="pic" class="text"/></td>
      </tr>
      <tr>
        <td align="right">文章内容：</td>
        <td><textarea name="content" style="width:100%;height:500px;display:block;">请输入文字...</textarea></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="提  交" class="button" /></td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>
