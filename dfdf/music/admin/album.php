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
	$action=$_GET['action'];
	if($action=='search')
	{
		$search=$_GET['search'];
		if($search=='')
		{
			$search=$_POST['search'];
		}
		$page=showpage1($conn,'somethingmusic_album','*',10,"`artist` like '%".$search."%' or `title` like '%".$search."%'",'search&search='.$search);
		$count=count($page['page_arr']);
		for($i=0;$i<$count;$i++)
		{
			$page['page_arr'][$i]['artist']=str_replace($search,"<font color='#FF0000'>".$search."</font>",$page['page_arr'][$i]['artist']);
			$page['page_arr'][$i]['title']=str_replace($search,"<font color='#FF0000'>".$search."</font>",$page['page_arr'][$i]['title']);
		}
	}
	else
	{
		$page=showpage($conn,'somethingmusic_album','*','10','','');//调用分页函数
	}
	$smarty->assign("album",$page['page_arr']);
	$smarty->assign("str",$page['page_infor']);
	$smarty->display('album.html');
}
?>