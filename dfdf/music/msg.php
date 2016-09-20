<?php
include "head.php";
//消息提示
if($_SESSION['libmill_msg']){
	$smarty->assign('msg', $_SESSION['libmill_msg']);
	$smarty->assign('url', $_SESSION['libmill_url']);
	$smarty->assign('second', $_SESSION['libmill_second'] ? $_SESSION['libmill_second'] : 5);
} else {
	header('Location:'.$system[0]['yuming']);
}
unset($_SESSION['libmill_msg'], $_SESSION['libmill_url'], $_SESSION['libmill_second']);
$smarty->display('msg.html');
$conn->close();
?>