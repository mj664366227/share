<?php
/**
 * 后台
 */
class admincontroller extends controller {
	/**
	 * 后台首页
	 */
	public function index() {
		view::assign('result', 1);
	}
}
?>