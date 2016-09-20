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
	$pwd1=clean($_POST['userpassword1']);
	$pwd2=clean($_POST['userpassword2']);
	$user=clean($_POST['username']);
	$pwd=md5($pwd1);
		if($pwd1!=$pwd2)
		{
			tips('两次密码输入不一致！');
		}
		else
		{
			if($user==''||$pwd=='d41d8cd98f00b204e9800998ecf8427e')
			{
				tips('用户名或者密码空！');
			}
			else
			{
				$conn->Execute("update `somethingmusic_admin` set `pwd`='$pwd' where `name`='$user'")or die("update database error!");
				tips('用户修改成功！<font>（请谨记新密码！）</font>');
				echo "<script>window.location='user.php'</script>";
			}
		}
}
?>