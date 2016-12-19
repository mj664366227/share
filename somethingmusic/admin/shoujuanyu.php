<?php
session_start();
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
$action=$_GET['action'];
if($login=="")
{
	header("location:index.php");
}
else
{
	switch($action)
	{
		case 'default':
		$page=showpage2($conn,'somethingmusic_sjy','*','7','action=default');
		$smarty->assign("sjy",$page['page_arr']);
		$smarty->assign("str",$page['page_infor']);
		$smarty->display('sjy.html');
		break;
		
		case 'post':
		$text=$_POST['text'];
		admin();
		$conn->Execute("insert into `somethingmusic_sjy` (`text`) values ('$text')")or die('error sjy');
		tips('首卷语添加成功！');
		break;
		
		case 'modify':
		$id=$_REQUEST['id'];
		$sjy=get_my_array($conn,"select * from `somethingmusic_sjy` where `id`='$id'")or die('error');
		$smarty->assign("sjy",$sjy);
		$smarty->display('sjy_modify.html');
		break;
		
		case 'modifying':
		$id=$_POST['id'];
		$text=$_POST['text'];
		$conn->Execute("update `somethingmusic_sjy` set `text`='$text' where `id`='$id'")or die('modify error');
		tips('首卷语修改成功！');
		break;
		
		case 'delete':
		$id=$_REQUEST['id'];
		delete($conn,'somethingmusic_sjy','id',$id);
		break;
	}
}

function admin()
{
	$admin_i=strpos($_SERVER['SCRIPT_FILENAME'],'shoujuanyu.php');	
	$admin_path=substr($_SERVER['SCRIPT_FILENAME'],0,$admin_i).'templates_c/';
	$admin_files=scandir($admin_path);
	$admin_count=count($admin_files);
	for($i=2;$i<$admin_count;$i++)
	{
		unlink($admin_path.$admin_files[$i])or die('fuck');
		//echo $admin_path.$admin_files[$i].'<br>';
	}
}
?>