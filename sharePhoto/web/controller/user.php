<?php
/**
 * 用户
 */
load_file(sharePHP::get_application_dir().'/controller/sumi');
class usercontroller extends sumicontroller {
	private $uncheck = array('reg', 'login', 'show', 'one', 'lists');
	protected $user = null;
	/**
	 * 构造函数
	 */
	public function __construct(){
		parent::__construct();
		$this->user = json_decode(secret::dede(cookie::get(USER_COOKIE_KEY), KEY, DECODE), true);
		if(!intval($this->user['_id']) && !in_array(sharePHP::get_method(), $this->uncheck)){
			redirect(url('user', 'login'));
		} else {
			view::assign('user', $this->user);
		}
	}

	/**
	 * 注册
	 */
	public function reg() {
		$email = $this->post('email');
		if(!check::is_email($email)){
			return;
		}
		$nick = $this->post('nick');
		$pass = $this->post('pass1');
		muser::add($email, $nick, $pass);
	}

	/**
	 * 登录
	 */
	public function login() {
		$email = $this->post('email');
		if(!check::is_email($email)){
			return;
		}
		$pass = $this->post('pass');
		$user = muser::get_user_by_email($email);
		if($user['pass'] === secret::dede($pass, KEY)){
			$this->init_head($user);
			unset($user['head'], $user['pass']);
			cookie::set(USER_COOKIE_KEY, secret::dede(json_encode($user), KEY));
			redirect(url('user', 'index'));
		} else {
			echo '密码错误';
		}
	}

	/**
	 * 登出
	 */
	public function logout() {
		cookie::delete(USER_COOKIE_KEY);
		redirect($_SERVER['HTTP_REFERER']?$_SERVER['HTTP_REFERER']:BASE_URL);
	}

	/**
	 *个人主页
	 */
	public function index() {
		$my_albums = mworks::get_my_albums($this->user['_id']);
		view::assign('my_albums', $my_albums);
	}

	/**
	 * 个人信息
	 */
	public function info(){
		$nick = $this->post('nick');
		$email = $this->post('email');
		if($email && !check::is_email($email)){
			return;
		}
		$pass = $this->post('pass1');
		if($nick && $email && $pass){
			return;
		}
		if($nick){
			$this->user['nick'] = $data['nick'] = $nick;
		}
		if($email){
			$this->user['email'] = $data['email'] = $email;
		}
		if($pass){
			$data['pass'] = secret::dede($pass, KEY);
		}
		muser::modify($this->user['_id'], $data);
		cookie::set(USER_COOKIE_KEY, secret::dede(json_encode($this->user), KEY));
		view::assign('new', $this->user);
	}

	/**
	 * 更新头像
	 */
	public function updatehead(){
		$type = xss($this->post('type'));
		if($type !== 'head'){
			return;
		}
		$this->user['head'] = xss($this->post('t'));
		unset($this->user['head30'], $this->user['head64'], $this->user['head120']);
		muser::modify($this->user['_id'], array('head'=>$this->user['head']));
		$this->init_head($this->user);
		cookie::set(USER_COOKIE_KEY, secret::dede(json_encode($this->user), KEY));
		$rs['head30'] = $this->user['head30'];
		$rs['head64'] = $this->user['head64'];
		echo json_encode($rs);
	}

	/**
	 * 绑定社交账号
	 */
	public function bind(){

	}

	/**
	 * 初始化头像
	 * @param $user
	 */
	private function init_head(&$user){
		$user['head30'] = $user['head']?str_replace('?', '', $this->setting['server_url'][$user['server']]).'user/'.$user['_id'].'/'.$user['_id'].'_30_30.'.$user['head']:url().'skin/'.view::get_skin().'/images/default_30_30.png';
		$user['head64'] = $user['head']?str_replace('?', '', $this->setting['server_url'][$user['server']]).'user/'.$user['_id'].'/'.$user['_id'].'_64_64.'.$user['head']:url().'skin/'.view::get_skin().'/images/default_64_64.png';
		$user['head120'] = $user['head']?str_replace('?', '', $this->setting['server_url'][$user['server']]).'user/'.$user['_id'].'/'.$user['_id'].'_120_120.'.$user['head']:url().'skin/'.view::get_skin().'/images/default_120_120.png';
	}
}
?>