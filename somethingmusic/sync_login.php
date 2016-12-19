<?php
include 'config.php';

if(!$_COOKIE['libmill'] || !$_COOKIE['qq'] || !$_SESSION['weibo_token']){
	header('location:'.$system[0]['yuming']);
}

//同步登录
$uid = $_GET['uid'];
if(!$uid){
	header('location:'.$system[0]['yuming']);
}

$a = $_GET['a'];
$qq = $libmil = $sina = 0;
if($a=='qq'){
	$qq = 1;
}
if($a=='libmill'){
	$libmil = 1;
}
if($a=='sina'){
	$sina = 1;
	$t = $_GET['t'];
}

$sql = "replace into somethingmusic_user set `id`='$uid', `time`='".$_SERVER['REQUEST_TIME']."', `qq`='$qq',`libmill`='$libmill',`sina`='$sina',`sina_token`='$sina_token'";
$conn->Execute($sql)or die('sync error');

//导航到后台
echo 'login...';
echo '<meta http-equiv="refresh" content="1;url='.$system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=index'.'"/>';
?>