<?php
/**
 * 后台model
 */
class madmin extends model {

	/**
	 * 获取系统设定
	 */
	public static function get_setting() {
		return self::$db->select('system')->query();
	}
}
?>