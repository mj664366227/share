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

//导入框架核心文件
require DOCUMENT_ROOT . '../share-php/core/core.php';

//定义系统基础网址
define('BASE_URL', 'http://' . $_SERVER['HTTP_HOST'] . str_replace('index.php', '', $_SERVER['SCRIPT_NAME']));

//md文件根目录
define('MD_FILE_ROOT_PATH', DOCUMENT_ROOT.'md/');
?>