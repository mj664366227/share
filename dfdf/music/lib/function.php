<?php
/*消息提示函数*/
function tips($str)
{
	echo '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><link rel="stylesheet" href="templates/template/css.css" type="text/css" /></head><body><div class="main_right"><div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>消息提示</a></div><table cellspacing="0"><tr><td align="center">'.$str.'<br><br><br><br><form><input type="button" class="button" value="返  回" onclick="history.back(-1)"/></form></td></tr></table></body></html>';
}

/*分页函数*/
/*
参数：
$conn：adodb5数据连接标识
$table：数据表
$condition：查询条件(一般是*)
$perpage：每页数目
$name：列项名
$like：模糊查询
*/
function showpage($conn,$table,$condition,$perpage,$name,$like)
{
	global $template;
	if($condition=='')
	{
		$condition='*';
	}
	if($like==''&&$name!='')
	{
		$sql="select ".$condition." from ".$table." where ".$name;
	}
	if($like==''&&$name=='')
	{
		$sql="select ".$condition." from ".$table;
	}
	if($like!=""&&$name!='')
	{
		$sql="select ".$condition." from ".$table." where `".$name."` like '%".$like."%'";
	}
	$result=$conn->Execute($sql)or die('select error!count!');
	$page=$_GET['page'];//获取当前页码
	while($page==0){$page=1;}//置1
	$count=$result->RecordCount();//获取总消息条数
	$totalpage=ceil($count/$perpage);//计算总页数
	if($totalpage!=1)
	{
		if($totalpage>0)
		{
			$firstpage=$_SERVER['PHP_SELF'];//显示首页
			$str.='<a href="'.$firstpage.'" title="首页">首页</a>';
		}
		//显示上一页
		$prev=$_SERVER['PHP_SELF'].'?skin='.$template.'&page='.($page-1);
	    if($page>1)
	    {
			$str.='<a href="'.$prev.'" title="上一页">上一页</a>';
	    }
	    //显示中间
	    $start=$page-5;
	    if($start<=0)
	    {
			$start=1;
	    }
	    $end=$page+6;
	    if($end>=$totalpage)
	    {
		    $end=$totalpage+1;
	    }
	    for($i=$start;$i<$end;$i++)
	    {
		    if($page==$i)
		    {
				$str.='&nbsp;&nbsp;<font>'.$i.'</font>&nbsp;';
		    }
		    else
		    {
				$str.='<a href="'.$_SERVER['PHP_SELF'].'?skin='.$template.'&page='.$i.'" title="第'.$i.'页">'.$i.'</a>';
			}
	    }
	    //显示下一页
	    $next=$_SERVER['PHP_SELF'].'?skin='.$template.'&page='.($page+1);
	    if($page<$totalpage)
	    {
			$str.='<a href="'.$next.'" title="下一页">下一页</a>';
	    }
		if($totalpage>0)
		{
			$lastpage=$_SERVER['PHP_SELF'].'?skin='.$template.'&page='.$totalpage;//显示最后一页
			$str.='<a href="'.$lastpage.'" title="尾页">尾页</a>';//显示尾页
		}
	}
	$startpage=($page-1)*$perpage;
	$endpage=$page*$perpage;
	$sql="select ".$condition." from ".$table." order by `id` desc limit ".($page-1)*$perpage.",".$perpage;
	if($like!=""&&$name!='')
	{
		$sql="select ".$condition." from ".$table." where `".$name."` like '%".$like."%'"." order by `id` desc limit ".($page-1)*$perpage.",".$perpage;
	}
	$result=$conn->Execute($sql)or die('select error!showpage');
	$arr=$result->GetArray();
	$page=array(/*分页*/'page_infor'=>$str,/*查询结果*/'page_arr'=>$arr);
	return $page;
}

