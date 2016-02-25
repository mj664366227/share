<?php
/**
 * 登录控制器
 */
class logincontroller extends controller {

	/**
	 * 登录
	 */
	public function login() {
		$username = $this->post('username');
		$password = $this->post('password');
		if ($username === 'ruanzhijun' && $password === 'ruanzhijun') {
			cookie::set(COOKIE_KEY, 1);
			redirect('?action=admin.index');
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