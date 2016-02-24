<?php
/**
 * 应用程序配置文件
 */

// 是否开启debug模式
define('DEBUG', true);

// 加密密匙
define('KEY', 'LK(&TGP&fO*^di*^Re8%^%$&D*XUICO*P(G*)(YFDEZ%R%DC^%DSi86R^%*Du7^rf6i8F*6v');

// 设置时区
date_default_timezone_set('Asia/Shanghai');

// 定义系统物理硬盘根目录
define('DOCUMENT_ROOT', realpath(dirname(__FILE__)) . '/');

// 程序运行模式
// 1普通模式 http://localhost/admin/admin/page/6
// 2伪静态 http://localhost/?action=admin.admin&page=6
// 3纯静态 http://localhost/admin/admin/page_6.html
define('MODE', 1);

// 导入框架核心文件
require DOCUMENT_ROOT . '../sharePHP/core/core.php';

// 定义系统基础网址
define('BASE_URL', 'http://' . $_SERVER['HTTP_HOST'] . str_replace('index.php', '', $_SERVER['SCRIPT_NAME']));

// 定义皮肤
define('SKIN', 'gray');

// 数据库配置
define('DB_TYPE', 'Mysql');
define('DB_HOST', '127.0.0.1');
define('DB_PORT', 3306);
define('DB_USER', 'root');
define('DB_PASS', 'root');
define('DB_PRE', 'b_');
define('DB_NAME', 'blog');

// 定义系统字符集
define('CHARSET', 'utf8');
define('CHARSET_HTML', 'utf-8');

// 定义默认类和方法
define('DEFAULT_CLASS', 'index');
define('DEFAULT_METHOD', 'index');
?>