function showpage1($conn,$table,$condition,$perpage,$name,$action,$orderby='')
{
	$template = $_COOKIE['libmill_skin'];
	if($condition=='')
	{
		$condition='*';
	}
	if($name!='')
	{
		$sql="select ".$condition." from ".$table." where ".$name;
		//die($sql);
	}
	$result=$conn->Execute($sql)or die('select error!count!');
	$page=$_GET['page'];//获取当前页码
	while($page==0){$page=1;}//置1
	$count=$result->RecordCount();//获取总消息条数
	$totalpage=ceil($count/$perpage);//计算总页数
	if($totalpage!=1)
	{
		if($totalpage>0)
		{
			$firstpage=$_SERVER['PHP_SELF'].'?skin='.$template.'&action='.$action;//显示首页
			$str.='<a href="'.$firstpage.'" title="首页">首页</a>';
		}
		//显示上一页
		$prev=$_SERVER['PHP_SELF'].'?skin='.$template.'&action='.$action.'&page='.($page-1);
	    if($page>1)
	    {
			$str.='<a href="'.$prev.'" title="上一页">上一页</a>';
	    }
	    //显示中间
	    $start=$page-5;
	    if($start<=0)
	    {
			$start=1;
	    }
	    $end=$page+6;
	    if($end>=$totalpage)
	    {
		    $end=$totalpage+1;
	    }
	    for($i=$start;$i<$end;$i++)
	    {
		    if($page==$i)
		    {
				$str.='&nbsp;&nbsp;<font>'.$i.'</font>&nbsp;';
		    }
		    else
		    {
				$str.='<a href="'.$_SERVER['PHP_SELF'].'?skin='.$template.'&action='.$action.'&page='.$i.'" title="第'.$i.'页">'.$i.'</a>';
			}
	    }
	    //显示下一页
	    $next=$_SERVER['PHP_SELF'].'?skin='.$template.'&action='.$action.'&page='.($page+1);
	    if($page<$totalpage)
	    {
			$str.='<a href="'.$next.'" title="下一页">下一页</a>';
	    }
		if($totalpage>0)
		{
			$lastpage=$_SERVER['PHP_SELF'].'?skin='.$template.'&action='.$action.'&page='.$totalpage;//显示最后一页
			$str.='<a href="'.$lastpage.'" title="尾页">尾页</a>';//显示尾页
		}
	}
	$startpage=($page-1)*$perpage;
	$endpage=$page*$perpage;
	if($orderby!='')
	{
		$sql="select ".$condition." from ".$table." where ".$name." order by `".$orderby."` desc limit ".($page-1)*$perpage.",".$perpage;
	}
	else
	{
		$sql="select ".$condition." from ".$table." where ".$name." order by `id` desc limit ".($page-1)*$perpage.",".$perpage;
	}
	$result=$conn->Execute($sql)or die('select error!showpage');
	$arr=$result->GetArray();
	$page=array(/*分页*/'page_infor'=>$str,/*查询结果*/'page_arr'=>$arr);
	return $page;
}

function showpage2($conn,$table,$condition,$perpage,$action,$setname='')
{
	global $template;
	$sql="select ".$condition." from ".$table;
	//echo ($sql);
	$result=$conn->Execute($sql)or die('select error!count!');
	$page=$_GET['page'];//获取当前页码
	while($page==0){$page=1;}//置1
	$count=$result->RecordCount();//获取总消息条数
	$totalpage=ceil($count/$perpage);//计算总页数
	if($totalpage!=1)
	{
		if($totalpage>0)
		{
			$firstpage=$_SERVER['PHP_SELF'].'?skin='.$template.'&'.$action;//显示首页
			$str.='<a href="'.$firstpage.'" title="首页">首页</a>';
		}
		//显示上一页
		$prev=$_SERVER['PHP_SELF'].'?skin='.$template.'&'.$action.'&page='.($page-1);
	    if($page>1)
	    {
			$str.='<a href="'.$prev.'" title="上一页">上一页</a>';
	    }
	    //显示中间
	    $start=$page-5;
	    if($start<=0)
	    {
			$start=1;
	    }
	    $end=$page+6;
	    if($end>=$totalpage)
	    {
		    $end=$totalpage+1;
	    }
	    for($i=$start;$i<$end;$i++)
	    {
		    if($page==$i)
		    {
				$str.='&nbsp;&nbsp;<font>'.$i.'</font>&nbsp;';
		    }
		    else
		    {
				$str.='<a href="'.$_SERVER['PHP_SELF'].'?skin='.$template.'&'.$action.'&page='.$i.'" title="第'.$i.'页">'.$i.'</a>';
			}
	    }
	    //显示下一页
	    $next=$_SERVER['PHP_SELF'].'?skin='.$template.'&'.$action.'&page='.($page+1);
	    if($page<$totalpage)
	    {
			$str.='<a href="'.$next.'" title="下一页">下一页</a>';
	    }
		if($totalpage>0)
		{
			$lastpage=$_SERVER['PHP_SELF'].'?skin='.$template.'&'.$action.'&page='.$totalpage;//显示最后一页
			$str.='<a href="'.$lastpage.'" title="尾页">尾页</a>';//显示尾页
		}
	}
	$startpage=($page-1)*$perpage;
	$endpage=$page*$perpage;
	if($setname=='')
	{
		$sql="select ".$condition." from ".$table." order by `id` desc limit ".($page-1)*$perpage.",".$perpage;
	    //echo ($sql);
	}
	else
	{
		$sql="select ".$condition." from ".$table." order by `".$setname."` desc limit ".($page-1)*$perpage.",".$perpage;
	}
	$result=$conn->CacheExecute($sql)or die('select error!showpage');
	$arr=$result->GetArray();
	$page=array(/*分页*/'page_infor'=>$str,/*查询结果*/'page_arr'=>$arr);
	return $page;
}

