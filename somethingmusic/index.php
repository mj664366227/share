<?php 
include "head.php";
/*热点推荐*/
switch($template)
{
	case 'deafault':
		$num=9;
		$hot_num=11;
		$suggest_num=5;
		$suggest_len=85;
		$suggest=get_my_array($conn,'select title,content,pic,id from `somethingmusic_music_introduce` order by `id` desc limit 0,'.$suggest_num);
		for($i=0;$i<count($suggest);$i++)
		{
			$suggest[$i]['content']=str_replace('　','',str_replace('nbsp;','',mb_substr(clean1($suggest[$i]['content']),0,$suggest_len)));
		}
		$smarty->assign('suggest',$suggest);
	break;
	
	case 'gray':
		$num=$hot_num=10;
		$suggest_num=1;
		$suggest_len=320;
		$slide=get_my_array($conn,'select url,name from somethingmusic_music_slide order by id desc limit '.$system[0]['slide']);
		$smarty->assign('slide',$slide);
		$suggest=get_my_array($conn,'select title,content,pic,id from `somethingmusic_music_introduce` order by `id` desc limit 0,'.$suggest_num);
		for($i=0;$i<count($suggest);$i++)
		{
			$suggest[$i]['content']=str_replace('　','',str_replace('nbsp;','',mb_substr(clean1($suggest[$i]['content']),0,$suggest_len)));
		}
		$smarty->assign('suggest',$suggest);
	break;
}

/*获取专辑*/
$album=get_my_array($conn,'select id,title,artist,picture from `somethingmusic_album` order by `id` desc limit '.$num);
$smarty->assign('album',$album);

/*站内公告*/
$notice=get_my_array($conn,'select * from `somethingmusic_notice`order by `id` asc');
if(count($notice<=0))
{
	$smarty->assign('notice',$notice);
}
else
{
	$smarty->assign('notice',array());
}

/*热门排行*/
$hot=get_my_array($conn,'select * from `somethingmusic_musicplay` left join `somethingmusic_album` on somethingmusic_musicplay.albumid=somethingmusic_album.id order by `have_played` desc limit '.$hot_num);
$smarty->assign('hot',$hot);

//获取广告
$advertisement=get_my_array($conn,'select * from `somethingmusic_ad` where `show`=1 order by `id` desc limit 1');
for($i=0;$i<count($advertisement);$i++)
{
	$advertisement[$i]['show_id']=$i+1;
}
$smarty->assign('ad',$advertisement);

/*访问统计*/
$conn->Execute("update `somethingmusic_system` set count=count+1 where `id`='1'")or die('update count error');

$smarty->display('index.html');
$conn->close();
?>