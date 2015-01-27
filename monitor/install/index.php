<?php
require dirname(__FILE__).'/../config.php';

//定义默认的类和方法
sharePHP::set_class('install');
sharePHP::set_method('step1');

//定义框架运行模式
sharePHP::set_mode(MODE);

//定义base_url
sharePHP::set_base_url(BASE_URL.'../');

//设置皮肤
view::set_skin(SKIN);

//运行sherePHP框架
sharePHP::run(DOCUMENT_ROOT);
?>