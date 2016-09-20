<?php 
include "head.php";
define(RIGHT,'true');
include "right.php";
$action=$_GET['action'];
switch($template)
{
	case 'deafault':$contents=2;break;
	case 'gray':$contents=4;break;
}
//获取广告
$advertisement=get_my_array($conn,'select * from `somethingmusic_ad` where `show`=1 order by `id` desc');
for($i=0;$i<count($advertisement);$i++)
{
	$advertisement[$i]['show_id']=$i+1;
}

if($action=='')
{
	header('Location:'.BASE_URL);
}
elseif($action=='all')
{
	//获取单篇文章
	$content=showpage2($conn,'somethingmusic_music_introduce','id,title,pic,time,content',$contents,'action=all');
	$size=count($content['page_arr']);
	for($i=0;$i<$size;$i++)
	{
		$content['page_arr'][$i]['content']=str_replace('　','',str_replace('nbsp;','',clean1($content['page_arr'][$i]['content'])));
	}
	$smarty->assign('content',$content['page_arr']);
	$smarty->assign('page',$content['page_infor']);
	$smarty->assign('ad',$advertisement);
	
	//获取专题
	$topic=get_my_array($conn,'select id,name from `somethingmusic_topic` order by `id` desc limit 0,5');
	$smarty->assign('topic',$topic);
	$smarty->display('introduce.html');
}
elseif($action=='list')
{
	$content=showpage2($conn,'somethingmusic_music_introduce','*',5,'action=list');
	for($i=0;$i<count($content['page_arr']);$i++)
	{
		$content['page_arr'][$i]['content']=str_replace('　','',str_replace('nbsp;','',clean1($content['page_arr'][$i]['content'])));
	}
	$smarty->assign('content',$content['page_arr']);
	$smarty->assign('page',$content['page_infor']);
	$smarty->assign('type','content');
	$smarty->display('introduce_more.html');
}
elseif($action=='more_topic')
{
	$content=showpage2($conn,'somethingmusic_topic','*',5,'action=more_topic');
	for($i=0;$i<count($content['page_arr']);$i++)
	{
		$content['page_arr'][$i]['introduce']=str_replace('　','',str_replace('nbsp;','',clean($content['page_arr'][$i]['introduce'])));
	}
	$smarty->assign('content',$content['page_arr']);
	$smarty->assign('page',$content['page_infor']);
	$smarty->assign('type','topic');
	$smarty->display('introduce_more.html');
}

elseif($action=='content_list')
{
	$id=$_GET['id'];
	$title=get_my_array($conn,"select * from `somethingmusic_topic` where `id`='$id'");
	$smarty->assign('title',$title);
	
	$content=get_my_array($conn,"select * from `somethingmusic_topics` where `id`='$id'");
	$smarty->assign('content',$content);
	$smarty->display('content_list.html');
}

elseif($action=='topic_content')
{
	$id=$_GET['id'];
	$topic=$_GET['topic'];
	/*获取文章*/
	$content=get_my_array($conn,"select * from `somethingmusic_topics` where `topic_id`='$id' limit 1");
	$smarty->assign('content',$content);
	
	/*统计点击*/
	$conn->Execute('update `somethingmusic_topics` set click=click+1 where `topic_id`='.$id);
	
	/*上一篇*/
	$prev=get_my_array($conn,"select topic_id,title from `somethingmusic_topics` where `topic_id`>'$id' and `id`='$topic' order by `topic_id` asc limit 1");
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
	/*下一篇*/
	$next=get_my_array($conn,"select topic_id,title from `somethingmusic_topics` where `topic_id`<'$id' and `id`='$topic' order by `topic_id` desc limit 1");
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

	/*获取评论*/
	$comment=get_my_array($conn,"select * from `somethingmusic_topic_comment` where `content_id`='$id' order by `id` desc");
	if(count($comment)<=0)
	{
		$smarty->assign('none','1');
	}
	$smarty->assign('comment',$comment);
	
	$smarty->assign('type','topic');
	$smarty->display('content.html');
}
$conn->close();
?>