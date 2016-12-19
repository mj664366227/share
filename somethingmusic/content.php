<?php
include "config.php";
include "right.php";
$id=$_GET['id'];
if(!$id)
{
	die();
}

//获取文章
$content=get_my_array($conn,"select * from `somethingmusic_music_introduce` where `id`='$id'");
$smarty->assign('content',$content);

//统计点击
$conn->Execute('update `somethingmusic_music_introduce` set click=click+1 where `id`='.$id);

//上一篇
$prev=get_my_array($conn,"select id,title from `somethingmusic_music_introduce` where `id`>'$id' order by `id` asc limit 1");
if(count($prev)>0)
{
	$smarty->assign('prev',$prev[0][0]);
	$smarty->assign('prev_title','《'.$prev[0][1].'》');
}
else
{
	$smarty->assign('prev',$id);
	$smarty->assign('prev_title','没有了');
}

//下一篇
$next=get_my_array($conn,"select id,title from `somethingmusic_music_introduce` where `id`<'$id' order by `id` desc limit 1");
if(count($next)>0)
{
	$smarty->assign('next',$next[0][0]);
	$smarty->assign('next_title','《'.$next[0][1].'》');
}
else
{
	$smarty->assign('next',$id);
	$smarty->assign('next_title','没有了');
}

//获取评论
$comment=get_my_array($conn,"select * from `somethingmusic_read_comment` where `content_id`='$id' order by `id` desc");
$num=count($comment);
if($num<=0)
{
	$smarty->assign('none','1');
}
$smarty->assign('comment',$comment);
//获取广告
$advertisement=get_my_array($conn,'select * from `somethingmusic_ad` where `show`=1 order by `id` desc');
for($i=0;$i<count($advertisement);$i++)
{
	$advertisement[$i]['show_id']=$i+1;
}

$smarty->assign('ad',$advertisement);
$smarty->assign('num',$num);
$smarty->display('content.html');
$conn->close();
?>