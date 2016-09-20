<?php
include_once( 'config.php' );
include_once( '../config.php' );
include_once( 'saetv2.ex.class.php' );

$o = new SaeTOAuthV2( WB_AKEY , WB_SKEY );
$code_url = $o->getAuthorizeURL( WB_CALLBACK_URL );
if(!$_COOKIE["sina"]){
	header('Location:'.$code_url);
	die();
}
$_SESSION['weibo_token'] = $_COOKIE["sina"];
header('location:'.$system[0]['yuming'].'sync_login.php?a=sina&t='.$_SESSION['weibo_token']);
?>