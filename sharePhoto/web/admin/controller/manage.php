<?php
/**
 * 后台管理父类
 */
class managecontroller extends controller{
	/**
	 * 构造函数
	 */
	public function __construct(){
		parent::__construct();
		$manager = json_decode(secret::dede(cookie::get(md5(KEY)), KEY, DECODE), true);
		if($manager['auth'] != md5($manager['ip'].$manager['lass_login_time'].$manager['nick'].KEY)){
			redirect(url('login', 'logout'));
		}
		view::assign('nick', $manager['nick']);
		view::assign('menu', $this->_menu(false));
		if(FORCE_LOGIN === true) {
			$this->show_msg('notice', FORCE_LOGIN_NOTICE);
		}
	}
	
	/**
	 * 生成管理菜单
	 */
	public function menu(){
		return $this->_menu(false);
	}

	/**
	 * 生成管理菜单
	 * @param $is_system 是否为系统调用(系统调用不检查权限)
	 */
	protected function _menu($is_system){
		$dir = dirname(__FILE__);
		$self = basename(__FILE__);
		chdir($dir);
		$list = glob('*.php');
		$manager = json_decode(secret::dede(cookie::get('manager'), KEY, DECODE), true);
		if(!$is_system){
			$rank = explode(';', $manager['rank']);
			array_pop($rank);
		}
		$fliter_function = get_class_methods(substr($self, 0, -4).'controller');
		foreach($list as $file){
			if($file == $self || $file == 'admin.php' || $file == 'login.php'){
				continue;
			}
			$str = explode('/**', file_get_contents($dir.'/'.$file));
			$class = trim(str_replace('*', '', substr($str[1], 0, strrpos($str[1], '*/'))));
			foreach($str as $s){
				$s = explode('*',$s);
				foreach($s as $ss){
					$ss = str_replace('&lt;?php', '', $ss);
					if(!trim($ss)){
						continue;
					}
					if(strpos($ss, '@') <= 0 && strpos($ss, 'function') <= 0 && strpos($ss, '&lt;p&gt;') <= 0 && strpos($ss, '&lt;br&gt;') <= 0){
						continue;
					}
					$index = stripos($ss, '){');
					if($index > 0){
						$p1 = strpos($ss, '/');
						$p2 = strpos($ss, 'p');
						$ss = substr($ss, $p1 + 1, -$p2);
						$function = substr($ss, 0, $index);
					}
					if(strpos($function, 'private') > 0 || strpos($function, 'protected') > 0 || strpos($function, '__construct') > 0 || strpos($function, '__destruct') > 0){
						continue;
					}
					$function = str_replace('public ', '', substr($ss, 0, $index));
					$function = str_replace('function ', '', $function);
					$function = str_replace('()', '', $function);
					$filename = trim(substr($file, 0, -4));
					$function = $filename.'/'.trim($function);
					if($rank && !in_array($function, $rank)){
						continue;
					}
					$menu[$class.'#'.$filename][$function] = trim($s[1]);
				}
			}
		}
		return $menu;
	}
}
?>