<?php
/**
 * 应用程序配置文件
 */

//是否开启debug模式
define('DEBUG', true);

//加密密匙
define('KEY', 'monitor!@#$|+_"?>(*^yaowan');

//设置时区
date_default_timezone_set('Asia/Shanghai');

//定义系统物理硬盘根目录
define('DOCUMENT_ROOT', realpath(dirname(__FILE__)) . '/');

//程序运行模式
//1普通模式   		http://localhost/?action=admin.admin&page=6
//2伪静态         	http://localhost/admin/admin/page/6
//3纯静态         	http://localhost/admin/admin/page_6.html
define('MODE', 1);

//导入框架核心文件
require DOCUMENT_ROOT.'../sharePHP/core/core.php';

//模式不运行以下代码
if (!$argv) {
	//导入语言包
	$lang = cookie::get('share_lang');
	if (!$lang) {
		$lang = 'cn';
	}
	load_file(DOCUMENT_ROOT.'model/system');
	msystem::set_lang($lang);
	load_file(DOCUMENT_ROOT.'/lang/'.$lang);

	//定义系统基础网址
	define('BASE_URL', 'http://'.$_SERVER['HTTP_HOST'].str_replace('index.php', '', $_SERVER['SCRIPT_NAME']));
}
//强制登录后台(忘记密码时候用！)
define('FORCE_LOGIN', true);

//定义皮肤
define('SKIN', 'gray');

//定义系统字符集
define('CHARSET', 'utf8');
define('CHARSET_HTML', 'utf-8');

//数据库配置
define('DB_TYPE', 'Mysql');
define('DB_HOST', '127.0.0.1');
define('DB_PORT', 3306);
define('DB_USER', 'root');
define('DB_PASS', 'root');
define('DB_PRE', 'm_');
define('DB_NAME', 'monitor');

//定义默认类和方法
define('DEFAULT_CLASS', 'view');
define('DEFAULT_METHOD', 'index');

//定义每次刷新的时间
define('REFRESH_TIME', 60000);
?>