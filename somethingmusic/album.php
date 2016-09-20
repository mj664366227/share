<?php 
include "config.php";
$songid=$_GET['id'];
if($songid=='')
{
	header('Location:'.BASE_URL);
	exit();
}

/*统计点击*/
$conn->Execute('update `somethingmusic_album` set hit=hit+1 where `id`='.$songid);

$song=get_my_array($conn,"select * from `somethingmusic_album` where `id`='$songid'");
$artist=$song[0][0];//获取歌手id
$songger=$song[0]['artist'];//获取歌手名称
$album_title=$song[0]['title'];//获取专辑名称
$category=$song[0][1];

$songlist=get_my_array($conn,"select artist,album,title,url,pid,id,have_played,download from `somethingmusic_musicplay` where `albumid`='$artist' order by `pid` asc");

for($i=0;$i<count($songlist);$i++)
{
	$songlist[$i]['show_id']=$i+1;
}

$about_artist=get_my_array($conn,"select id,title,artist,picture from `somethingmusic_album` where `artist`='$songger' and `title`!='$album_title' order by rand() limit 0,25");

$album_count=count($about_artist);

if($album_count<=25)
{
	if($album_count==0)
	{
		$about_artist1=get_my_array($conn,"select id,title,artist,picture from `somethingmusic_album` where `category`='$category' and `artist`!='$songger' order by rand() limit 0,".(25-$album_count));
	}
	else
	{
		$about_artist1=get_my_array($conn,"select id,title,artist,picture from `somethingmusic_album` where `category`='$category' and `artist`!='$songger' order by rand() limit 0,".(25-$album_count));
	}
}

if($album_count+count($about_artist1)<=25)
{
	$about_artist2=get_my_array($conn,"select id,title,artist,picture from `somethingmusic_album` where `category`!='$category' and `artist`!='$songger' order by rand() limit 0,".(25-$album_count-count($about_artist1)));
}


//获取广告
$advertisement=get_my_array($conn,'select * from `somethingmusic_ad` where `show`=1 order by `id` desc');
for($i=0;$i<count($advertisement);$i++)
{
	$advertisement[$i]['show_id']=$i+1;
}

//是否被当前用户喜欢
$like=get_my_array($conn,'select album_id from `somethingmusic_favourite` where `album_id`=\''.$song[0]['id'].'\' and uid=\''.UID.'\'');

$smarty->assign('ad',$advertisement);
$smarty->assign('about_artist',$about_artist);
$smarty->assign('about_artist1',$about_artist1);
$smarty->assign('about_artist2',$about_artist2);
$smarty->assign('song',$song);
$smarty->assign('songlist',$songlist);
$smarty->assign('like',$like[0]['album_id']);
$smarty->display('album.html');
$conn->close();
?>