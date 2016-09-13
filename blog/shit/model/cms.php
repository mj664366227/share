<?php
/**
 * 内容model
 */
class mcms extends model {

	/**
	 * 添加文章分类
	 * @param $name 分类类型名
	 */
	public static function add_category($name) {
		$data['name'] = $name;
		$data['contents'] = 0;
		self::$db->insert('category', $data);
	}

	/**
	 * 获取所有分类
	 */
	public static function get_category() {
		$order_by['id'] = DESC;
		return self::$db->select('category')->order_by($order_by)->query();
	}

	/**
	 * 根据分类获取文章
	 * @param $id 分类id
	 * @param $page 当前页码
	 * @param $page_size 页面大小
	 */
	public static function get_content_by_category($id, $page, $page_size) {
		$data['list'] = self::$db->select('content')->where('category_id')->eq($id)->limit(($page - 1) * $page_size, $page_size)->query();
		$data['max'] = ceil(self::$db->get_count() / $page_size);
		return $data;
	}

	/**
	 * 添加文章
	 * @param $category 类型
	 * @param $title 题目
	 * @param $content 内容
	 */
	public static function add_content($category, $title, $content) {
		$data['category_id'] = $category;
		$data['title'] = $title;
		$data['content'] = $content;
		$data['pv'] = 0;
		$data['create_time'] = time();
		$id = intval(self::$db->insert('content', $data));
		if (!$id) {
			return;
		}
		
		$sql = 'update `b_category` set `contents`=`contents`+1 where `id`=\'' . $category . '\'';
		self::$db->query($sql);
	}
}
?>