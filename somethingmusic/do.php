<?php 
include 'config.php';
header('http-equiv="Content-Type" content="text/html; charset=utf-8');
$action=$_GET['action'];
if($action=='')
{
	$action=$_POST['action'];
}

if(!isset($action))
{
	die('Get Out!');
}

/*开始*/
switch($action)
{
	/*统计播放次数*/
	case 'update_have_played':
	$id=$_POST['id'];
	if($id > 0)
	{
		$time = date('Y_m_W',$_SERVER['REQUEST_TIME']);
		$sql = 'select song from somethingmusic_data where `song`=\''.$id.'\' and time=\''.$time.'\'';
		$res = get_my_array($conn,$sql);
		if(!$res[0]['song'])
		{
			$sql = 'insert into somethingmusic_data (`song`,`time`,`click`) ';
			$sql.= 'values (\''.$id.'\',\''.$time.'\',\'1\')';
		}
		else
		{
			$sql = 'update somethingmusic_data set click=click+1 where `song`=\''.$id.'\'';
		}
		$conn->Execute($sql);
		sth_update($conn,'have_played',$id);
	}
	break;
	
	/*统计下载次数*/
	case 'update_download':
	sth_update($conn,'download',$id);
	break;
	
	/*音乐电台*/
	case 'radio':
	$channel=clean($_POST['channel']);
	$radio=intval($_POST['radio']);
	$id=intval($_POST['id']);
	if($id)
	{
		$sql="select * from `somethingmusic_musicplay` left join `somethingmusic_album` 
		      on somethingmusic_musicplay.albumid=somethingmusic_album.id 
			  where `somethingmusic_musicplay`.`id`='$id'";
	}
	elseif($channel=='self')//私人电台
	{
		if(UID > 0){
			$sql="select artist,style from `somethingmusic_user` where `id`='".UID."'";
			$rs = get_my_array($conn,$sql);
			if($rs[0]['artist'] || $rs[0]['style']){
			$sql="select * from `somethingmusic_musicplay` left join `somethingmusic_album` 
				  on somethingmusic_musicplay.albumid=somethingmusic_album.id 
				  where (somethingmusic_musicplay.artist 
				  in ('".str_replace(';',"','",$rs[0]['artist'])."') 
				  or somethingmusic_musicplay.category 
				  in ('".str_replace(';',"','",$rs[0]['style'])."')
				  or 1) and somethingmusic_musicplay.id>=
					  (select round(rand() * 
					  (select max(somethingmusic_musicplay.id) from `somethingmusic_musicplay`))) 
					  order by somethingmusic_musicplay.id limit 1";
			} else {
				$sql="select * from `somethingmusic_musicplay` left join `somethingmusic_album` 
				  	  on somethingmusic_musicplay.albumid=somethingmusic_album.id 
					  where somethingmusic_musicplay.id>=
					  (select round(rand() * 
					  (select max(somethingmusic_musicplay.id) from `somethingmusic_musicplay`))) 
					  order by somethingmusic_musicplay.id limit 1";
			}
		}
	}
	elseif($channel!='new')
	{
		$sql="select * from `somethingmusic_musicplay` left join `somethingmusic_album` 
		      on somethingmusic_musicplay.albumid=somethingmusic_album.id 
			  where `".$channel."`='1' order by rand() limit 1";
	}
	else
	{
		$sql="select * from `somethingmusic_musicplay` left join `somethingmusic_album` 
	      on somethingmusic_musicplay.albumid=somethingmusic_album.id 
		  where ".get_recent_3_month()." order by rand() limit 1";
	}
	$this_song=this_radio($conn,$sql);
	setcookie('channel_id',$radio,time()+999999);
	echo $this_song[0][3].'||'.             //歌名 0
		 $this_song[0]['artist'].'||'.      //歌手名 1
		 $this_song[0]['picture'].'||'.     //图片文件路径 2
		 $this_song[0]['url'].'||'.         //歌曲地址 3
		 $this_song[0]['albumid'].'||'.     //专辑id 4 
		 $this_song[0]['album'].'||'.       //专辑名称 5 
		 $this_song[0][0].'||'.             //歌曲id 6
		 $this_song[0]['base_url'] = $system[0]['yuming']; //本站base_url 7
	break;

	/*ajax搜索*/
	case 'ajax_search':
	$text=clean($_POST['text']);
	if($text!='')
	{
		$sql="select distinct artist from `somethingmusic_album` where 
		`artist` like '%$text%'
		order by `id` desc limit 10";
		$result=get_my_array($conn,$sql);
		for($i=0;$i<count($result);$i++)
		{
			echo '<div class="ajax_search_result"><a href="javascript:void(0);">'.str_replace('.','',$result[$i]['artist'])."</a></div>";
		}
	}
	break;
	
	case 'count':
	$channel=$_POST['channel'];
	$conn->Execute("update `somethingmusic_radio_channel_set` set count=count+1 where `en_name`='$channel'")or die('radio channel count update error!');
	$sql="select id from somethingmusic_radio where `radio_id`='$channel' and time='".date('Y-m-d',$_SERVER['REQUEST_TIME'])."'";
	$res=get_my_array($conn,$sql);
	if(!$res)
	{
		$sql="insert into somethingmusic_radio (radio_id,time,count) values 
		      ('$channel','".date('Y-m-d',$_SERVER['REQUEST_TIME'])."','1')";
	}
	else
	{
		$sql="update somethingmusic_radio set count=count+1 where 
		      `radio_id`='$channel'";
	}
	$conn->Execute($sql);
	break;
	
	case 'favourite':
	$album_id=$_POST['album_id'];
	$uid=$_POST['uid'];
	if($album_id&&$uid){
		$conn->Execute("insert into somethingmusic_favourite (album_id,uid) values ('$album_id','$uid')");
	}
	break;
	
	case 'favourite_artist':
	$artist_id=$_POST['artist_id'];
	$uid=$_POST['uid'];
	if($artist_id&&$uid){
		$conn->Execute("insert into somethingmusic_favourite_artist (artist_id,uid) values ('$artist_id','$uid')");
	}
	break;
	
	case 'otherlike':
	//搜索其他喜欢这专辑的用户
	$album_id=$_POST['album_id'];
	$limit=$_POST['limit'];
	if($album_id&&$limit){
		$sql='select uid from `somethingmusic_favourite` where `album_id`=\''.$album_id.'\' order by rand() limit '.$limit;
		$otherlike=get_my_array($conn,$sql);
		foreach($otherlike as $key => $o){
			$otherlike[$key]['avatar'] = uc_get_avatar($o['uid']);
			$otherlike[$key]['nick'] = get_nick($conn,$o['uid']);
			$otherlike[$key]['url'] = $system[0]['yuming'];
		}
	}
	echo json_encode($otherlike);
	break;
	
	case 'otherlike_artist':
	//搜索其他喜欢这歌手的用户
	$artist_id=$_POST['artist_id'];
	$limit=$_POST['limit'];
	if($artist_id&&$limit){
		$sql='select uid from `somethingmusic_favourite_artist` where `artist_id`=\''.$artist_id.'\' order by rand() limit '.$limit;
		$otherlike_artist=get_my_array($conn,$sql);
		foreach($otherlike_artist as $key => $o){
			$otherlike_artist[$key]['avatar'] = uc_get_avatar($o['uid']);
			$otherlike_artist[$key]['nick'] = get_nick($conn,$o['uid']);
			$otherlike_artist[$key]['url'] = $system[0]['yuming'];
		}
	}
	echo json_encode($otherlike_artist);
	break;
	
	case 'maybelike':
	$limit=$_POST['limit'];
	if($limit){
		$maybelike=get_my_array($conn,"select id,title,artist,picture from `somethingmusic_album` order by rand() limit 0,".$limit);
	}
	echo json_encode($maybelike);
	break;
	
	case 'maybelike_artist':
	$maybelike_artist=get_my_array($conn,"select id,name,pic from `somethingmusic_artist` order by rand() limit 0,6");
	echo json_encode($maybelike_artist);
	break;
	
	case 'update_sign':
	$uid=intval($_POST['uid']);
	$sign=strval($_POST['sign']);
	$conn->Execute("update `somethingmusic_user` set `sign`='$sign' where `id`='$uid'");
	//写日志
	$action = $log['log'].$sign;
	$uc_user_data = uc_get_user($uid,true);
	$name = $uc_user_data[1];
	$conn->Execute("insert into `somethingmusic_user_log` (`uid`,`name`,`time`,`action`) values ('$uid','$name','".$_SERVER['REQUEST_TIME']."','$action')");
	break;
	
	case 'next_artist':
	$sql = 'select distinct artist from `somethingmusic_album` order by rand() limit 18';
	$artist = get_my_array($conn, $sql);
	echo json_encode($artist);
	break;
	
	case 'next_friend_introduce':
	$sql = "select id,nick from `somethingmusic_user` 
				where id not in 
				(select friendid from `uc_friends` where `uid`='".UID."')  
				and id!='".UID."' 
				order by rand() limit 20";
	$friend_introduce = get_my_array($conn, $sql);
	foreach($friend_introduce as $key => $f){
		$friend_introduce[$key]['avatar'] = uc_get_avatar($f['id'],'middle');
		$friend_introduce[$key]['url'] = $system[0]['yuming'];
	}
	echo json_encode($friend_introduce);
	break;
}


function this_radio($connection,$sql)
{
	$conn=$connection;
	$res=$conn->Execute($sql) or die('the error sql id:'.$sql);
	return $res->GetArray();
}
function get_nick($conn,$uid){
	$sign = get_my_array($conn,"select nick from `somethingmusic_user` where `id`='$uid'");
	return $sign[0]['nick'];
}
?>