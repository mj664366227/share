<?php

/**
 * 安装程序
 */
class installcontroller extends controller {
	/**
	 * 构造函数
	 */
	public function __construct(){
		error_reporting(0);
		$db_type = DB_TYPE;
		$database = 'share'.$db_type;
		view::assign('is_install', minstall::check(DB_HOST, DB_USER, DB_PASS, DB_PRE, DB_NAME, DB_PORT));
		if(DB_HOST && DB_USER && DB_PASS && DB_NAME && DB_PORT){
			model::init_db(new $database(DB_HOST, DB_USER, DB_PASS, DB_PRE, DB_NAME, DB_PORT, CHARSET));
		}
	}

	/**
	 * 第一步
	 */
	public function step1(){
	}

	/**
	 * 第二步
	 */
	public function step2(){
		$host = $this->post('host');
		$port = $this->post_uint('port');
		$user = $this->post('user');
		$pass = $this->post('pass');
		$pre = $this->post('pre');
		$db = $this->post('db');
		if(!$host || !$port || !$user || !$pass || !$db) {
			redirect('./');
		}
		view::assign('host', $host);
		view::assign('port', $port);
		view::assign('user', $user);
		view::assign('pass', $pass);
		view::assign('pre', $pre);
		view::assign('db', $db);
	}

	/**
	 * 第三步
	 */
	public function step3(){
		$name = $this->post('name');
		$pass = $this->post('pass');
		if($name && $pass){
			muser::add($name, $pass);
			redirect('./');
		}
	}

	/**
	 * 执行安装
	 */
	public function go(){
		$host = $this->post('host');
		$port = $this->post_uint('port');
		$user = $this->post('user');
		$pass = $this->post('pass');
		$pre = $this->post('pre');
		$db = $this->post('db');
		if(!$host || !$port || !$user || !$pass || !$db) {
			redirect('./');
		}
		minstall::build($host, $port, $user, $pass, $pre, $db);
		minstall::update_config_file($host, $port, $user, $pass, $pre, $db);
	}

	/**
	 * ajax
	 */
	public function ajax(){
		$rs = array();
		foreach (minstall::show() as $table) {
			$rs[] = trim($table['Tables_in_'.DB_NAME]);
		}
		if(count($rs) == count(minstall::sql())) {
			$rs[] = 'finish';
		}
		echo json_encode($rs);
	}
}
?>