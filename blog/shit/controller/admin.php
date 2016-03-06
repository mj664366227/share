<?php
/**
 * 后台父类
 */
class admincontroller extends controller {

	/**
	 * 构造函数
	 */
	public function __construct() {
		$cookie = cookie::get(COOKIE_KEY);
		if (!$cookie) {
			redirect('./');
			return;
		}
		view::assign('setting', madmin::get_setting());
		view::assign('now', time());
	}

	/**
	 * 后台首页
	 */
	public function index() {
	}

	/**
	 * 设置
	 */
	public function setting() {
	}
}
?>