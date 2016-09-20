<?php
include "config.php";
$time=time()-96400;
setcookie('sina','',$time);
setcookie('qq','',$time);
setcookie('libmill','',$time);
unset($GLOBALS);
$_SESSION['libmill_msg'] = '退出成功！正在打开主页！';
$_SESSION['libmill_url'] = $system[0]['yuming'].'index.php?skin='.$_COOKIE['libmill_skin'];
redirect();
?>