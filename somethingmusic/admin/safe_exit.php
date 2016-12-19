<?php
session_start();
$login=$_SESSION['login'];
session_destroy();
header("location:index.php");
?>