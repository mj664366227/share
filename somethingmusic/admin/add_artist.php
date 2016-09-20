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
		$page=showpage($conn,'somethingmusic_artist','*','5','','');//调用分页函数
		for($i=0;$i<count($page['page_arr']);$i++)
		{
			$page['page_arr'][$i]['area']=area($page['page_arr'][$i][4]);
		}
	    $smarty->assign("artist",$page['page_arr']);
	    $smarty->assign("str",$page['page_infor']);
		$smarty->display('add_artist.html');
	}
	elseif($action=='add_artist'&&$login!='')
	{
		$name=$_POST['name'];
		$area=$_POST['area'];
		$pic=upload_pic();
		$content=$_POST['introduce'];
		$result=$conn->Execute("select * from `somethingmusic_artist` where name='$name'");
		if($result->recordcount()==1)
		{
			tips('<font color="#FF0000">'.$name.'</font>已存在！');
			exit();
		}
		$conn->Execute("insert into `somethingmusic_artist` (`name`,`introduce`,`pic`,`area`) values ('$name','$content','$pic','$area')")or die('add artist error!');
		header('Location:http://lovemusic.cc/thumbs/?thumb=admin/'.$pic.'&w=184&h=184&q=30');
		//tips('音乐人添加成功！');
		exit();
	}
	elseif($action=='look'&&$login!='')
	{
		$id=$_GET['id'];
		$result=$conn->Execute("select * from `somethingmusic_artist` where id='$id'");
		$res=$result->GetArray();
		$res[0]['area']=area($res[0]['area']);
		$smarty->assign('artist',$res);
		$smarty->display('artist_look.html');
		exit();
	}
	elseif($action=='modify'&&$login!='')
	{
		$id=$_POST['id'];
		$name=$_POST['name'];
		$area=$_POST['area1'];
		if($area=='')
		{
			$area=$_POST['area'];			
		}
		else
		{
			$area=$_POST['area1'];
		}
		if($area=='')
		{
			tips('地区为空！');
			exit();		
		}
		if($_FILES['pic']['tmp_name']=='')
		{
			$pic=$_POST['pic'];
		}
		else
		{
			$pic=upload_pic();
		}
		if($pic=='')
		{
			tips('图片未上传！');
			exit();
		}		
		$content=$_POST['introduce'];
		$conn->Execute("update `somethingmusic_artist` set `name`='$name',`pic`='$pic',`introduce`='$content',`area`='$area' where `id`='$id'")or die('error!');
		header('Location:http://lovemusic.cc/thumbs/?thumb=admin/'.$pic.'&w=184&h=184&q=30');
		exit();
	}
	elseif($action=='deleted'&&$login!='')
	{
		$id=$_GET['id'];
		delete($conn,'somethingmusic_artist','id',$id);
		exit();
	}
}

function area($string)
{
	switch($string)
	{
		case 1: return '香港';break;
		case 2: return '台湾';break;
		case 3: return '日本';break;
		case 4: return '韩国';break;
		case 5: return '欧美';break;
		case 6: return '大陆';break;
		case 8: return '菲律宾';break;
		case 9: return '马来西亚';break;
		case 10: return '新加坡';break;
		case 11: return '其他';break;
	}
}
?>