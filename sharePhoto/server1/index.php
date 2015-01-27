<?php
$root = realpath(dirname(__FILE__)).'/';
require $root.'function.php';

foreach($GLOBALS['_GET'] as $key => $get){
	$action = filter($key);
	require $root.$action.'.php';
	break;
}
?>