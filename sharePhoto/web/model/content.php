<?php
/**
 * 文章model
 */
class mcontent extends model{
	/**
	 * 获取所有文章类型
	 */
	public static function get_all(){
		return self::$db->select()
		->order_by(array('_id'=>ASC))
		->get('content_category');
	}

	/**
	 * 获取某个用户的所有文章
	 * @param $uid
	 * @param $page
	 * @param $size
	 */
	public static function content_list($uid, $page, $size = ADMIN_PAGE_SIZE){
		$list = self::$db->select()->where(array('uid'=>intval($uid)))
		->order_by(array('_id' => DESC))
		->limit(($page - 1) * $size, $size)
		->get('content');
		$rs['list'] = $list;
		$rs['count'] = self::$db->count('content');
		return $rs;
	}

	/**
	 * 统计某个用户所有文章总数
	 * @param $uid
	 */
	public static function count($uid){
		$rs = self::content_list($uid, 1);
		return $rs['count'];
	}

	/**
	 * 发表文章
	 * @param $uid
	 * @param $title
	 * @param $category
	 * @param $thumbnails
	 * @param $content
	 * @param $server
	 */
	public static function add($uid, $title, $category, $thumbnails, $content, $server){
		$data['uid'] = intval($uid);
		$data['title'] = $title;
		$data['category'] = intval($category);
		$data['time'] = $_SERVER['REQUEST_TIME'];
		$data['thumbnails'] = $thumbnails;
		$data['content'] = $content;
		$data['server'] = intval($server);
		self::$db->insert('content', $data);
	}

	/**
	 * 获取所有人的文章
	 * @param $category
	 * @param $order
	 * @param $page
	 * @param $size
	 */
	public static function lists($category, $order, $page, $size = ADMIN_PAGE_SIZE){
		$category = intval($category);
		if($category){
			self::$db->where(array('category'=>$category));
		}
		$list = self::$db->select(array('uid','title','time','thumbnails','content','server'))
		->order_by(array('_id' => DESC))
		->limit(($page - 1) * $size, $size)
		->get('content');
		$rs['list'] = $list;

		if($category){
			self::$db->where(array('category'=>$category));
		}
		$rs['count'] = self::$db->count('content');
		return $rs;
	}
}
?>