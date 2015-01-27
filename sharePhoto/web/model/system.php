<?php
/**
 * 系统model
 */
class msystem extends model{
	/**
	 * 获取设置
	 */
	public static function get_setting(){
		$rs = self::$db->select()->get('setting');
		return $rs[0];
	}
	
	/**
	 * 获取服务器
	 */
	public static function get_server($id = 0){
		if($id){
			self::$db->where(array('_id'=>intval($id)));
		}
		$rs = self::$db->select()->get('server');
		return $rs;
	}
}
?>