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
		}
	}
	
	/**
	 * 后台首页
	 */
	public function index() {
		view::assign('result', 1);
	}
}
?>