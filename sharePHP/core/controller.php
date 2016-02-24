<?php
/**
 * 所有Controller父类
 */
class controller {
	/**
	 * 构造函数
	 */
	public function __construct() {
		if (sharePHP::get_mode() === 2) {
			$get = fliter($_SERVER['REQUEST_URI']);
			$class = sharePHP::get_class();
			$method = sharePHP::get_method();
			$i = strpos(fliter($_SERVER['REQUEST_URI']), $class . '/' . $method);
			$get = explode('/', substr($get, $i + strlen($class . '/' . $method) + 1));
			$flag = null;
			foreach ($get as $k => $g) {
				if ($flag === null) {
					$GLOBALS['_GET'][$g] = '';
					$flag = fliter($g);
				} else {
					$GLOBALS['_GET'][$flag] = fliter($g);
					$flag = null;
				}
			}
		}
	}
	
	/**
	 * 析构函数
	 */
	public function __destruct() {
		// 显示模板(如果有特定模板显示的，在c层指定；如没有就按照$dir/class/mothod的方式寻找模板)
		view::display();
	}
	
	/**
	 * 是否有post数据
	 */
	protected function has_post() {
		return ! empty($GLOBALS['_POST']);
	}
	
	/**
	 * post
	 *
	 * @param $key 键        	
	 */
	protected function post($key) {
		return trim($GLOBALS['_POST'][$key]);
	}
	
	/**
	 * post int
	 *
	 * @param $key 键        	
	 */
	protected function post_int($key) {
		return intval($this->post($key));
	}
	
	/**
	 * post uint
	 * @param $key 键        	
	 */
	protected function post_uint($key) {
		$int = $this->post_int($key);
		if ($int <= 0) {
			return 0;
		}
		return $int;
	}
	
	/**
	 * post float
	 * @param $key 键        	
	 */
	protected function post_float($key) {
		return floatval($this->post($key));
	}
	
	/**
	 * post ufloat
	 *
	 * @param $key 键        	
	 */
	protected function post_ufloat($key) {
		$int = $this->post_float($key);
		if ($int <= 0) {
			return 0;
		}
		return $int;
	}
	
	/**
	 * post array
	 *
	 * @param $key 键        	
	 */
	protected function post_array($key) {
		return is_array($GLOBALS['_POST'][$key]) ? $GLOBALS['_POST'][$key] : array ();
	}
	
	/**
	 * 是否有get数据
	 */
	protected function has_get() {
		return ! empty($GLOBALS['_GET']);
	}
	
	/**
	 * get
	 *
	 * @param $key 键        	
	 */
	protected function get($key) {
		return trim($GLOBALS['_GET'][$key]);
	}
	
	/**
	 * get int
	 *
	 * @param $key 键        	
	 */
	protected function get_int($key) {
		return intval($this->get($key));
	}
	
	/**
	 * get uint
	 *
	 * @param $key 键        	
	 */
	protected function get_uint($key) {
		$int = $this->get_int($key);
		if ($int <= 0) {
			return 0;
		}
		return $int;
	}
	
	/**
	 * get float
	 *
	 * @param $key 键        	
	 */
	protected function get_float($key) {
		return floatval($this->get($key));
	}
	
	/**
	 * get ufloat
	 *
	 * @param $key 键        	
	 */
	protected function get_ufloat($key) {
		$int = $this->get_float($key);
		if ($int <= 0) {
			return 0;
		}
		return $int;
	}
	
	/**
	 * get array
	 *
	 * @param $key 键        	
	 */
	protected function get_array($key) {
		return is_array($GLOBALS['_GET'][$key]) ? $GLOBALS['_GET'][$key] : array ();
	}
	
	/**
	 * 是否有request数据
	 */
	protected function has_request() {
		return $this->has_post() || $this->has_get();
	}
	
	/**
	 * request
	 *
	 * @param $key 键        	
	 */
	protected function request($key) {
		$get = $this->get($key);
		return $get ? $get : $this->post($key);
	}
	
