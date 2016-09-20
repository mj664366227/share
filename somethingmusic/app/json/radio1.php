<?php
header("Content-type: application/json");
include '../../config.php';
$c=$_GET['channel'];//获取用户选择的频道

if($c!='')
{
	$sql="select `somethingmusic_musicplay`.`albumid`,
	              `somethingmusic_album`.`picture`,
				  `somethingmusic_musicplay`.`title`,
				  `somethingmusic_album`.`artist`,
				  `somethingmusic_musicplay`.`url` 
				  from `somethingmusic_musicplay` left join `somethingmusic_album` 
		      on somethingmusic_musicplay.albumid=somethingmusic_album.id 
			  where `".$c."`='1' order by rand() limit 1";
	
	$radio=get_my_array($conn,$sql);
			  
	$count=count($radio);
	for($i=0;$i<$count;$i++)
	{
		for($ii=0;$ii<5;$ii++)
		{
			unset($radio[$i][$ii]);
		}
		$radio[$i]['artist']=urlencode($radio[$i]['artist']);
		$radio[$i]['title']=urlencode($radio[$i]['title']);
		$radio[$i]['url']=urlencode($radio[$i]['url']);
		$radio[$i]['picture']=urlencode(BASE_URL.'thumbs/thumbs/cache/w190h190/admin/'.$radio[$i]['picture']);
	}
	//print_r($radio);
	echo urldecode(json_encode($radio)); 
}
else
{
	die('There is no channel selected!');
}
?>