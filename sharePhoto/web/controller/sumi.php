<?php
class sumicontroller extends controller {
	protected $setting = null;
	/**
	 * 构造函数
	 */
	public function __construct(){
		$this->setting = msystem::get_setting();
		$server = msystem::get_server();
		foreach($server as $s){
			$this->setting['server_url'][$s['_id']] = $s['url'];
		}
		view::assign('setting', $this->setting);
	}
	
	/**
	 * 首页
	 */
	public function index() {
	}
}
?>