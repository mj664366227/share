<?php
header('Content-type:text/xml;charset=utf-8');
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
			  where `".$c."`='1' order by rand() limit 10";
	
	$radio=get_my_array($conn,$sql);
			  
	echo '<?xml version="1.0" encoding="utf-8"?><radio>';
	foreach($radio as $r)
	{
		echo '<song>
	      
		   <picture>'.BASE_URL.'thumbs/thumbs/cache/w190h190/admin/'.$r['picture'].'</picture>
		   <title>'.trim(clean1($r['title'])).'</title>
		   <artist>'.trim($r['artist']).'</artist>
		   <url>'.$r['url'].'</url>
	       </song>';
	}
	echo '</radio>';/* <albumid>'.$r['albumid'].'</albumid>*/
}
else
{
	die('There is no channel selected!');
}
?>