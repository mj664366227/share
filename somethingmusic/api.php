<?php
action();
/*接口*/
function action()
{
	$action=$_GET['action'];
	$mod=$_GET['mod'];
	if($action&&$mod)
	{
		include 'lib/controller.php';
		include 'class/'.$action.'.php';
	}
	if(!class_exists($action)) die();
	$f = new $action;
	if(!method_exists($f, $mod)) die();
	$f->$mod();	
}
?>