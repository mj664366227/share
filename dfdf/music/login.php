<?php 
include "head.php";
if($_POST['submit']){
	$username = $_POST['username'];
	$pwd = $_POST['pwd'];
	if(!$username || !$pwd){
		return;
	}
	$data = uc_user_login($username, $pwd);
	if(intval($data[0]) > 0){
		setcookie('libmill', uc_authcode($username,'ENCODE'), time()+3600*24*30);
		$_SESSION['libmill_msg'] = '登录成功！正在登录后台！';
		$_SESSION['libmill_url'] = $system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=index';
	} else {
		unset($GLOBALS);
		$_SESSION['libmill_msg'] = '登录失败！请重新登录！';
		$_SESSION['libmill_url'] = $system[0]['yuming'].'login.php?skin='.$_COOKIE['libmill_skin'];
	}
	redirect();
}
$smarty->display('login_.html');
$conn->close();
?>