<?php
session_start();
include 'config.php';
set_time_limit(0);
$action=$_GET['action'];
$name=clean($_POST['usernickname']);
$yzm=clean($_POST['yanzhengma']);
$text=$_POST['text'];
$ip=$_SERVER['REMOTE_ADDR'];
$a=$_SESSION['yzm_check'];
$real_yzm=substr($a,-4,strlen($a));
if($name!=''&&$yzm!=''&&$text!='')
{
	$id=$_GET['id'];
	if($action=='say')
	{
		if($yzm!=$real_yzm)
		{
			my_message($conn,$smarty,'验证码错误！','error_yzm',$id);
			die();
		}
		$conn->Execute("insert into `somethingmusic_read_comment` (`content_id`,`name`,`time`,`ip`,`text`) values ('$id','$name',now(),'$ip','$text')")or die('comment insert error!');
		$res=$conn->Execute('select id from `somethingmusic_read_comment` order by `id` desc limit 1');
		$count=$res->GetArray();
		header('Location:'.$system[0]['yuming'].'content.php?id='.$id.'#ok'.$count[0][0]);
		die();
	}
	elseif($action=='topic')
	{
		if($yzm!=$real_yzm)
		{
			my_message($conn,$smarty,'验证码错误！','topic','introduce.php?action=topic_content&id='.$id.'&topic='.$topic);
			die();
		}
		$topic=$_GET['topic'];
		$conn->Execute("insert into `somethingmusic_topic_comment` (`content_id`,`name`,`time`,`ip`,`text`) values ('$id','$name',now(),'$ip','$text')")or die('comment insert error!');
		$res=$conn->Execute('select id from `somethingmusic_topic_comment` order by `id` desc limit 1');
		$count=$res->GetArray();
		header('Location:'.$system[0]['yuming'].'introduce.php?action=topic_content&id='.$id.'&topic='.$topic.'#ok'.$count[0][0]);
		die();
	}
}
session_unregister('yzm_check');
?>