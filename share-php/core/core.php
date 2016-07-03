<?php
/**
 * 框架核心文件
 */

//定义本框架的基本路径
define('SHARE_ROOT', realpath(dirname(__FILE__) . '/../') . '/');

//导入必要文件
require SHARE_ROOT . 'core/controller.php';
require SHARE_ROOT . 'core/model.php';
class sharePHP {
	/**
	 * 应用程序路径
	 */
	private static $application_dir = null;
	/**
	 * 基本http地址
	 */
	private static $base_url = null;

	/**
	 * 获取应用程序路径
	 */
	public static function get_application_dir() {
		return self::$application_dir . '/';
	}

	/**
	 * 设置基本http地址
	 * @param $url
	 */
	public static function set_base_url($url) {
		self::$base_url = $url;
	}

	/**
	 * 获取基本http地址
	 */
	public static function get_base_url() {
		return self::$base_url;
	}

	/**
	 * 运行框架
	 * @param $application_dir 应用程序路径
	 */
	public static function run($application_dir) {
		if (!$application_dir) {
			echo_('please defined your application dir...', true);
		}
		if (!file_exists($application_dir)) {
			echo_('directory ' . $application_dir . ' is not exists, place check...', true);
		}
		self::$application_dir = $application_dir;
		self::action();
	}

	/**
	 * 处理外部接口
	 */
	private static function action() {
		$application_dir = str_replace('\\', '/', self::$application_dir);
		$application_base_root = str_replace($_SERVER['DOCUMENT_ROOT'], '', $application_dir);
		$parse_url = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
		$request_param = explode('/', substr(str_replace($application_base_root, '', $parse_url), 1));
		
		if ($request_param[0] && $request_param[1]) {
			//如果有定义类和方法的覆盖默认的
			self::set_class(strtolower($request_param[0]));
			self::set_method(strtolower($request_param[1]));
		}
		
		$class = self::$class . 'controller';
		$obj = new $class();
		if (!class_exists($class)) {
			if (DEBUG === true) {
				echo_('class ' . $class . ' is not exists...', true);
			} else {
				error404();
				die();
			}
		}
		if (!method_exists($obj, self::$method)) {
			if (DEBUG === true) {
				echo_('action ' . self::$method . ' is not exists...', true);
			} else {
				error404();
				die();
			}
		}
		$method = self::$method;
		$obj->$method();
	}
}

/**
 * 输出错误信息(调试程序用)
 * @param $str 错误信息
 * @param $is_need_die 是否需要停止程序(默认是否)
 * @return
 */
function echo_($str = null, $is_need_die = false) {
	echo '<pre style="text-align:left">';
	print_r($str);
	echo '</pre>';
	if ($is_need_die === true) {
		die();
	}
}

/**
 * 输出变量的结构(调试程序用)
 * @param $var 变量
 * @param $is_need_die 是否需要停止程序(默认是否)
 * @return
 */
function echo_dump($var = null, $is_need_die = false) {
	echo '<pre style="text-align:left">';
	var_dump($var);
	echo '</pre>';
	if ($is_need_die === true) {
		die();
	}
}

/**
 * 导入文件
 * @param $file 文件路径
 */
function load_file($file) {
	if (!file_exists($file)) {
		if (DEBUG === true) {
			echo_('file ' . $file . ' not exists...', true);
		} else {
			error404();
			die();
		}
	}
	require $file;
}

/**
 * 自动导入
 * @param $class 类名(如果是导入model则是m + model名，例：madmin::list())
 */
function __autoload($class) {
	if (strpos($class, 'controller') > 0) {
		$autoload = sharePHP::get_application_dir() . '/controller/' . str_replace('controller', '', $class) . '.php';
	} else {
		$autoload = sharePHP::get_application_dir() . '/model/' . substr($class, 1) . '.php';
		if (!file_exists($autoload)) {
			$autoload = SHARE_ROOT . 'class/' . $class . '.php';
		}
	}
	load_file($autoload);
	
	//分页类特殊处理
	if ($class === 'page') {
		page::set_rewrite(MODE === 2);
	}
}

/**
 * 构造url
 * @param $class 类名
 * @param $method 方法名
 * @param $param 参数(a=1&b=2&c=3...)
 * @param $static 是否是静态地址
 */
function url($class = null, $method = null, $param = null, $static = true) {
	$url = sharePHP::get_base_url();
	if ($class && $method) {
		if (sharePHP::get_mode() == 1) {
			$url .= '?action=' . $class . '.' . $method;
		} else if (sharePHP::get_mode() == 2) {
			$url .= $class . '/' . $method . '/';
		} else {
			if ($static === true) {
				$url .= 'html/' . $class . '/' . $method . '/';
			} else {
				if (MODE == 1) {
					$url .= '?action=' . $class . '.' . $method;
				} else {
					$url .= $class . '/' . $method . '/';
				}
			}
		}
	}
	
	if (!$param) {
		return $url;
	}
	
	$action = explode('&', $param);
	foreach ($action as $act) {
		if (sharePHP::get_mode() == 1) {
			$url .= '&' . $act;
		} else if (sharePHP::get_mode() == 2) {
			$p = explode('=', $act);
			$url .= $p[0] . '/' . $p[1] . '/';
		} else {
			$p = explode('=', $act);
			if ($static === true) {
				$url .= $p[1] . '.html';
			} else {
				if (MODE == 1) {
					$url .= '&' . $act;
				} else {
					$url .= $p[0] . '/' . $p[1] . '/';
				}
			}
		}
	}
	return $url;
}

/**
 * 获取当前url
 */
function cururl() {
	return 'http://' . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
}
?>