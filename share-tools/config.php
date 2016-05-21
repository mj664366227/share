<?php
/**
 * 应用程序配置文件
 */

//是否开启debug模式
define('DEBUG', true);

//加密密匙
define('KEY', 'a∝s≡jˇ≤F〓1￡◎f');

//设置时区
date_default_timezone_set('Asia/Shanghai');

//定义系统物理硬盘根目录
define('DOCUMENT_ROOT', realpath(dirname(__FILE__)) . '/');

//程序运行模式
//1普通模式   		http://localhost/admin/admin/page/6
//2伪静态         	http://localhost/?action=admin.admin&page=6
//3纯静态         	http://localhost/admin/admin/page_6.html
define('MODE', 1);

//定义网站标题
define('SITE_TITLE', 'share - tools');

//导入框架核心文件
require DOCUMENT_ROOT . '../sharePHP/core/core.php';

//定义系统基础网址
define('BASE_URL', 'http://' . $_SERVER['HTTP_HOST'] . str_replace('index.php', '', $_SERVER['SCRIPT_NAME']));

//定义皮肤
define('SKIN', 'default');

//定义系统字符集
define('CHARSET', 'utf8');
define('CHARSET_HTML', 'utf-8');


//定义默认类和方法
define('DEFAULT_CLASS', 'tools');
define('DEFAULT_METHOD', 'index');

//md文件根目录
define('MD_FILE_ROOT_PATH', DOCUMENT_ROOT.'md/');
?>