<?php
header('Content-type:text/xml;charset=utf-8');
include '../../config.php';

//此文件调用格式:http://lovemusic.cc/mobile/mobile_get_album.php?id=要查询的专辑id号，如id不存在则报错

$id=$_GET['id'];
if($id!='')
{
	$sql="select * from `somethingmusic_album`,`somethingmusic_musicplay` 
	      where `somethingmusic_album`.`id`=`somethingmusic_musicplay`.`albumid` 
		  and `somethingmusic_album`.`id`='$id'
		  order by `pid` asc";
		  
	/*
	字段解释：
	id：           当前专辑的id号
	category：     当前专辑的风格
	title：        当前专辑的名称
	artist：       当前专辑的歌手
	time：         当前专辑的发行时间
	company：      唱片公司
	area：         来源地区
	language：     歌曲语种
	text：         专辑介绍
	hit：          专辑点击率
	picture：      专辑封面地址，图片的大小是190 x 190，图片访问图片缓存，地址在demo中，BASE_URL是返回当前域名
	url：          歌曲地址
	pid：          歌曲所在专辑的序号
	have_played：  试听次数
	download：     下载次数
	title：        歌曲名字
	*/
	$album=get_my_array($conn,$sql) or die('select error!');
	echo '<?xml version="1.0" encoding="utf-8"?><album>';
	
	//显示专辑信息       所有字段已经列出，具体如何调用看下面的demo,demo中的专辑介绍把所有html标签都过滤掉了
	echo '<detail>
	      <category>'.$album[0]['category'].'</category>
		  <title>'.trim(clean1($album[0]['title'])).'</title>
		  <artist>'.trim(clean1($album[0]['artist'])).'</artist>
		  <time>'.$album[0]['time'].'</time>
		  <company>'.trim($album[0]['company']).'</company>
		  <area>'.$album[0]['area'].'</area>
		  <language>'.trim(clean1($album[0]['language'])).'</language>
	      <picture>'.BASE_URL.'thumbs/thumbs/cache/w190h190/admin/'.trim(clean1($album[0]['picture'])).'</picture>
		  <text>'.trim(clean1($album[0]['text'])).'</text>
	      </detail>';
		  
	//显示专辑曲目列表
	echo '<songlist>';
	foreach($album as $a)
	{
		echo '<song pid="'.$a['pid'].'" url="'.$a['url'].'" have_played="'.$a['have_played'].'" download="'.$a['download'].'">'.$a['title'].'</song>';
	}
	echo '</songlist></album>';
	
	//demo完毕
}
else
{
	die('Access Dany!');
}
?>