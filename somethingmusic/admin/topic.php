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
	$action=clean($_GET['action']);
	if($action==''&&$login!='')
	{
		$page=showpage($conn,'somethingmusic_topic','*','6','','');
		$smarty->assign("topic",$page['page_arr']);
		$smarty->assign("str",$page['page_infor']);
		$smarty->display('topic.html');
	}
	elseif($action=='add_topic'&&$login!='')
	{
		$topic_name=clean($_POST['topic_name']);//专题名
		if($topic_name=='')
		{
			tips('专题名不能为空！');
			exit();
		}
		$topic_introduce=$_POST['topic_introduce'];//专题介绍
		if($topic_introduce=='')
		{
			tips('专题介绍不能为空！');
			exit();
		}
		$time=date('Y-m-d H:i:s');
		$result=$conn->Execute("select * from `somethingmusic_topic` where name='$topic_name'");
		if($result->recordcount()==1)
		{
			tips('专题《'.$topic_name.'》已存在！');
			exit();
		}
		$conn->Execute("insert into `somethingmusic_topic` (`name`,`introduce`,`time`) values ('$topic_name','$topic_introduce','$time')")or die('error!');
		tips('新建专题成功！');
		exit();
	}
	elseif($action=='look'&&$login!='')
	{
		$id=$_REQUEST['id'];
		$result1=$conn->Execute("select * from `somethingmusic_topics` where id='$id'")or die('error!');
		$res1=$result1->GetArray();	
		$smarty->assign("topics",$res1);
		$result2=$conn->Execute("select * from `somethingmusic_topic` where id='$id'")or die('error!');
		$res2=$result2->GetArray();	
		$smarty->assign("topic",$res2);
		$smarty->display('topics.html');
		exit();
	}
	elseif($action=='delete'&&$login!='')
	{
		$id=$_REQUEST['id'];
		$conn->Execute("delete from `somethingmusic_topic` where id='$id'") or die('error');
		$conn->Execute("delete from `somethingmusic_topics` where id='$id'") or die('error');
		tips('专题删除成功！');
		exit();
	}
	elseif($action=='modify_topic_introduce'&&$login!='')
	{
		$id_id=$_POST['id_id'];
		$text=$_POST['text'];
		if($text=='')
		{
			tips('专题介绍不能为空！');
			exit();
		}
		$conn->Execute("update `somethingmusic_topic` set `introduce`='$text' where `id`='$id_id'")or die('error');
		tips('修改成功！');
		exit();
	}
	elseif($action=='add_content'&&$login!='')
	{
		$title=clean($_POST['title']);
		$id=clean($_POST['id']);
		if($title=='')
		{
			tips('请输入文章题目！');
			exit();
		}
		$result=$conn->Execute("select * from `somethingmusic_topics` where title='$title' and `id`='$id'");
		if($result->recordcount()==1)
		{
			tips('文章《'.$title.'》已存在！');
			exit();
		}
		$smarty->assign("id",$id);
		$smarty->assign("title",$title);
		$smarty->display('topic_add_content.html');
	}
	elseif($action=='add'&&$login!='')
	{
		$title=$_POST['title'];
		$id=clean($_POST['id']);
		if($title=='')
		{
			tips('文章题目未填写！');
			exit();
		}
		/*$pic=upload_pic();
		if($pic=='')
		{
			tips('图片未上传！');
			exit();
		}*/
		$time=date("Y-m-d H:i:s");
		$content=$_POST['content'];
		if($content=='')
		{
			tips('文章内容未填写！');
			exit();
		}
		/*$result=$conn->Execute("select * from `somethingmusic_topics` where title='$title' and `id`='$id'");
		if($result->recordcount()==1)
		{
			tips('文章《'.$title.'》已存在！');
		    echo "<script>window.location='topic.php?action=look&id=$id'</script>";
			exit();
		}*/
		$conn->Execute("insert into `somethingmusic_topics` (`id`,`title`,`time`,`content`,`click`) values ('$id','$title','$time','$content','0')")or die('error!');
		tips('文章发表成功！');
		echo "<script>window.location='topic.php?action=look&id=$id'</script>";
	}
	elseif($action=='look_content'&&$login!='')
	{
		$topic_id=clean($_REQUEST['topic_id']);
		$result=$conn->Execute("select * from `somethingmusic_topics` where topic_id='$topic_id'")or die('error!');
		$res=$result->GetArray();
		$smarty->assign("content",$res);
		$smarty->display('look_content.html');
		exit();
	}
	elseif($action=='content_modify'&&$login!='')
	{
		$title=$_POST['title'];
		$id=clean($_POST['id']);
		$id_id=clean($_POST['id_id']);
		if($title=='')
		{
			tips('文章题目未填写！');
			exit();
		}
		$content=$_POST['content'];
		if($content=='')
		{
			tips('文章内容未填写！');
			exit();
		}
		$conn->Execute("UPDATE `somethingmusic_topics` SET `title` = '$title',`content` = '$content' WHERE `topic_id`='$id';
")or die('error!'); 
        tips('文章修改成功！');
		echo "<script>window.location='topic.php?action=look&id=$id_id'</script>";
		exit();
	}
	elseif($action=='delete_content'&&$login!='')
	{
		$id=clean($_REQUEST['topic_id']);
		delete($conn,'somethingmusic_topics','topic_id',$id);
		exit();
	}
}
?>