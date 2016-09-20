<?php
header('Content-type:text/xml;charset=utf-8');
include '../../config.php';

    //此文件调用格式:http://lovemusic.cc/mobile/mobile_get_list.php
        
    
	//第4个参数是每页大小
	
	
	$list=showpage($conn,'somethingmusic_album','*',16,'','');
	//输出列表
	
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
	*/
	
	//输出的内容在foreach里写，for example如下
	echo '<?xml version="1.0" encoding="utf-8"?><list>';
	foreach($list['page_arr'] as $a)
	{
		//example
		
		echo '<album>
		      <id>'.$a['id'].'</id>
			  <category>'.trim(clean1($a['category'])).'</category>
			  <title>'.trim(clean1($a['title'])).'</title>
		      <artist>'.trim(clean1($a['artist'])).'</artist>
			  <time>'.$a['time'].'</time>
			  <company>'.trim(clean1($a['company'])).'</company>
			  <area>'.trim(clean1($a['area'])).'</area>
			  <language>'.trim(clean1($a['language'])).'</language>
			  <text>'.trim(clean1($a['text'])).'</text>
			  <hit>'.$a['hit'].'</hit>
			  <picture>'.BASE_URL.'thumbs/thumbs/cache/w190h190/admin/'.trim(clean1($a['picture'])).'</picture>
			  <author>'.$a['author'].'</author>
		      </album>';
		
		//echo $a['artist'].'-'.$a['title'].'<br><img src=thumbs/thumbs/cache/w190h190/admin/'.$a['picture'].' width="190" height="190"/>';
	}
	
	//输出分页信息
	echo '<lastpage>';
	//echo str_replace('&nbsp;','',$list['page_infor']);
	echo substr($list['page_infor'],strrpos($list['page_infor'],'page=')+5,-27);
	echo '</lastpage></list>';

?>