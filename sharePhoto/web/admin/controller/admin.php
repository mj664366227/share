<?php
class admincontroller extends managecontroller{
	public function main(){
	}
	
	/**
	 * 后台首页
	 */
	public function admin(){
		$page = $this->get_uint('page');
		$page = $page ? $page : 1;
		page::init($page, 10, 9652);
		page::search();
		page::total();
		view::assign('info', msystem::info());
		view::assign('page', page::show());
	}
}
?>