<?php
require dirname(__FILE__) . '/config.php';

//初始化数据库
$db_type = DB_TYPE;
$database = 'share' . $db_type;
model::init_db(new $database(DB_HOST, DB_USER, DB_PASS, DB_PRE, DB_NAME, DB_PORT, CHARSET));

//定义默认的类和方法
sharePHP::set_class(DEFAULT_CLASS);
sharePHP::set_method(DEFAULT_METHOD);

//定义框架运行模式
sharePHP::set_mode(MODE);

//定义base_url
sharePHP::set_base_url(BASE_URL);

//设置皮肤
view::set_skin(SKIN);

//运行sherePHP框架
sharePHP::run(DOCUMENT_ROOT);
?>