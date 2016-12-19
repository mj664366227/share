<?php
session_start();
header('http-equiv="Content-Type" content="text/html; charset=utf-8');
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
if($login=="")
{
	header("location:index.php");
}
else
{
	$action=$_GET['action'];
	$do=$_GET['do'];
	$search=trim($_GET['search']);
	if($search=='')
	{
		$search=$_POST['search'];
	}
	$perpage=$_REQUEST['perpage'];
	if(!isset($perpage))
	{
		$perpage=11;
	}
	
	if($do=='search')
	{
		$song=showpage1($conn,'somethingmusic_musicplay','',$perpage,"`category`='".$action."' and `artist` like '%".$search."%' or `title` like '%".$search."%' ",$action.'&perpage=11&search='.$search.'&do=search');
		$count=count($song['page_arr']);
		for($i=0;$i<$count;$i++)
		{
			$song['page_arr'][$i]['artist']=str_replace($search,"<font color='#FF0000'>".$search."</font>",$song['page_arr'][$i]['artist']);
			$song['page_arr'][$i]['title']=str_replace($search,"<font color='#FF0000'>".$search."</font>",$song['page_arr'][$i]['title']);
		}
		$smarty->assign('show',0);
	}
	else
	{		
	    //获取歌曲
		$song=showpage1($conn,'somethingmusic_musicplay','',$perpage,"`category`='".$action."'",$action.'&perpage='.$perpage);
		$smarty->assign('show',1);
	}
	
	//获取电台频道
	$radio_channel_set=get_my_array($conn,'select * from `somethingmusic_radio_channel_set` order by `id` asc');
	$smarty->assign('radio_channel_set',$radio_channel_set);
		
	//获取类型
	$category=get_my_array($conn,'select distinct category,count(*) as count from `somethingmusic_musicplay` group by `category` order by `count` desc');
	$smarty->assign('category',$category);

	$smarty->assign('action',$action);
	$smarty->assign('type',$action);
	$smarty->assign('song',$song['page_arr']);
	$smarty->assign('page',$song['page_infor']);
	$smarty->display('channel.html');
}
?>