<?php
/**
 * 应用程序配置文件
 */

//是否开启debug模式
define('DEBUG', true);

//加密密匙
define('KEY', '!@#$%^&somi(ruan_&_oasiseekers_&_day)!@#$%^&');

//设置时区
date_default_timezone_set('Asia/Shanghai');

//定义系统物理硬盘根目录
define('DOCUMENT_ROOT', realpath(dirname(__FILE__)) . '/');

//程序运行模式
//1普通模式   	http://localhost/admin/admin/page/6
//2伪静态         	http://localhost/?action=admin.admin&page=6
//3纯静态         	http://localhost/admin/admin/page_6.html
define('MODE', 1);

//导入框架核心文件
require DOCUMENT_ROOT . '../../sharePHP/core/core.php';

//定义系统基础网址
define('BASE_URL', 'http://' . $_SERVER['HTTP_HOST'] . str_replace('index.php', '', $_SERVER['SCRIPT_NAME']));

//强制登录后台(忘记密码时候用！)
define('FORCE_LOGIN', false);

//定义皮肤
define('SKIN', 'gray');

//定义系统字符集
define('CHARSET', 'utf8');
define('CHARSET_HTML', 'utf-8');

//数据库配置
define('DB_TYPE', 'Mongodb');
define('DB_HOST', '127.0.0.1');
define('DB_PORT', 27017);
define('DB_USER', 'www.showme.cc');
define('DB_PASS', 'just_looking');
define('DB_PRE', '');
define('DB_NAME', 'showme');

//定义默认类和方法
define('DEFAULT_CLASS', 'sumi');
define('DEFAULT_METHOD', 'index');

//网站语言
define('LANG', 'zh-CN');

//后台皮肤
define('ADMIN_SKIN', 'black');

//默认分页大小
define('ADMIN_PAGE_SIZE', 20);//后台
define('SOMI_PAGE_SIZE', 20);//前台

//用户cookie key
define('USER_COOKIE_KEY', 'user');
?>