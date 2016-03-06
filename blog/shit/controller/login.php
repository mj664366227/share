<?php
/**
 * 登录控制器
 */
class logincontroller extends controller {

	/**
	 * 登录
	 */
	public function login() {
		$cookie = cookie::get(COOKIE_KEY);
		if ($cookie) {
			redirect(url('admin', 'index'));
			return;
		}
		$username = $this->post('username');
		$password = $this->post('password');
		if ($username === 'ruanzhijun' && $password === 'ruanzhijun') {
			cookie::set(COOKIE_KEY, 1);
			redirect(url('admin', 'index'));
			return;
		}
	}

	/**
	 * 登出
	 */
	public function logout() {
		cookie::delete(COOKIE_KEY);
		redirect('./');
	}
}
?>