	/**
	 * request int
	 *
	 * @param $key 键        	
	 */
	protected function request_int($key) {
		return intval($this->request($key));
	}
	
	/**
	 * request uint
	 *
	 * @param $key 键        	
	 */
	protected function request_uint($key) {
		$int = $this->request_int($key);
		if ($int <= 0) {
			return 0;
		}
		return $int;
	}
	
	/**
	 * request float
	 *
	 * @param $key 键        	
	 */
	protected function request_float($key) {
		return floatval($this->request($key));
	}
	
	/**
	 * request ufloat
	 *
	 * @param $key 键        	
	 */
	protected function request_ufloat($key) {
		$int = $this->request_float($key);
		if ($int <= 0) {
			return 0;
		}
		return $int;
	}
	
	/**
	 * request array
	 *
	 * @param $key 键        	
	 */
	protected function request_array($key) {
		$array = $this->get_array($key);
		return $array ? $array : $this->post_array($key);
	}
	
	/**
	 * 输出提示信息
	 *
	 * @param $result success、notice、error        	
	 * @param $msg 消息内容        	
	 */
	protected function show_msg($result, $msg) {
		view::assign('result', $result);
		view::assign('tips', $msg);
	}
	
	/**
	 * 获取上传的文件
	 *
	 * @param $key 键        	
	 */
	protected function file($key) {
		return $GLOBALS['_FILES'][$key];
	}
	
	/**
	 * 生成管理菜单
	 */
	public function menu($fliter_class = array(), $fliter_method = array()) {
		$dir = sharePHP::get_application_dir() . 'controller/';
		chdir($dir);
		$list = glob('*.php');
		foreach ($list as $file) {
			if (in_array(str_replace('.php', '', $file), $fliter_class, true)) {
				continue;
			}
			$str = explode('/**', file_get_contents($dir . '/' . $file));
			$class = trim(str_replace('*', '', substr($str[1], 0, strrpos($str[1], '*/'))));
			foreach ($str as $s) {
				$s = explode('*', $s);
				foreach ($s as $ss) {
					$ss = str_replace('&lt;?php', '', $ss);
					if (! trim($ss)) {
						continue;
					}
					if (strpos($ss, '@') <= 0 && strpos($ss, 'function') <= 0 && strpos($ss, '&lt;p&gt;') <= 0 && strpos($ss, '&lt;br&gt;') <= 0) {
						continue;
					}
					$index = stripos($ss, '){');
					if ($index > 0) {
						$p1 = strpos($ss, '/');
						$p2 = strpos($ss, 'p');
						$ss = substr($ss, $p1 + 1, - $p2);
						$function = substr($ss, 0, $index);
					}
					if (strpos($function, 'private') > 0 || strpos($function, 'protected') > 0 || strpos($function, '__construct') > 0 || strpos($function, '__destruct') > 0) {
						continue;
					}
					$function = str_replace('public ', '', substr($ss, 0, $index));
					$function = str_replace('function ', '', $function);
					$function = str_replace('()', '', $function);
					$filename = trim(substr($file, 0, - 4));
					$function = $filename . '.' . str_replace('/', '', trim($function));
					if (in_array($function, $fliter_method, true)) {
						continue;
					}
					$menu[trim($class) . '#' . trim($filename)][trim($function)] = trim($s[1]);
				}
			}
		}
		view::assign('menu', $menu);
		return $menu;
	}
	
	/**
	 * 获取接口名字
	 */
	public function action_name() {
		$action_name = $this->get('action');
		if (! $action_name) {
			return;
		}
		$menu = $this->menu();
		if (! is_array($menu)) {
			return;
		}
		foreach ($this->menu() as $k => $m) {
			foreach ($m as $key => $name) {
				if ($key === $action_name) {
					view::assign('action_name', $name);
					return;
				}
			}
		}
	}
	
	/**
	 * 跳转
	 *
	 * @param $action 接口        	
	 */
	public function redirect($action) {
		$get = $this->get('action');
		$get = substr($get, 0, strrpos($get, '.'));
		redirect(url($get, $action));
	}
}
?>