/*图片上传函数*/
function upload_pic()
{
	$file_path="upload/";//设置文件路径
	$type=$_FILES['pic']['type'];//获取文件类型
	if($type=='image/pjpeg'||$type=='image/jpg'||$type=='image/jpeg')
	{
		$type='.jpeg';
	}
	else if($type=='image/x-png'||$type=='image/png')
	{
		$type='.png';
	}
	else
	{
		tips('图片格式错误！');
		exit();
	}
	$tmp_name=$_FILES['pic']['tmp_name'];
	//生成文件名
	$file_name=md5(microtime()).'_'.rand(10000000,99999999);
	$name=$file_path.$file_name.$type;
	move_uploaded_file($tmp_name,$name)or die('upload file error!');
	return $name;
}

/*删除函数*/
function delete($connection,$table,$coloum,$where,$andwhere='')
{
	$conn=$connection;
	$sql="delete from `".$table."` where `".$coloum."`='".$where."' ".$andwhere;
	$conn->Execute($sql)or die('delete error!');
	tips('删除成功！');
}

/*字符串过滤函数*/
function clean($str)
{
	$str=strip_tags($str);
	$str=preg_replace("/\s|\`|\~|\!|\@|\#|\$|\%|\^|\&|\*|\'|\"|\*|\-|\+/", "", $str);
	$str=str_replace('nbsp;','',$str);
	return $str;
}

function clean1($str)
{
	$str=strip_tags($str);
	$str=preg_replace("/\s|\`|\~|\!|\@|\#|\$|\%|\^|\&|\*|\'|\"|\*|\-|\+/", " ", $str);
	$str=str_replace('nbsp;','',$str);
	return $str;
}

/*获取数组*/
function get_my_array($connection,$sql)
{
	$conn=$connection;
	$res=$conn->Execute($sql) or die('the error sql is:'.$sql);
	$result=$res->GetArray();
	return $result;
}

/*用于导航栏，获取活动的链接*/
function get_active($url)
{
	$start=strripos($url,'/');
	$end=strlen(strstr($url,'.'));
	$content=substr($url,$start+1,-$end);
	if($content==''){$content='index';}
	return $content;
}

/*前台消息提示函数*/
function my_message($c,$s,$str,$type,$other='')
{
	$conn=$c;
	$smarty=$s;
	if($other!='')
	{
		$smarty->assign('sss',$other);  
	}
	$system=get_my_array($conn,'select * from `somethingmusic_system`');
	$smarty->assign('active',get_active($_SERVER['REQUEST_URI'])); 
	$smarty->assign('system',$system);  
	$smarty->assign('str',$str);  
	$smarty->assign('type',$type);                               
	$smarty->display('error.html');
	$conn->close();
}

