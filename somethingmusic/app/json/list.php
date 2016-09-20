<?php
header("Content-type: application/json");
include '../../config.php';

	$list=showpage($conn,'somethingmusic_album','*',6,'','');
	$count=count($list['page_arr']);
	for($i=0;$i<$count;$i++)
	{
		for($ii=0;$ii<12;$ii++)
		{
			unset($list['page_arr'][$i][$ii]);
		}
		$list['page_arr'][$i]['text']=urlencode(clean($list['page_arr'][$i]['text']));
		$list['page_arr'][$i]['title']=urlencode($list['page_arr'][$i]['title']);
		$list['page_arr'][$i]['category']=urlencode($list['page_arr'][$i]['category']);
		$list['page_arr'][$i]['company']=urlencode($list['page_arr'][$i]['company']);
		$list['page_arr'][$i]['area']=urlencode($list['page_arr'][$i]['area']);
		$list['page_arr'][$i]['language']=urlencode($list['page_arr'][$i]['language']);
		$list['page_arr'][$i]['artist']=urlencode($list['page_arr'][$i]['artist']);
		$list['page_arr'][$i]['picture']=urlencode(BASE_URL.'thumbs/thumbs/cache/w190h190/admin/'.clean($list['page_arr'][$i]['picture']));
	}
	echo urldecode(json_encode($list['page_arr']));
	echo json_encode(array('lastpage'=>substr($list['page_infor'],strrpos($list['page_infor'],'page=')+5,-27)));
?>