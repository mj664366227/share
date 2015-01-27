<?php
/**
 * 作品
 */
load_file(sharePHP::get_application_dir().'/controller/user');
class workscontroller extends usercontroller {
	/**
	 * 添加作品
	 */
	public function add(){
		$name = $this->post('name');
		$type = $this->post_uint('type');
		$description = $this->post('description');
		if($name && $type && $description){
			$rs = mworks::add(intval($this->user['_id']), $name, $type, $description);
			if($rs){
				redirect(url('works', 'upload', 'album_id='.$rs['_id']));
			}
		}
		view::assign('category', mworks::get_all());
	}

	/**
	 * 上传照片
	 */
	public function upload(){
		view::assign('album_id', $this->get_uint('album_id'));
		view::assign('uid', $this->user['_id']);
	}

	/**
	 * 展示作品
	 */
	public function show(){
		$uid = $this->get_uint('uid');
		$album_id = $this->get_uint('album_id');
		$server = 'http://127.0.0.1/sharePhoto/server1/?get';
		view::assign('uid', $uid);
		view::assign('album_id', $album_id);
		view::assign('server', $server);
	}

	/**
	 * 查看单张图片
	 */
	public function one(){
		$uid = $this->get_uint('uid');
		$album_id = $this->get_uint('album_id');
		$file = $this->get('file');
		if(!is_numeric($file)){
			return;
		}
		$server = 'http://127.0.0.1/sharePhoto/server1/?one';
		view::assign('uid', $uid);
		view::assign('album_id', $album_id);
		view::assign('server', $server);
		view::assign('file', $file);
		view::assign('is_like', mworks::is_like($this->user['_id'], $uid, $album_id, $file));
		view::assign('like', mworks::get_like($uid, $album_id, $file));
		view::assign('browse', mworks::get_browse($uid, $album_id, $file));
		//view::assign('is_me', $this->user['_id']==$uid);
		$comment = mworks::get_comment($uid, $album_id, $file);
		$comment_user = array();
		foreach($comment as $c){
			$comment_user[] = $c['commentuid'];
		}
		$comment_user = muser::get_user_by_uid($comment_user);
		foreach($comment_user as $u){
			$comment_user[$u['_id']] = $u['nick'];
		}
		foreach($comment as $k => $c){
			$comment[$k]['nick'] = $comment_user[$c['commentuid']];
			unset($comment[$k]['_id']);
		}
		view::assign('comment', $comment);
		mworks::save_browse($this->user['_id'], $uid, $album_id, $file);
	}

	/**
	 * 标记喜欢
	 */
	public function like(){
		$file = $this->post('file');
		if(!is_numeric($file)){
			return;
		}
		$uid = $this->post_uint('uid');
		$album_id = $this->post_uint('album_id');
		mworks::save_like($this->user['_id'], $uid, $album_id, $file);
		echo 1;
	}

	/**
	 * 评论
	 */
	public function comment(){
		$file = $this->post('file');
		if(!is_numeric($file)){
			return;
		}
		$uid = $this->post_uint('uid');
		$album_id = $this->post_uint('album_id');
		$comment = mworks::comment($this->user['_id'], $uid, $album_id, $file, $this->post('comment'));
		$user = muser::get_user_by_uid($uid);
		if($user){
			$comment['nick'] = $user['nick'];
		}
		echo json_encode($comment);
	}
}
?>