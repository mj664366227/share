<?php
include 'config.php';

if($_SESSION["access_token"])
{
	setcookie('qq',$_SESSION["access_token"],time()+3600*24*30);
	unset($_SESSION["access_token"]);
	header('location:'.$system[0]['yuming'].'sync_login.php?a=qq');
}
else 
{
	header('location:'.$system[0]['yuming']);
}
?>