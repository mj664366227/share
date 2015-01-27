<?php

/**
 * 后台
 */
class admincontroller extends controller {
	/**
	 * 构造函数
	 */
	public function __construct() {
		$menu = array (
			'index' => SERVER_LIST,
			'add' => ADD_SERVER,
			'export' => EXPORT_SQL
		);
		view::assign('menu', $menu);
	}

	/**
	 * 析构函数
	 */
	public function __destruct() {
		global $lang;
		view::assign('class', sharePHP::get_class());
		view::assign('action', sharePHP::get_method());
		view::display();
	}

	/**
	 * 登录
	 */
	public function login(){
		view::remove('menu');
		$name = $this->post('user');
		$pass = $this->post('pass');
		if(!$name || !$pass) {
			return;
		}

		$user = muser::get($name);
		if(!$user) {
			view::assign('tips', USER_NOT_EXISTS);
			return;
		}
		if(md5(sha1($name.KEY.$pass)) !== $user['pass']){
			view::assign('tips', PASSWORD_INCORRECT);
			return;
		}
		redirect('?action=admin.index');
	}

	/**
	 * 主页
	 */
	public function index(){
		$server_list = mserver::ls();
		view::assign('server_list', $server_list);
	}
	
	/**
	 * 添加服务器
	 */
	public function add(){
		$submit = $this->post('submit');
		$server = $this->post('server');
		$ip = $this->post('ip');
		$port = $this->post_uint('port');
		$os = $this->post('os');
		$security_name = $this->post('security_name');
		$pass_phrase = $this->post('pass_phrase');
		$auth_protocol = $this->post('auth_protocol');
		$priv_protocol = $this->post('priv_protocol');
		if(!$submit && (!$server || $port <= 0 || !$ip || !$security_name || !$pass_phrase)) {
			view::assign('tips', PLEASE_INPUT_ALL);
			return;
		}
		
		mserver::add($server, $ip, $port, $os, $security_name, $pass_phrase, $auth_protocol, $priv_protocol);
	}
	
	/**
	 * 导出sql
	 */
	public function export(){
		msystem::export_sql();	
	}
}
?>