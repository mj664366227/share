<?php 
include 'config.php';
$smarty->assign('base_url',$system[0]['yuming']);
$smarty->assign('active',get_active($_SERVER['REQUEST_URI']));    //导航栏活动链接
$smarty->assign('web_title',$system[0]['webtitle']);
$smarty->assign('keyword',$system[0]['seo']);
$smarty->assign('description',$system[0]['description']);
$action = explode('&',$_SERVER['REQUEST_URI']);
$class = explode('=',$action[1]);
$method = explode('=',$action[2]);
$smarty->assign('model', $class[1].'.'.$method[1]);
$smarty->display('head.html');
?>