/*获取时间函数*/
function get_time($time)
{
	for($i=0;$i<count($time);$i++)
	{
		if($time[$i][0]<2005)
		{
			break;
		}
	}
	for($a=0;$a<$i;$a++)
	{
		if($time[$a][0]==2005)
		{
			$result[$a]='00-05';
		}
		else
		{
			$result[$a]=$time[$a][0];
		}
	}
	return $result;
}

/*更新各类统计*/
function sth_update($connection,$colomn,$id)
{
	$conn=$connection;
	$sql='update `somethingmusic_musicplay` set '.$colomn.'='.$colomn.'+1 where `id`='.$id;
	$conn->Execute($sql)or die('the error sql is:'.$sql);
}

/*音乐电台*/
function radio_by_category($connection,$category)
{
	$conn=$connection;
	$sql="select * from `somethingmusic_musicplay` left join `somethingmusic_album` on somethingmusic_musicplay.albumid=somethingmusic_album.id where `somethingmusic_musicplay`.`category`='".$category."' order by rand() limit 1";
	$result=$conn->Execute($sql)or die ('the error sql is:'.$sql);
	return $result->GetArray();
}

/*获取最近4个月的时间*/
function get_recent_3_month()
{
	$year=date('Y');
	$month=date('m');
	$temp=$month-3;
	if($temp==-2)
	{
		$sql="`addtime` like '%".$year.'-'.$month."%' or `addtime` like '%".($year-1).'-'."12%' or `addtime` like '%".($year-1).'-'."11%'";
	}
	elseif($temp==-1)
	{
		$sql="`addtime` like '%".$year.'-'.$month."%' or `addtime` like '%".$year.'-'.($month-1)."%' or `addtime` like '%".($year-1).'-'."12%'";
	}
	elseif($temp==0)
	{
		$sql="`addtime` like '%".$year.'-'.$month."%' or `addtime` like '%".$year.'-'.($month-1)."%' or `addtime` like '%".$year.'-'.($month-2)."%'";
	}
	elseif($temp>=1)
	{
		$sql="`addtime` like '%".$year.'-'.$month."%' or `addtime` like '%".$year.'-'.($month-1)."%' or `addtime` like '%".$year.'-'.($month-2)."%'";
	}
	return $sql;
}

function set_true($connection,$id,$colomn)
{
	$conn=$connection;
	$conn->Execute('update `somethingmusic_musicplay` set `'.$colomn.'`=1 where `id`='.$id)or die('error');
}

function set_false($connection,$id,$colomn)
{
	$conn=$connection;
	$conn->Execute('update `somethingmusic_musicplay` set `'.$colomn.'`=0 where `id`='.$id)or die('error');
}

/*A-Z*/
$a_to_z=array('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');

/*加密函数*/
function secret($str)
{
	return sha1(md5(base64_encode($str),true));
}

/*计算等级*/
function level($score)
{
	return ceil(0.07*$score);
}

//显示等级图标
function show_level($level)
{
	$crown=floor($level/64);
	$level=$level%64;
	$sun=floor($level/16);
	$level=$level%16;
	$moon=floor($level/4);
	$star=$level%4;
	while($crown>0)
	{
		$award[]='crown';
		$crown-=1;
	}
	while($sun>0)
	{
		$award[]='sun';
		$sun-=1;
	}
	while($moon>0)
	{
		$award[]='moon';
		$moon-=1;
	}
	while($star>0)
	{
		$award[]='star';
		$star-=1;
	}
	return $award;
}

/*文件夹排序*/
function folder_sort($array)
{
	foreach($array as $a)
	{
		$count=count(explode('.',$a));
		if($count==1)
		{
			$return[]=$a.$count;
		}
	}
	foreach($array as $a)
	{
		$count=count(explode('.',$a));
		if($count==2)
		{
			$return[].=$a.$count;
		}
	}
	return $return;
}

/*自定义截取*/
function cut($string,$start_cut)
{
	$i=strripos($string,$start_cut);
	$len=strlen($start_cut);
	return substr($string,0,$i+$len);
}

/**
 * 返回文件大小
 * @param $size
 */
function size($size)
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

//重定向
function redirect(){
	header('Location:'.REDIRECT);
}

function _echo($value){
	echo 'debug...<br/><pre>';print_r($value);
}
?>