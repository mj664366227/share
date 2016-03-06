<?php
/**
 * 后台model
 */
class madmin extends model {

	/**
	 * 获取系统设定
	 */
	public static function get_setting() {
		$data = self::$db->select('system')->query();
		$setting = array ();
		foreach ($data as $k => $v) {
			$setting[trim($v['key'])] = trim($v['value']);
		}
		return $setting;
	}
}
?>