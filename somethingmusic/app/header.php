<?php 
$file=substr($_SERVER["REQUEST_URI"],stripos($_SERVER["REQUEST_URI"],'app')+4);
$title=str_replace('.php','',$file);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="飞车坊,华汽飞车坊,somethingmusic,lovemusic,流行音乐,小众音乐,摇滚音乐,经典老歌,爱音乐,音乐电台,jplayer,有内涵的音乐" />
<meta name="description" content="somethingmusic音乐电台,最新最快最全的音乐。" />
<link rel="shortcut icon" href="../favicon.ico"/>
<link rel="stylesheet" href="../templates/template/deafault/css/css.css" type="text/css" />
<!--[if IE]><link rel="stylesheet" href="../templates/template/deafault/css/all_ie.css" type="text/css" /><![endif]-->
<!--[if IE 6]><link rel="stylesheet" href="../templates/template/deafault/css/ie6.css" type="text/css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="../templates/template/deafault/css/ie7.css" type="text/css" /><![endif]-->
<title><?=$title?> - API文档 - somethingmusic音乐电台 - 我们一直在努力！</title>
</head>
<body>
<!--初始化播放器-->
<div id="jquery_jplayer"></div>
<!--头部-->
<div class="header"> <a href="../" title="somethingmusic音乐电台"><img src="../templates/template/deafault/images/logo.png" width="107" height="35" alt="logo" border="0"/></a> </div>
<!--导航栏-->
<div class="nav">
  <ul>
    <li><a href="../" title="网站首页">首页</a></li>
    <li><a href="../radio.php" title="音乐电台" target="_blank">电台</a></li>
    <li><a href="../resources.php?action=all" title="本站资源">资源</a></li>
    <li><a href="../musician.php?action=all" title="音乐人介绍">音乐人</a></li>
    <li><a href="../introduce.php?action=all" title="音乐推荐文章">推荐</a></li>
  </ul>
  <!--搜索栏-->
  <div class="search">
    <form action="../search.php?action=search" method="post">
      <input type="text" class="search_input" id="search_input" name="search_input" onclick="value=''" autocomplete="off"/>
      <div class="submit" title="搜！">
        <input type="image" src="../templates/template/deafault/images/search.png" width="18" height="18" id="search_submit"/>
      </div>
    </form>
  </div>
</div>
