<?php 
include "head.php";
$action=$_GET['action'];
$area=$_GET['area'];
if($area=='')
{
	$letter=$_GET['letter'];
	$letter_ascii=ord($letter);
	$smarty->assign('action',$letter);
}
if($action=='')
{
	header('Location:'.BASE_URL);
}
elseif($action=='all')
{
//获取广告
$advertisement=get_my_array($conn,'select * from `somethingmusic_ad` where `show`=1 order by `id` desc');
for($i=0;$i<count($advertisement);$i++)
{
	$advertisement[$i]['show_id']=$i+1;
}
$smarty->assign('ad',$advertisement);

//获取音乐人
if($area=='')
{
	$musician=showpage2($conn,'somethingmusic_artist','*',4,'action=all');
	for($i=0;$i<count($musician['page_arr']);$i++)
	{
		$musician['page_arr'][$i]['introduce']=clean($musician['page_arr'][$i][2]);
	}
}

else
{
	$musician=showpage1($conn,'somethingmusic_artist','',4,"`area`='".$area."'",'all&area='.$area);
	for($i=0;$i<count($musician['page_arr']);$i++)
	{
		$musician['page_arr'][$i]['introduce']=clean($musician['page_arr'][$i][2]);
	}
}

if($area>=1)
{
	$smarty->assign('action',$area);
}

elseif($letter_ascii>=65)
{
	$musician=showpage1($conn,"somethingmusic_artist left join somethingmusic_letter on somethingmusic_letter.cBegin and somethingmusic_letter.cEnd and somethingmusic_letter.f_PY='".$letter."'",'*',4,'ASCII(NAME)='.$letter_ascii.' or ORD(CONVERT( NAME USING gbk )) between somethingmusic_letter.cBegin and somethingmusic_letter.cEnd','all&letter='.$letter);
	for($i=0;$i<count($musician['page_arr']);$i++)
	{
		$musician['page_arr'][$i]['introduce']=clean($musician['page_arr'][$i][2]);
	}
}
else
{
	$smarty->assign('action','all');
}

if(count($musician['page_arr'])<=0)
{
	$smarty->assign('musician','0');
}
else
{
	$smarty->assign('musician',$musician['page_arr']);
}
$smarty->assign('a_z',$a_to_z);
$smarty->assign('page',$musician['page_infor']);
$smarty->display('musician.html');
}
elseif($action=='look')
{
	//获取广告
    $advertisement=get_my_array($conn,'select * from `somethingmusic_ad` where `show`=1 order by `id` desc');
    for($i=0;$i<count($advertisement);$i++)
    {
		$advertisement[$i]['show_id']=$i+1;
    }
    $smarty->assign('ad',$advertisement);
	//获取歌手
	$id=$_GET['id'];
	$singer=get_my_array($conn,'select * from `somethingmusic_artist` where `id`='.$id);
    $smarty->assign('singer',$singer);
	
	//获取发行专辑
	$artist=$singer[0][1];
	//$album=get_my_array($conn,"select * from `somethingmusic_album` where `artist`='".$artist."' order by `time` asc");
	$album=showpage2($conn,'somethingmusic_album  where `artist`=\''.$artist.'\'','*',9,'action=look&id='.$id,'id');
	if(count($album['page_arr'])<=0)
	{
		$smarty->assign('no_album','0');
	}
	else
	{
		$smarty->assign('album',$album['page_arr']);
		$smarty->assign('page',$album['page_infor']);
	}
	
	//是否被当前用户喜欢
	$like=get_my_array($conn,'select artist_id from `somethingmusic_favourite_artist` where `artist_id`=\''.$id.'\' and uid=\''.UID.'\'');
	
	//获取相关音乐人
	//$other=get_my_array($conn,"select * from `somethingmusic_artist` order by rand() limit 0,9");
    $smarty->assign('other',$other);
	$smarty->assign('like',$like[0]['artist_id']);
	$smarty->display('musician_look.html');
}
$conn->close();
?>