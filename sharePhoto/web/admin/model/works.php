<?php
/**
 * 作品管理model
 */
class mworks extends model{
	/**
	 * 添加新作品类型
	 * @param $name 类型名称
	 */
	public static function add($name){
		self::$db->insert('works_category', array('name'=>$name));
	}

	/**
	 * 删除作品类型
	 * @param $id
	 */
	public static function delete($id){
		self::$db->delete('works_category',array('_id'=>intval($id)));
	}

	/**
	 * 获取所有作品类型
	 */
	public static function get_all(){
		return self::$db->select(array('_id','name'))->order_by(array('_id'=>ASC))->get('works_category');
	}
}