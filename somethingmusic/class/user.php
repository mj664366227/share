<?php
class user extends controller
{
	/**
	 * 登录主界面
	 */
	public function index()
	{
		$look = clean($_GET['look']);
		$type = intval($_GET['type']);
		$type = $type ? $type : 1;
		$action = 'user&mod=index&type='.$type;
		$uid = UID;
		$show = 1;
		$arr = array();
		if($type){
			switch($type){
				case 1:
				$friends = uc_friend_ls(UID,1,999999999999);
				foreach($friends as $ff){
					$arr[] = $ff['friendid'];
				}
				$arr[] = UID;
				break;
				
				case 2:
				$friends = uc_friend_ls(UID,1,999999999999);
				$arr = array();
				foreach($friends as $ff){
					$arr[] = $ff['friendid'];
				}
				break;
				
				case 3:
				$arr[] = UID;
				break;
			}
		}
		if($look==true&&$uid!=intval($_GET['uid'])){
			unset($arr);
			$arr[] = $uid = intval($_GET['uid']);
			$action .= '&look=true&uid='.$uid;
			$show = 0;
		}
		$this->smarty->assign('avatar',uc_get_avatar($uid,'big'));
		$sign=get_my_array($this->db,"select sign,nick from `somethingmusic_user` where `id`='".$uid."'");
		$action=showpage1($this->db,'`somethingmusic_user_log`','uid,time,action,name',5,"`uid` in(".implode(',',$arr).")",$action);
		foreach($action['page_arr'] as $k=>$a){
			$action['page_arr'][$k]['avatar'] = uc_get_avatar($a['uid']);
			$action['page_arr'][$k]['time'] = date('Y-m-d H:i:s',$a['time']);
			$action['page_arr'][$k]['nick'] = $this->get_nick($a['uid']);
		}
		
		$friend_list = uc_friend_ls(UID,1,9);
		foreach($friend_list as $key => $f){
			$friend_list[$key]['avatar'] = uc_get_avatar($f['friendid'],'middle');
			$friend_list[$key]['nick'] = $this->get_nick($f['friendid']);
		}

		$this->smarty->assign('sign',$sign[0]['sign']);
		$this->smarty->assign('nick',$sign[0]['nick']);
		$this->smarty->assign('type',$type);
		$this->smarty->assign('uid',$uid);
		$this->smarty->assign('has_friend',uc_friend_totalnum(UID));
		$this->smarty->assign('other',$show);
		$this->smarty->assign('is_friend',$this->is_friend(UID,$uid));
		$this->smarty->assign('friend_list',$friend_list);
		$this->smarty->assign('friend_list_num',count($friend_list));
		$this->smarty->assign('action',$action['page_arr']);
		$this->smarty->assign('page',$action['page_infor']);
		$this->smarty->display('user.index.html');
	}
	
	/**
	 * 获取某用户的昵称
	 */
	private function get_nick($uid){
		$sign = get_my_array($this->db,"select nick from `somethingmusic_user` where `id`='$uid'");
		return $sign[0]['nick'];
	}
	
