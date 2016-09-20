<?php
session_start();
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
if($login=="")
{
	header("location:index.php");
}
else
{
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>somethingmusic音乐电台后台管理</title>
</head>
<frameset rows="80,*" border="0">
<frame src="top.php" />
<frameset cols="180,*" border="0">
<frame src="templates/template/left.html" />
<frame src="right.php" name="right" />
</frameset>
</frameset><noframes></noframes>
</html>
<?php }?>