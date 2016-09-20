<?php
include 'head.php';
set_time_limit(0);
$action=$_GET['action'];
if($action=='')
{
	$action=$_POST['action'];
}

if($action=='search')
{
	$text=$_POST['search_input'];
	
	if($text=='')
	{
		$text=clean($_GET['word']);
	}
	
	//$album=showpage2($conn,'somethingmusic_album','*',16,'action=search&word='.$text);
	$album=showpage1($conn,'somethingmusic_album','',16,"`artist` like '".'%'.$text.'%'."'",'search&word='.$text);
	if(count($album['page_arr'])<=0)
	{
		$smarty->assign('no_album','0');
	}
	$smarty->assign('a_z',$a_to_z);
	$smarty->assign('album',$album['page_arr']);
	$smarty->assign('page',$album['page_infor']);
	$smarty->assign('type','search');
	
	//获取广告
	$advertisement=get_my_array($conn,'select * from `somethingmusic_ad` where `show`=1 order by `id` desc');
	for($i=0;$i<count($advertisement);$i++)
	{
		$advertisement[$i]['show_id']=$i+1;
	}
	$smarty->assign('ad',$advertisement);
	
	//获取风格
	$category=get_my_array($conn,'select distinct category as smallcategory from `somethingmusic_album` order by `id` asc');
	$smarty->assign('category',$category);
	
	//获取地区
	$area=get_my_array($conn,'select distinct bigcategory from `somethingmusic_bigcategory` order by `id` asc');
	for($i=0;$i<count($area);$i++)
	{
		$area[$i]['id']=$i+1;
	}
	$smarty->assign('area',$area);
	
	//获取时间
	$time=get_my_array($conn,'select distinct SUBSTRING(TIME,1,4) from `somethingmusic_album` order by `time` desc');
	$smarty->assign('time',get_time($time));
	$smarty->display('resources.html');
}
else
{
	header('Location:'.BASE_URL);
}
?>