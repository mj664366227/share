<?php
/**
 * 管理员管理
 */
class managercontroller extends managecontroller{
	/**
	 * 管理员列表
	 */
	public function lists(){
		$page = $this->get_uint('page');
		$page = $page ? $page : 1;
		$rs = madmin::ls($page);
		page::init($page, ADMIN_PAGE_SIZE, $rs['count']);
		page::search();
		page::total();
		view::assign('page', page::show());
		view::assign('list', $rs['list']);
	}

	/**
	 * 添加管理员
	 */
	public function add(){
		$name = $this->post('name');
		$nick = $this->post('nick');
		$pass = $this->post('pass1');
		$rank = $this->post_array('rank');
		if($name && $nick && $pass){
			if(madmin::get($name)){
				$this->show_msg('error', USER_NAME_DUPLICATE);
			} else {
				madmin::add($name, $nick, $pass, implode($rank, ';'));
				$this->show_msg('success', USER_ADD_SUCCESS);
				$this->log = ADD_USER.$name;
			}
		}
		view::assign('rank', $this->_menu(true));
	}

	//删除管理员
	public function delete(){
		$id = $this->get_uint('id');
		$nick = $this->get('nick');
		if($id == cookie::get('id')){
			//不能删除自己
			$this->show_msg('error', CAN_NOT_DELETE_MYSELF);
			$this->manager();
			die();
		}
		madmin::delete($id);
		$this->show_msg('success', USER_DELETE_SUCCESS);
		$this->log = DELETE_USER.$nick;
		$page = $this->get('page');
		$page = $page ? $page : 1;
		$rs = madmin::ls($page);
		page::init($page, ADMIN_PAGE_SIZE, $rs['count']);
		page::search();
		page::total();
		view::assign('page', page::show());
		view::assign('list', $rs['list']);
		view::display('manager.lists');
	}

	/**
	 * 管理员日志
	 */
	public function log(){
		$page = $this->get_uint('page');
		$page = $page ? $page : 1;
		$rs = msystem::ls_log($page);
		page::init($page, ADMIN_PAGE_SIZE, $rs['count']);
		page::search();
		page::total();
		view::assign('page', page::show());
		view::assign('list', $rs['list']);
	}
}
?>