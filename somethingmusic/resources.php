<?php 
include "head.php";
header('http-equiv="Content-Type" content="text/html; charset=utf-8');
switch($template)
{
	case 'deafault':$num=16;break;
	case 'gray':$num=15;break;
}
//获取广告
$advertisement=get_my_array($conn,'select * from `somethingmusic_ad` where `show`=1 order by `id` desc');
for($i=0;$i<count($advertisement);$i++)
{
	$advertisement[$i]['show_id']=$i+1;
}
$smarty->assign('ad',$advertisement);

//获取风格
$category=get_my_array($conn,'select distinct category as smallcategory from `somethingmusic_album` order by `id` asc');
$smarty->assign('category',$category);

//获取地区
$area=get_my_array($conn,'select distinct bigcategory from `somethingmusic_bigcategory` order by `id` asc');
for($i=0;$i<count($area);$i++)
{
	$area[$i]['id']=$i+1;
}
$smarty->assign('area',$area);

//获取时间
$time=get_my_array($conn,'select distinct SUBSTRING(TIME,1,4) from `somethingmusic_album` order by `time` desc');
$smarty->assign('time',get_time($time));

$action=clean($_GET['action']);
$year=clean($_GET['year']);
if($action=='year')
{
	$smarty->assign('action',substr($_SERVER['REQUEST_URI'],stripos($_SERVER['REQUEST_URI'],'&year=')+6,4));
}
elseif(ord($action)>180)
{
	$smarty->assign('action',$action);
}
else
{
	$smarty->assign('action',get_action($_SERVER['REQUEST_URI']));
}
//全部
if($action=='all')
{
	$letter=$_GET['letter'];
	$letter_ascii=ord($letter);
	if(isset($letter)&&$letter_ascii>=65)
	{
		$smarty->assign('action',$letter);
		$album=showpage1($conn,"somethingmusic_album left join somethingmusic_letter on somethingmusic_letter.cBegin and somethingmusic_letter.cEnd and somethingmusic_letter.f_PY='".$letter."'",'*',$num,'ASCII(ARTIST)='.$letter_ascii.' or ORD(CONVERT( ARTIST USING gbk )) between somethingmusic_letter.cBegin and somethingmusic_letter.cEnd','all&letter='.$letter);
	}
	else
	{
		$album=showpage2($conn,'somethingmusic_album','*',$num,'action=all','id');
	}
}

//按专辑热度排序
elseif($action=='hot')
{
	$album=showpage2($conn,'somethingmusic_album','*',$num,'action=hot','hit');
}

//按发行时间排序
elseif($action=='time')
{
	$album=showpage2($conn,'somethingmusic_album','*',$num,'action=time','time');
}

//按风格查询
elseif(strlen($action)>=6)
{
	$album=showpage1($conn,'somethingmusic_album','',$num,"`category`='".$action."'",$action);
}

//按地区查询
elseif($action==1)
{
	$album=showpage1($conn,'somethingmusic_album','',$num,"`area`='".'港台'."'",$action);
}
elseif($action==2)
{
	$album=showpage1($conn,'somethingmusic_album','',$num,"`area`='".'大陆'."'",$action);
}
elseif($action==3)
{
	$album=showpage1($conn,'somethingmusic_album','',$num,"`area`='".'欧美'."'",$action);
}
elseif($action==4)
{
	$album=showpage1($conn,'somethingmusic_album','',$num,"`area`='".'日韩'."'",$action);
}
elseif($action==5)
{//其他暂时不行
	$album=showpage1($conn,'somethingmusic_album','',$num,"`area`='".'%%'."'",$action);
}

//按语种查询
elseif($action=='a')
{
	//$album=language($conn,'somethingmusic_album','*',16,'粤语');
	$album=showpage1($conn,'somethingmusic_album','',$num,"`language` like '".'%粤语%'."'",'a');
}
elseif($action=='b')
{
	$album=showpage1($conn,'somethingmusic_album','',$num,"`language` like '".'%国语%'."'",'b');
}
elseif($action=='c')
{
	$album=showpage1($conn,'somethingmusic_album','',$num,"`language` like '".'%英语%'."'",'c');
}
elseif($action=='d')
{
	$album=showpage1($conn,'somethingmusic_album','',$num,"`language` like '".'%日语%'."'",'d');
}
elseif($action=='e')
{
	$album=showpage1($conn,'somethingmusic_album','',$num,"`language` like '".'%韩语%'."'",'e');
}
//按年份
elseif($year>=60&&$action=='year')
{
	if($year==2005)
	{
		$album=showpage1($conn,'somethingmusic_album','',$num,"SUBSTRING(TIME,1,4)<='".$year."' and SUBSTRING(TIME,1,4)>='2000'",'year&year='.$year,'time');
	}
	elseif($year==90)
	{
		$album=showpage1($conn,'somethingmusic_album','',$num,"SUBSTRING(TIME,1,4)<='1999' and SUBSTRING(TIME,1,4)>='1990'",'year&year='.$year,'time');
	}
	elseif($year==80)
	{
		$album=showpage1($conn,'somethingmusic_album','',$num,"SUBSTRING(TIME,1,4)<='1989' and SUBSTRING(TIME,1,4)>='1980'",'year&year='.$year,'time');
	}
	elseif($year==70)
	{
		$album=showpage1($conn,'somethingmusic_album','',$num,"SUBSTRING(TIME,1,4)<='1979' and SUBSTRING(TIME,1,4)>='1970'",'year&year='.$year,'time');
	}
	elseif($year==60)
	{
		$album=showpage1($conn,'somethingmusic_album','',$num,"SUBSTRING(TIME,1,4)<='1969' and SUBSTRING(TIME,1,4)>='1960'",'year&year='.$year,'time');
	}
	else
	{
		$album=showpage1($conn,'somethingmusic_album','',$num,"SUBSTRING(TIME,1,4)='".$year."'",'year&year='.$year,'time');
	}
}
elseif($action=='f')
{//其他暂时不行
	//
}
else
{
	header('Location:'.BASE_URL);
}

if(count($album['page_arr'])<=0)
{
	$smarty->assign('album','0');
}
else
{
	$smarty->assign('album',$album['page_arr']);
}

$smarty->assign('a_z',$a_to_z);
$smarty->assign('page',$album['page_infor']);
	
$smarty->display('resources.html');
$conn->close();

function get_action($url)
{
	$start=stripos($url,'action=');
	if(stripos($url,'&')!='')
	{
		$content=substr($url,$start+7,stripos($url,'&')-$start-7);
	}
	else
	{
		$content=substr($url,$start+7);
	}
	return $content;
}
?>