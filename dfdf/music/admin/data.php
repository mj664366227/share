<?php
//数据统计
session_start();
include "config.php";//载入系统配置文件
$login=$_SESSION['login'];
$action=clean($_GET['action']);
if(!$action)
{
	die('Access Dein!');
}
if($login=="")
{
	header("location:index.php");
}
else
{
	switch($action)
	{
		//统计专辑
		case 'album':
		$type=$_GET['type'];
		switch($type)
		{
			case 'category':
			$sql="select category,sum(hit) as click,count(id) as total from somethingmusic_album group by category";
			$rs=get_my_array($conn,$sql);
			foreach($rs as $r)
			{
				$count+=$r['click'];
			}
			foreach($rs as $r)
			{
				$data.="['".$r['category']."(".$r['total'].")',".round($r['click']/$count*100,2).'],';
			}
			break;
			
			case 'area':
			$sql="select area,sum(hit) as click,count(id) as total from somethingmusic_album group by area";
			$rs=get_my_array($conn,$sql);
			foreach($rs as $k=>$r)
			{
				$pos=strpos($r['area'],'&nbsp;');
				if($pos){unset($rs[$k]);}
				$count+=$r['click'];
			}
			foreach($rs as $r) $rs1[] = $r;
			foreach($rs1 as $k=>$r)
			{
				if(urldecode(str_replace('%C2%A0','',urlencode($rs1[$k]['area'])))==urldecode(str_replace('%C2%A0','',urlencode($rs1[$k+1]['area']))))
				{
					$rs1[$k]['total']+=$rs1[$k+1]['total'];
					$rs1[$k]['click']+=$rs1[$k+1]['click'];
					unset($rs1[$k+1]);
				}
				else
				{
					$rs1[$k]['area']=$rs1[$k]['area'];
					$rs1[$k]['total']=$rs1[$k]['total'];
					$rs1[$k]['click']=$rs1[$k]['click'];
				}
			}
			foreach($rs1 as $r)
			{
				if($r['area'])
				$data.="['".$r['area']."(".$r['total'].")',".round($r['click']/$count*100,2).'],';
			}
			break;
			
			case 'language':
			$sql="select language,sum(hit) as click,count(id) as total from somethingmusic_album group by language";
			$rs=get_my_array($conn,$sql);
			foreach($rs as $k=>$r)
			{
				$rs[$k]['language']=str_replace(' ','',str_replace('&nbsp;','',$rs[$k]['language']));
				$pos=substr_count($r['language'],'语');
				if($pos>1) unset($rs[$k]);
				$count+=$r['click'];
			}
			foreach($rs as $r) $rs1[] = $r;
			foreach($rs1 as $k=>$r)
			{
				if(urldecode(str_replace('%C2%A0','',urlencode($rs1[$k]['language'])))==urldecode(str_replace('%C2%A0','',urlencode($rs1[$k+1]['language']))))
				{
					$rs1[$k]['total']+=$rs1[$k+1]['total'];
					$rs1[$k]['click']+=$rs1[$k+1]['click'];
					unset($rs1[$k+1]);
				}
				else
				{
					$rs1[$k]['language']=$rs1[$k]['language'];
					$rs1[$k]['total']=$rs1[$k]['total'];
					$rs1[$k]['click']=$rs1[$k]['click'];
				}
			}
			foreach($rs1 as $r)
			{
				if($r['language'])
				$data.="['".$r['language']."(".$r['total'].")',".round($r['click']/$count*100,2).'],';
			}
			break;
			
			case 'year':
			$sql="select substring(time,1,4) as time,sum(hit) as click,count(id) as total from somethingmusic_album group by substring(time,1,4)";
			$rs=get_my_array($conn,$sql);
			$time=date('Y',$_SERVER['REQUEST_TIME'])-6;
			$i=0;
			foreach($rs as $r)
			{
				if($r['time']<=$time)
				{
					$i-=1;
					$rs1[$i]['time']=$time.'之前';
					$rs1[$i]['total']=+$r['total'];
					$rs1[$i]['click']=+$r['click'];
				}
				else
				{
					$rs1[$i]['time']=$r['time'];
					$rs1[$i]['total']=$r['total'];
					$rs1[$i]['click']=$r['click'];
					
				}
				$i+=1;
				$count+=$r['click'];
			}
			foreach($rs1 as $r)
			{
				$data.="['".$r['time']."(".$r['total'].")',".round($r['click']/$count*100,2).'],';
			}
			break;
		}
		$title='不同'.substr(menu_type($type),3).'各专辑点击率';
		$smarty->assign('type',$type);
		$smarty->assign('menu',menu_type());
		$smarty->assign('title',$title);
		$smarty->assign('data',substr($data,0,-1));
		$smarty->display('data.album.html');
		break;
		
		//统计电台
		case 'radio':
		$sql = 'select name , count  ,en_name , name from somethingmusic_radio_channel_set';
		$channel = get_my_array($conn, $sql);
		foreach($channel as $c)
		{
			if($c['en_name'] != 'new')
			{
				$sql = 'select count('.$c['en_name'].') as '.$c['en_name'].' from somethingmusic_musicplay';
				$sql.=' where `'.$c['en_name'].'`=\'1\'';
				$res = get_my_array($conn, $sql);
				$result[$c['en_name']] = $res[0][$c['en_name']];
				
			}
		}
		$i = 0;
		foreach($channel as $r)
		{
			foreach($result as $k => $rr)
			{
				if($r['en_name'] == $k)
				{
					$output[$i]['name'] = $r['name'];
					$output[$i]['click'] = $r['count'];
					$output[$i]['count'] = $rr;
				}
			}
			$i += 1;
		}
		$smarty->assign('output',$output);
		$smarty->display('data.radio.html');
		break;
		
		//电台收听走势
		case 'go':
		$st=$_GET['st'];
		$et=$_GET['et'];
		$channel=$_GET['channel'];
		if(!$st)
		{
			$st=date('Y-m-d',$_SERVER['REQUEST_TIME']-24*3600*7);
		
		}
		if(!$et)
		{
			$et=date('Y-m-d',$_SERVER['REQUEST_TIME']);
		}
		//总的
		$sql='select `show`,`en_name`,`name` from somethingmusic_radio_channel_set order by `id` asc';
		$radio=get_my_array($conn,$sql);
		foreach($radio as $radio_name)
		{
			$array[$radio_name['en_name']]=$radio_name['name'];
		}
		if(!$channel || $channel=='all')
		{
			$channel='all';
			$sql="select sum(count) as count ,time from somethingmusic_radio where 
			  time>='".$st."'
			  and time<='".$et."'
			  group by time";
		}
		else
		{
			$sql="select sum(count) as count ,time from somethingmusic_radio where 
			  time>='".$st."'
			  and time<='".$et."'
			  and radio_id='".$channel."'
			  group by time";
		}
		$res=get_my_array($conn,$sql);
		$this_st=strtotime($st);
		$this_et=strtotime($et);
		for($i=$this_st;$i<=$this_et;$i+=(24*3600))
		{
			$times[date('Y-m-d',$i)]['count']=0;
		}
		foreach($res as $r)
		{
			foreach($times as $k=>$t)
			{
				if($k==$r['time'])
				{
					$times[$k]['count']=$r['count'];
				}
			}
		}
		foreach($times as $k=>$t)
		{
			$time.="'".$k."',";
			if($t['count'])
			{
				$result.=$t['count'].',';
			}
			else
			{
				$result.='0,';
			}
		}
		$array['all']='总趋势';
		$result="{name:'".$array[$channel]."',data:[".substr($result,0,-1)."]}";
		if($st&&$et)
		{
			$html='&st='.$st.'&et='.$et;
		}
		$smarty->assign('st',$st);
		$smarty->assign('et',$et);
		$smarty->assign('time',substr($time,0,-1));
		$smarty->assign('result',$result);
		$smarty->assign('radio',$radio);
		$smarty->assign('channel',$channel);
		$smarty->assign('html',$html);
		$smarty->assign('en',$array[$channel]);
		$smarty->display('data.radio.go.html');
		break;
	}	
}

function menu_type($key='')
{
	$arr=array(
				'category'=>'按风格',
				'area'=>'按地区',
				'language'=>'按语种',
				'year'=>'按年份'
				);
	if(!$key)
	{
		return $arr;
	}
	else
	{
		return $arr[$key];
	}
}
?>