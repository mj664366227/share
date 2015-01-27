<?php
/**
 * 系统 model
 */
class msystem extends model{
	/**
	 * 服务器基本信息
	 * @return $array
	 */
	public static function info(){
		//php版本
		$info['php_version'] = phpversion();

		//数据库版本
		$info['db_version'] = self::$db->version();

		//当前硬盘总容量
		$info['disk_total'] = system::get_disk_total('', true);

		//当前硬盘剩余容量
		$info['disk_free'] = system::get_disk_free('', true);

		//http端口
		$info['http_port'] = $_SERVER['SERVER_PORT'];

		//内存限制
		$info['memory_limit'] = ini_get('memory_limit');

		//脚本最大执行时间
		$info['max_execution_time'] = ini_get('max_execution_time').'s';

		//提交数据最大时间
		$info['max_input_time'] = ini_get('max_input_time').'s';

		//上传文件的最大值
		$info['post_max_size'] = ini_get('post_max_size');

		//是否支持文件上传
		$info['file_uploads'] = ini_get('file_uploads') ? YES : NO;

		//上传文件的最大大小
		$info['upload_max_filesize'] = ini_get('upload_max_filesize');

		//一次可以上传的最大文件个数
		$info['max_file_uploads'] = ini_get('max_file_uploads');

		//服务器IP
		$info['server_ip'] = $_SERVER['SERVER_ADDR'];

		//服务器操作系统名称
		$info['os'] = PHP_OS;

		//Zend版本
		$info['zend_version'] = zend_version();

		//http服务器
		$info['sapi_name'] = php_sapi_name().'('.$_SERVER['SERVER_SOFTWARE'].')';

		//临时文件路径
		$info['temp_dir'] = sys_get_temp_dir();

		//后台物理地址
		$info['base_dir'] = sharePHP::get_application_dir();
		return $info;
	}

	/**
	 * 记录系统日志
	 * @param $user 操作者
	 * @param $content 日志内容
	 */
	public static function log($user, $content){
		return self::$db->insert('log', array('time' => $_SERVER['REQUEST_TIME'],
		'content' => $content, 'user' => $user));
	}

	/**
	 * 列出系统日志
	 * @param $page 当前页码
	 * @param $size 分页大小(默认是配置的大小)
	 * @return 系统日志列表
	 */
	public static function ls_log($page, $size = ADMIN_PAGE_SIZE){
		$list = self::$db->select()->order_by(array('time' => -1))
		->limit(($page - 1) * $size, $size)->get('log');
		$rs['list'] = $list;
		$rs['count'] = self::$db->count('log');
		return $rs;
	}

	/**
	 * 保存设置
	 */
	public static function save_setting($title, $description, $keywords, $server, $statistics){
		$data['_id'] = 1;
		$data['title'] = $title;
		$data['description'] = $description;
		$data['keywords'] = $keywords;
		$data['server'] = $server;
		$data['statistics'] = $statistics;
		self::$db->save('setting', $data);
	}
	
	/**
	 * 获取设置
	 */
	public static function get_setting(){
		$rs = self::$db->select()->get('setting');
		return $rs[0];
	}
}
?>