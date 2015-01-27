<?php
class logincontroller extends controller{
	/**
	 * 构造函数
	 */
	public function __construct(){
		parent::__construct();
	}
	/**
	 * 登录界面
	 */
	public function index(){
		view::assign('login', 1);
		view::display('login.index');
	}

	/**
	 * 管理员登录
	 */
	public function login(){
		if(FORCE_LOGIN === true){
			//强制进入后台，神马都不用输入！
			$set_cookie_array['ip'] = ip::ip_to_long(ip::get_user_ip());
			$set_cookie_array['nick'] = 'anonymous';
			$set_cookie_array['lass_login_time'] = $_SERVER['REQUEST_TIME'];
			$set_cookie_array['auth'] = md5($set_cookie_array['ip'].$set_cookie_array['lass_login_time'].$set_cookie_array['nick'].KEY);
			cookie::set(md5(KEY), secret::dede(json_encode($set_cookie_array), KEY));
			msystem::log('anonymous', FORCE_LOGIN_SYSTEM);
			redirect(url('admin', 'main'));
		}
		$user_yzm = strval($this->post('yzm'));
		$system_yzm = strval(cookie::get('yzm'));
		cookie::delete('yzm');
		if($system_yzm !== $user_yzm){
			view::assign('status', -3);
			$this->index();
			die();
		}
		$user = $this->post('user');
		$pass = $this->post('pass');
		$rs = madmin::login($user, $pass);
		if(is_array($rs)){
			$update['lass_login_time'] = $_SERVER['REQUEST_TIME'];
			$update['ip'] = ip::ip_to_long(ip::get_user_ip());
			$update['times'] = intval($rs['times']) + 1;
			madmin::update($rs['_id'], $update);
			msystem::log($rs['nick'], LOGIN);
			$set_cookie_array['lass_login_time'] = $update['lass_login_time'];
			$set_cookie_array['ip'] = $update['ip'];
			$set_cookie_array['nick'] = $rs['nick'];
			$set_cookie_array['rank'] = $rs['rank'];
			$set_cookie_array['auth'] = md5($update['ip'].$update['lass_login_time'].$rs['nick'].KEY);
			cookie::set(md5(KEY), secret::dede(json_encode($set_cookie_array), KEY));
			redirect(url('admin', 'main'));
		} else {
			view::assign('status', $rs);
			$this->index();
		}
	}

	/**
	 * 退出
	 */
	public function logout(){
		$manager = json_decode(secret::dede(cookie::get(md5(KEY)), KEY, DECODE), true);
		msystem::log($manager['nick'] ? $manager['nick'] : 'anonymous', LOGOUT);
		cookie::delete(md5(KEY));
		redirect(url('login', 'index'));
	}

	/**
	 * 获取验证码
	 */
	public function yzm(){
		if(FORCE_LOGIN === true){
			return;
		}
		yzm::show();
		cookie::set('yzm', yzm::get_code());
	}
}
?>