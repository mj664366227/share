<?php
/**
 * 作品
 */
load_file(sharePHP::get_application_dir().'/controller/user');
class contentcontroller extends usercontroller {
	/**
	 * 构造函数
	 */
	public function __construct(){
		parent::__construct();
	}

	/**
	 * 添加文章
	 */
	public function add(){
		$title = strip_tags($this->post('title'));
		$file = strip_tags($this->post('file'));
		$category = $this->post_uint('category');
		$content = $this->post('content');
		if($title && $file && $category && $content){
			mcontent::add($this->user['_id'], $title, $category, $file, $content, $this->setting['server']);
		}
		view::assign('uid', $this->user['_id']);
		view::assign('category', mcontent::get_all());
		view::assign('count', mcontent::count($this->user['_id'])+1);
	}

	/**
	 * 文章列表
	 */
	public function lists(){
		$category = $this->get_uint('category');
		$order = string::filter($this->get('order'));
		if(!$order){
			$order = 'new';
		}
		$page = $this->get_uint('page');
		$page = $page ? $page : 1;
		view::assign('cid', $category);
		view::assign('order', $order);
		view::assign('category', mcontent::get_all());
		$pagesize = 4;
		$list = mcontent::lists($category, $order, $page, $pagesize);
		$user = array();
		foreach($list['list'] as $c){
			$user[] = intval($c['uid']);
		}
		$users = muser::get_user_by_uid($user);
		$userinfo = array();
		foreach($users as $u){
			$userinfo[$u['_id']]['nick'] = $u['nick'];
			$userinfo[$u['_id']]['head'] = $u['head'];
			$userinfo[$u['_id']]['server'] = $u['server'];
		}
		page::init($page, $pagesize, $list['count']);
		page::search();
		page::total();
		view::assign('page', page::show());
		view::assign('userinfo', $userinfo);
		view::assign('list', $list);
	}

	/**
	 * 评论
	 */
	public function comment(){

	}
}
?>