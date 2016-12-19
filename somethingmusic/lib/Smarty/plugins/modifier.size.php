<?php
/**
返回文件大小
*/
function smarty_modifier_size($size)
{
	if($size==0)
	{
		$result=$size;
	}
	elseif($size>0&&$size<1024)
	{
		$result=$size.' Bytes';
	}
	elseif($size>=1024&&$size<1024*1024)
	{
		$result=round($size/1024,2).' KB';
	}
	elseif($size>=1024*1024&&$size<1024*1024*1024)
	{
		$result=round($size/1024/1024,2).' MB';
	}
	else
	{
		$result=round($size/1024/1024/1024,2).' GB';
	}
	return $result;
} 
?>