	/**
	 * 用户资料设置
	 */	
	public function info(){
		$step = intval($_GET['step']);
		$step = $step ? $step : 1;
		switch($step){
			case 1:
			$sql = "select nick,realname,xi,classname from `somethingmusic_user` where `id`='".UID."'";
			$step1 = get_my_array($this->db, $sql);
			if($_POST['step1']){
				$nick = clean($_POST['nick']);
				$realname = clean($_POST['realname']);
				$xi = clean($_POST['xi']);
				$classname = clean($_POST['classname']);
				$sql = "update `somethingmusic_user` set `nick`='$nick'
				        ,`realname`='$realname',`xi`='$xi',`classname`='$classname' 
						where `id`='".UID."'";
				$this->db->Execute($sql)or die($sql);
				header('location:'.$system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=info&step=2');
			}
			$this->smarty->assign('step1', $step1[0]);
			break;
			case 2:
			$sql = 'select distinct smallcategory from `somethingmusic_smallcategory`';
			$style = get_my_array($this->db, $sql);
			
			$sql = 'select distinct artist from `somethingmusic_album` order by rand() limit 18';
			$artist = get_my_array($this->db, $sql);

			$sql = "select style,artist from `somethingmusic_user` where `id`='".UID."'";
			$like = get_my_array($this->db, $sql);
			
			if($_POST['step2']){
				$style = $_POST['style'];
				$artist = $_POST['artist'];
				$str_style = '';
				$str_artist = '';
				if(is_array($style)){
					foreach($style as $s){
						$str_style .= $s.';';
					}
				}
				if(is_array($artist)){
					foreach($artist as $a){
						$str_artist .= $a.';';
					}
				}
				$str_style = substr($str_style,0,-1);
				$str_artist = substr($str_artist,0,-1);
				$sql = "update `somethingmusic_user` set `style`='$str_style',`artist`='$str_artist' where `id`='".UID."'";
				$this->db->Execute($sql);
				header('location:'.$system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=info&step=3');
			}

			$this->smarty->assign('style_list', explode(';',$like[0]['style']));
			$this->smarty->assign('artist_list', explode(';',$like[0]['artist']));
			$this->smarty->assign('style', $style);
			$this->smarty->assign('artist', $artist);
			break;
			case 3:
			$this->smarty->assign('avatar',uc_avatar($this->uid));
			if($_POST['step3']){
				header('location:'.$system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=index');
			}
			break;
		}
		$this->smarty->assign('step', $step);
		$this->smarty->display('user.info.html');
	}
	
	/**
	 * 好友
	 */
	public function friend(){
		$page = $_GET['page'] ? $_GET['page'] : 1;
		$count = uc_friend_totalnum(UID);
		$perpage = 5;
		$friend_list = uc_friend_ls(UID, $page, $perpage, $count);
		foreach($friend_list as $key => $f){
			$friend_list[$key]['avatar'] = uc_get_avatar($f['friendid'],'middle');
			$sql = "select xi,classname,sign,nick from `somethingmusic_user` where `id`='".$f['friendid']."'";
			$rs = get_my_array($this->db, $sql);
			$friend_list[$key]['nick'] = $rs[0]['nick'];
			$friend_list[$key]['xi'] = $rs[0]['xi'];
			$friend_list[$key]['classname'] = $rs[0]['classname'];
			$friend_list[$key]['sign'] = $rs[0]['sign'];
		}
		
		//列出其他非好友用户
		$this->list_friends();
		
		$this->smarty->assign('page', $this->page($page, $perpage, $count));
		$this->smarty->assign('next', $next);
		$this->smarty->assign('prev', $prev);
		$this->smarty->assign('current_page', $page);
		$this->smarty->assign('friend_list', $friend_list);
		$this->smarty->display('user.friend.html');
	}
	
	/**
	 *列出其他非好友用户
	 */
	private function list_friends(){
		$sql = "select id,nick from `somethingmusic_user` 
				where id not in 
				(select friendid from `uc_friends` where `uid`='".UID."') 
				and id!='".UID."' 
				order by rand() limit 20";
		$friend_introduce = get_my_array($this->db, $sql);
		foreach($friend_introduce as $key => $f){
			$friend_introduce[$key]['avatar'] = uc_get_avatar($f['id'],'middle');
		}
		$this->smarty->assign('friend_introduce', $friend_introduce);
	}
	
	/**
	 * 加好友
	 */
	public function add_friend(){
		$uid = intval($_GET['uid']);
		if($uid){
			$rs = uc_friend_add(UID, $uid);
			if($rs){
				global $log;
				$this->db->Execute("insert into `somethingmusic_user_log` (`uid`,`name`,`time`,`action`) values ('".UID."','".$this->username."','".$_SERVER['REQUEST_TIME']."','".sprintf($log['add_friend'], 'api.php?skin=gray&action=user&mod=index&look=true&uid='.$uid,$this->get_nick($uid))."')");
			}
		}
		header('location:'.$system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod='.clean($_GET['from']));
	}
	
	/**
	 * 删除好友
	 */
	public function delete_friend(){
		$page = $_GET['page'];
		$uid = $_GET['uid'];
		if(!$page || !$uid){
			header('location:'.$system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=friend');
		}
		uc_friend_delete(UID, $uid);
		header('location:'.$system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=friend&page='.$page);
	}
	
	/**
	 * 音乐推荐页面
	 */
	public function album(){
		$sql = "select distinct smallcategory from `somethingmusic_smallcategory` order by `id` asc";
		$rs = get_my_array($this->db, $sql);
		$this->smarty->assign('rs', $rs);
		$this->smarty->display('user.album.html');
	}
	
	/**
	 * 提交音乐推荐
	 */
	public function album_submit(){
		$name = trim(clean1($_POST['name']));            //专辑名
		$category = trim(clean1($_POST['category']));    //类型
		$text = clean($_POST['text']);                   //专辑简介          
		$song_url = $_POST['song_url'];    		         //歌曲地址
		$song_title = $_POST['song_title'];	             //歌曲名字
		$artist = clean1($_POST['artist']);              //歌手名称
		if(!$name || !$category || !$text || !$song_url || !$song_title || !$artist){
			header('location:'.$system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=album_list');
		}
		$pic = upload_pic();                            //专辑封面 
		$sql = "insert into `somethingmusic_user_album` (`name`,`category`,`text`,`pic`,`uid`,`artist`,`time`) 
				values ('$name','$category','$text','$pic','".$this->uid."','$artist','".$_SERVER['REQUEST_TIME']."')";
		$this->db->Execute($sql)or die('user add error  '.$sql);
		$album_id=$this->db->Insert_ID();
		$sql = 'insert into `somethingmusic_user_album_song` (`album_id`,`url`,`name`) values ';
		$i = 0;
		foreach($song_url as $url){
			$sql .= "('$album_id','$url','".$song_title[$i]."'),";
			$i += 1;
		}
		$this->db->Execute(substr($sql,0,-1));
		$w=200;$h=200;$q=75;
		$path = realpath(dirname(__FILE__).'../..').'/';
		$image=$path.$pic;
		include 'thumbs/phpThumb/phpthumb.class.php'; 
		$phpThumb=new phpThumb();
		$phpThumb->setSourceFilename($image);   
		$phpThumb->setParameter('w',$w);   
		$phpThumb->setParameter('h',$h);  
		$phpThumb->setParameter('q',$q); 
		$cache=$path.'thumbs/thumbs/cache/w'.$w.'h'.$h.'/'.$pic;
		if($phpThumb->GenerateThumbnail()) 
		{
			@mkdir(dirname($cache),0777,true);   
			if($phpThumb->RenderToFile($cache))
			{
				unset($phpThumb);
				global $log;
				$this->db->Execute("insert into `somethingmusic_user_log` (`uid`,`name`,`time`,`action`) values ('".UID."','".$this->username."','".$_SERVER['REQUEST_TIME']."','".sprintf($log['add_album'], 'api.php?skin=gray&action=user&mod=album_info&album_id='.$album_id,$name)."')");
				$_SESSION['libmill_msg'] = '专辑添加成功！';
				$_SESSION['libmill_url'] = $system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=album_list';
				redirect();
			}
		}
	}
	
	/**
	 * 专辑信息
	 */
	public function album_info(){
		$sql = "select somethingmusic_user_album.id,uid,name,category,text,pic,nick,somethingmusic_user_album.time,
				somethingmusic_user_album.artist from somethingmusic_user_album 
				left join somethingmusic_user on 
				somethingmusic_user_album.uid=somethingmusic_user.id
				where somethingmusic_user_album.id='".$_GET['album_id']."'";
		$album = get_my_array($this->db, $sql);
		$sql = "select id,name,url from somethingmusic_user_album_song where album_id='".$_GET['album_id']."' order by id asc";
		$song_list = get_my_array($this->db, $sql);
		$this->smarty->assign('album', $album[0]);
		$this->smarty->assign('list', $song_list);
		if(intval($album[0]['uid'])==intval($_GET['uid'])){
			$sql = "select distinct smallcategory from `somethingmusic_smallcategory` order by `id` asc";
			$rs = get_my_array($this->db, $sql);
			$this->smarty->assign('rs', $rs);
			$this->smarty->display('user.album_modify.html');
		} else {
			$this->smarty->display('user.album_info.html');
		}
	}
	
	/**
	 * 专辑列表
	 */
	public function album_list(){
		$rs = showpage1($this->db,'somethingmusic_user_album left join `somethingmusic_user` on `somethingmusic_user_album`.`uid`=`somethingmusic_user`.`id`','`somethingmusic_user_album`.`id`,pic,`somethingmusic_user`.`nick`,uid,name,text,category',4,'1=1','user&mod=album_list');
		$this->smarty->assign('list', $rs['page_arr']);
		$this->smarty->assign('page', $rs['page_infor']);
		$this->smarty->assign('my', UID);
		$this->smarty->assign('cur_page', $_GET['page']?$_GET['page']:1);
		//列出其他非好友用户
		$this->list_friends();
		$this->smarty->display('user.album_list.html');
	}
	
	/**
	 * 修改专辑
	 */
	public function modify(){
		$id = intval(clean($_POST['id']));  
		$name = trim(clean1($_POST['name']));            //专辑名
		$category = trim(clean1($_POST['category']));    //类型
		$text = clean($_POST['text']);                   //专辑简介          
		$song_url = $_POST['song_url'];    		         //歌曲地址
		$song_title = $_POST['song_title'];	             //歌曲名字
		$artist = clean1($_POST['artist']);              //歌手名称
		if(!$name || !$category || !$text || !$song_url || !$song_title || !$artist){
			header('location:'.$system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=album_list');
		}
		if($_FILES['pic']['tmp_name']){
			$pic = upload_pic();   //专辑封面 
		} else {
			$pic = trim($_POST['old_pic']); 
		}		
		$sql = "update `somethingmusic_user_album` set `name`='$name',`category`='$category',`text`='$text',
				`artist`='$artist',`pic`='$pic' where `id`='$id'";
		$this->db->Execute($sql);
		
		$sql = "delete from `somethingmusic_user_album_song` where `album_id`='$id'";
		$this->db->Execute($sql);
		
		$sql = 'insert into `somethingmusic_user_album_song` (`album_id`,`url`,`name`) values ';
		$i = 0;
		foreach($song_url as $url){
			$sql .= "('$id','$url','".$song_title[$i]."'),";
			$i += 1;
		}
		$this->db->Execute(substr($sql,0,-1));
		$w=200;$h=200;$q=75;
		$path = realpath(dirname(__FILE__).'../..').'/';
		$image=$path.$pic;
		include 'thumbs/phpThumb/phpthumb.class.php'; 
		$phpThumb=new phpThumb();
		$phpThumb->setSourceFilename($image);   
		$phpThumb->setParameter('w',$w);   
		$phpThumb->setParameter('h',$h);  
		$phpThumb->setParameter('q',$q); 
		$cache=$path.'thumbs/thumbs/cache/w'.$w.'h'.$h.'/'.$pic;
		if($phpThumb->GenerateThumbnail()) 
		{
			@mkdir(dirname($cache),0777,true);   
			if($phpThumb->RenderToFile($cache))
			{
				$_SESSION['libmill_msg'] = '专辑修改成功！';
				$_SESSION['libmill_url'] = $system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=album_info&album_id='.$id;
				redirect();
			}
		}
	}
	
	/**
	 * 删除专辑
	 */
	public function delete(){
		$id = intval($_GET['id']);
		$page = intval($_GET['page']);
		if($id <= 0 || $page <= 0){
			return;
		}
		$sql = "delete from `somethingmusic_user_album` where `id`='$id'";
		$this->db->Execute($sql);
		$sql = "delete from `somethingmusic_user_album_song` where `album_id`='$id'";
		$this->db->Execute($sql);
		header('location:'.$system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=album_list&page='.$page);
	}
	
	/**
	 * 查询某人是不是我的好友
	 * @param uid 用户id
	 * @param frined_id 要检查是否为我的好友的uid
	 */
	private function is_friend($uid, $friend_id){
		if($uid==$friend_id){
			return true;
		}
		$sql = "select version from `uc_friends` where `uid`='$uid' and `friendid`='$friend_id'";
		$song_list = get_my_array($this->db, $sql);
		if($song_list){
			return true;
		}
		return false;
	}
}
?>