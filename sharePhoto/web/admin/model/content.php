<?php
/**
 * 文章管理model
 */
class mcontent extends model{
	/**
	 * 添加新文章类型
	 * @param $name 类型名称
	 */
	public static function add($name){
		self::$db->insert('content_category', array('name'=>$name));
	}
	
	/**
	 * 删除文章类型
	 * @param $id
	 */
	public static function delete($id){
		self::$db->delete('content_category',array('_id'=>intval($id)));
	}
	
	/**
	 * 获取所有文章类型
	 */
	public static function get_all(){
		return self::$db->select(array('_id','name'))->order_by(array('_id'=>ASC))->get('content_category');
	}
}