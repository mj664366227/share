<?php
/**
 * 系统model
 */
class msystem extends model{
	/**
	 * 设置语言
	 * @param $lang
	 */
	public static function set_lang($lang){
		cookie::set('share_lang', $lang, $_SERVER['REQUEST_TIME'] + 9999999);
	}
	
	/**
	 * 导出本系统的sql
	 */
	public static function export_sql(){
		self::$db->backup(sharePHP::get_application_dir().'monitor.sql');
	}
}
?>