<?php
/**
 * share tools
 */
class toolscontroller extends controller {
	/**
	 * 构造函数
	 */
	public function __construct(){
		view::assign('menu', parent::menu(array('tools')));
	}
	
	/**
	 * 首页
	 */
	public function index(){
	}
}
?>