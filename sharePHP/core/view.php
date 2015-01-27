<?php
/**
 * 视图层
 */
class view{
	/**
	 * 变量列表
	 */
	private static $vars = array();
	/**
	 * 皮肤
	 */
	private static $skin = null;
	/**
	 * 当前模板
	 */
	private static $display = null;

	/**
	 * 导入变量
	 * @param $varname 变量名
	 * @param $value 变量值
	 */
	public static function assign($varname, $value) {
		self::$vars[$varname] = $value;
	}

	/**
	 * 删除变量
	 * @param $varname 变量名
	 * @param $value 变量值
	 */
	public static function remove($varname) {
		unset(self::$vars[$varname]);
	}

	/**
	 * 显示模板
	 * @param $file_name 模板文件名(默认是类名/方法名)
	 */
	public static function display($file_name = ''){
		if(!$file_name){
			$file_name = sharePHP::get_class().'/'.sharePHP::get_method();
		}

		//寻找模板文件
		self::$display = $file_name;
		$path = self::dir().$file_name;
		$file_name = $path.'.php';
		if(!file_exists($file_name)){
			return;
		}

		//把变量导入模板文件
		foreach (self::$vars as $var_k_tmp => $var_v_tmp) {
			eval('$' . $var_k_tmp . ' = ' . var_export($var_v_tmp, true) . ";\r\n");
		}

		require $file_name;
	}

	/**
	 * 获取视图层的根目录
	 */
	public static function dir(){
		return sharePHP::get_application_dir().'/skin/'.self::$skin.'/view/';
	}

	/**
	 * 获取当前模板
	 */
	public static function get_display(){
		return self::$display;
	}

	/**
	 * 设置皮肤
	 * @param $skin 皮肤
	 */
	public static function set_skin($skin){
		self::$skin = $skin;
	}

	/**
	 * 获取皮肤
	 */
	public static function get_skin(){
		return self::$skin;
	}

	/**
	 * 导入css文件
	 * @param $css_file_name css文件名
	 */
	public static function load_css($css_file_name){
		echo '<link rel="stylesheet" href="'.sharePHP::get_base_url().'skin/'.self::$skin.'/css/'.$css_file_name.'.css?'.filesystem::mtime(sharePHP::get_application_dir().'/skin/'.self::$skin.'/css/'.$css_file_name.'.css').'" type="text/css">';
	}

	/**
	 * 导入js文件
	 * @param $js_file_name js文件名
	 */
	public static function load_js($js_file_name){
		echo '<script type="text/javascript" src="'.sharePHP::get_base_url().'skin/'.self::$skin.'/js/'.$js_file_name.'.js?'.filesystem::mtime(sharePHP::get_application_dir().'/skin/'.self::$skin.'/js/'.$js_file_name.'.js').'"></script>';
	}

	/**
	 * 导入图片
	 * @param $img 图片名称
	 * @param $width 图片宽度
	 * @param $height 图片高度
	 * @param $alt 注释
	 */
	public static function load_img($img, $width = 0, $height = 0, $alt = null){
		if($width > 0 && $height > 0){
			$html = '<img src="'.sharePHP::get_base_url().'skin/'.self::$skin.'/images/'.$img.'" width="'.$width.'" height="'.$height.'" '.($alt?('alt="'.$alt.'"'):'').'/>';
		} else {
			$html = '<img src="'.sharePHP::get_base_url().'skin/'.self::$skin.'/images/'.$img.'" '.($alt?('alt="'.$alt.'"'):'').'/>';
		}
		echo $html;
	}

	/**
	 * 导入网站图标
	 */
	public static function load_short_icon(){
		echo '<link rel="shortcut icon" href="'.sharePHP::get_base_url().'skin/'.self::$skin.'/images/favicon.ico?'.filesystem::mtime(sharePHP::get_application_dir().'/skin/'.self::$skin.'/images/favicon.ico').'"/>';
	}

	/**
	 * 网站关键字
	 */
	public static function keywords(){
		$str = '<meta name="keywords" content="';
		foreach (func_get_args() as $keywords) {
			$str .= $keywords.',';
		}
		$str = substr($str, 0, -1);
		$str .= '">';
		echo $str;
	}

	/**
	 * 网站描述
	 */
	public static function description(){
		$str = '<meta name="description" content="';
		foreach (func_get_args() as $description) {
			$str .= $description.',';
		}
		$str = substr($str, 0, -1);
		$str .= '">';
		echo $str;
	}

	/**
	 * 网站版权
	 * @param $author 作者
	 */
	public static function copyright($author){
		$now = date('Y');
		if($now == START_YEAR) {
			$str = START_YEAR;
		} else {
			$str = START_YEAR.'-'.$now;
		}
		echo 'Copyright &copy; '.$str.' Powered by '.$author;
	}
}
?>