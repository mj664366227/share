<?php
require dirname(__FILE__).'/config.php';

//定义base_url
sharePHP::set_base_url(BASE_URL);

//运行sherePHP框架
sharePHP::run(DOCUMENT_ROOT);
?>