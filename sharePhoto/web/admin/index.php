<?php
define('ADMIN_ROOT', dirname(__FILE__));
require ADMIN_ROOT.'/../config.php';



//初始化数据库
$db_type = DB_TYPE;
$database = 'share'.$db_type;
load_file(SHARE_ROOT.'class/db/'.$database);
model::init_db(new $database(DB_HOST, DB_USER, DB_PASS, DB_PRE, DB_NAME, DB_PORT, CHARSET));

//定义默认的类和方法
sharePHP::set_class('login');
sharePHP::set_method('index');

//导入必要的文件
load_file(ADMIN_ROOT.'/controller/manage');
load_file(ADMIN_ROOT.'/lang/'.LANG);

//定义框架运行模式
sharePHP::set_mode(MODE);

//定义base_url
sharePHP::set_base_url(BASE_URL);

//设置皮肤
view::set_skin(ADMIN_SKIN);

//运行sherePHP框架
sharePHP::run(ADMIN_ROOT);
?>