<?php
/**
 * 作品model
 */
class mworks extends model{
	/**
	 * 添加相册
	 * @param $uid 用户id
	 * @param $name 相册名称
	 * @param $type 相册类型
	 * @param $description 相册描述
	 */
	public static function add($uid, $name, $type, $description){
		return self::$db->insert('album', array('uid'=>$uid,
												'name'=>$name,
												'type'=>$type,
												'description'=>$description));
	}

	/**
	 * 获取所有相册类型
	 */
	public static function get_all(){
		return self::$db->select(array('_id','name'))
				->order_by(array('_id'=>ASC))
				->get('works_category');
	}

	/**
	 * 获取我所有的相册
	 * @param $uid
	 */
	public static function get_my_albums($uid){
		return self::$db->select(array('_id','name','type','description'))
				->where(array('uid'=>intval($uid)))->order_by(array('_id'=>DESC))
				->get('album');
	}

	/**
	 * 统计浏览人数
	 * @param $myuid
	 * @param $uid
	 * @param $album_id
	 * @param $file
	 */
	public static function save_browse($myuid, $uid, $album_id, $file){
		if($myuid == $uid){
			return;
		}
		$id = $myuid.$uid.$album_id.$file;
		$rs = self::$db->select(array('_id'))->where(array('_id'=>$id))->get('browse_map');
		if(!$rs){
			self::$db->insert('browse_map',array('_id'=>$id),false);
			self::$db->update_inc('browse', array('_id'=>$uid.$album_id.$file),'times');
		}
	}

	/**
	 * 读出浏览次数
	 * @param $uid
	 * @param $album_id
	 * @param $file
	 */
	public static function get_browse($uid, $album_id, $file){
		$rs = self::$db->select(array('times'))
			->where(array('_id'=>$uid.$album_id.$file))
			->get('browse');
		return $rs[0]['times'];
	}

	/**
	 * 统计喜欢人数
	 * @param $myuid
	 * @param $uid
	 * @param $album_id
	 * @param $file
	 */
	public static function save_like($myuid, $uid, $album_id, $file){
		if(!self::is_like($myuid, $uid, $album_id, $file)){
			self::$db->insert('like_map',array('_id'=>$myuid.$uid.$album_id.$file),false);
			self::$db->update_inc('like', array('_id'=>$uid.$album_id.$file),'times');
		}
	}

	/**
	 * 是否已经喜欢过了
	 * @param $myuid
	 * @param $uid
	 * @param $album_id
	 * @param $file
	 */
	public static function is_like($myuid, $uid, $album_id, $file){
		if($myuid == $uid){
			return true;
		}
		$id = $myuid.$uid.$album_id.$file;
		return count(self::$db->select(array('_id'))
				->where(array('_id'=>$id))
				->get('like_map')) > 0;
	}

	/**
	 * 读出喜欢次数
	 * @param $uid
	 * @param $album_id
	 * @param $file
	 */
	public static function get_like($uid, $album_id, $file){
		$rs = self::$db->select(array('times'))
			->where(array('_id'=>$uid.$album_id.$file))
			->get('like');
		return $rs[0]['times'];
	}

	/**
	 * 评论
	 * @param $myuid
	 * @param $uid
	 * @param $album_id
	 * @param $file
	 * @param $comment
	 */
	public static function comment($myuid, $uid, $album_id, $file, $comment){
		if($myuid == $uid || !$comment){
			//			return;
		}
		$data['commentuid'] = $myuid;
		$data['uid'] = $uid;
		$data['album_id'] = $album_id;
		$data['file'] = $file;
		$data['comment'] = trim($comment);
		$data['time'] = $_SERVER['REQUEST_TIME'];
		self::$db->insert('works_comment', $data);
		return $data;
	}

	/**
	 * 获取某张图片的评论
	 * @param $uid
	 * @param $album_id
	 * @param $file
	 */
	public static function get_comment($uid, $album_id, $file){
		return self::$db->select(array('commentuid','comment','time'))
				->where(array('uid'=>$uid,'album_id'=>$album_id,'file'=>$file))
				->order_by(array('time' => DESC))
				->get('works_comment');
	}
}