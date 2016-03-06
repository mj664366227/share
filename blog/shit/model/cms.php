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
		return self::$db->select('category')->order_by(array('id' => DESC))->query();
	}
}
?>