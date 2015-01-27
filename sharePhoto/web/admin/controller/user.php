<?php
/**
 * 用户管理
 */
class usercontroller extends managecontroller{
	/**
	 * 用户列表
	 */
	public function lists(){
		$page = $this->get_uint('page');
		$page = $page ? $page : 1;
		$rs = muser::lists($page);
		page::init($page, ADMIN_PAGE_SIZE, $rs['count']);
		page::search();
		page::total();
		view::assign('page', page::show());
		view::assign('list', $rs['list']);
	}

	//用户搜索
	public function search(){
		$nick = fliter($this->request('nick'));
		$page = $this->get_uint('page');
		$page = $page ? $page : 1;
		$rs = muser::search($nick, $page);
		page::init($page, ADMIN_PAGE_SIZE, $rs['count']);
		page::search();
		page::total();
		view::assign('page', page::show());
		view::assign('list', $rs['list']);
		view::assign('search_nick', $nick);
		view::display('member.lists');
	}

	/**
	 * 注册走势
	 */
	public function registertrend(){
	}

	/**
	 * 在线情况
	 */
	public function onlinetrend(){
	}
}