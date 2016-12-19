<?php 
include "head.php";
if($_POST['submit']){
	$username = trim(clean($_POST['username']));
	$email = trim($_POST['email']);
	$pwd = trim(clean($_POST['pwd1']));
	$rs = get_my_array($conn,"select uid from `uc_members` where `email`='$email'");
	if($rs[0]['uid']){
		$uid = -6;
	} else {
		$uid = uc_user_register($username, $pwd, $email);
	}
	if($uid > 0){
		$_SESSION['libmill_msg'] = '注册成功！';
		$ok = 1;
	} else {
		switch($uid){
			case -1:
				$_SESSION['libmill_msg'] = '用户名不合法！';
			break;
			
			case -2:
				$_SESSION['libmill_msg'] = '包含不允许注册的词语！';
			break;
			
			case -3:
				$_SESSION['libmill_msg'] = '用户名已经存在！';
			break;
			
			case -4:
				$_SESSION['libmill_msg'] = 'Email 格式有误！';
			break;
			
			case -5:
				$_SESSION['libmill_msg'] = 'Email 不允许注册！';
			break;
			
			case -6:
				$_SESSION['libmill_msg'] = '该 Email 已经被注册！';
			break;
		}
	}
	if($ok){
		setcookie('libmill', uc_authcode($username,'ENCODE'), time()+3600*24*30);
		$_SESSION['libmill_url'] = $system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=index';
		$sql = "insert into somethingmusic_user set `id`='$uid', `time`='".$_SERVER['REQUEST_TIME']."', `qq`='0',`libmill`='1',`sina`='0',`sina_token`='',realname='',xi='',classname='',nick='',artist='',style='',sign=''";
		$conn->Execute($sql)or die('sync error');
		redirect();
	} else {
		$_SESSION['libmill_url'] = $system[0]['yuming'].'reg.php?skin='.$_COOKIE['libmill_skin'];
		redirect();
	}
}
$smarty->display('reg.html');
$conn->close();
?>