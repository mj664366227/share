<?php		
/*所有controller的匪类*/
class controller{
	protected $smarty = NULL;
	protected $db = NULL;
	protected $uid = NULL;
	protected $username = NULL;
	protected $email = NULL;
	
	/*构造函数*/
	public function __construct()
	{
		include 'head.php';
		$this->smarty=$smarty;
		$this->db=$conn;
		$this->uid =  $uc_user_data[0];
		$this->username =  $uc_user_data[1];
		$this->email =  $uc_user_data[2];
		$this->smarty->assign('username',$this->username);
		$this->smarty->assign('email',$this->email);
		$this->first_login($this->uid);
		$sync = $this->check_sync();
		$this->smarty->assign('sync_libmill',$sync['libmill']);
		$this->smarty->assign('sync_qq',$sync['qq']);
		$this->smarty->assign('sync_sina',$sync['sina']);	
		$this->smarty->assign('libmill',$_COOKIE['libmill']);
		$this->smarty->assign('qq',$_COOKIE['qq']);
		$this->smarty->assign('sina',$_SESSION['weibo_token']);
		
		//获取广告
		$advertisement=get_my_array($conn,'select * from `somethingmusic_ad` where `show`=1 order by `id` desc limit 1');
		for($i=0;$i<count($advertisement);$i++)
		{
			$advertisement[$i]['show_id']=$i+1;
		}
		$smarty->assign('ad',$advertisement);
		
		if($_COOKIE['libmill'])
		{
			//echo 'libmill ok<br><br>';
		}
		//QQ
		if($_COOKIE['qq'])
		{
			//echo 'qq ok<br><br>';
		}
		//sina
		if(!$_SESSION['weibo_token']){
			
		} else {
			//echo 'sina ok<br><br>';
		}
		if(!$_COOKIE['libmill']&&!$_COOKIE['qq']&&!$_SESSION['weibo_token'])
		{
			$_SESSION['libmill_msg'] = '你还没有登录！快点登录吧！';
			$_SESSION['libmill_url'] = $system[0]['yuming'].'login.php?skin='.$_COOKIE['libmill_skin'];
			redirect();
		}
	}
	
	/*
	 * 检查是否有同步
	 */
	private function check_sync(){
		$rs = get_my_array($this->db,"select qq,libmill,sina from somethingmusic_user where `id`='".$this->uid."'");
		return $rs[0];
	}
	
	/*析构函数*/
	public function __destruct()
	{
		$this->smarty->display('foot.html');
		$thsi->db->close();
	}
	
	protected function page($page, $perpage, $count){
		$totalpage=ceil($count/$perpage);//计算总页数
		if($totalpage!=1)
		{
			$i = strpos($_SERVER['REQUEST_URI'], '&page=');
			if($i){
				$url = substr($_SERVER['REQUEST_URI'], 0, $i);
			} else {
				$url = $_SERVER['REQUEST_URI'];
			}
			if($totalpage>0)
			{
				$firstpage=$url;//显示首页
				$str.='<a href="'.$url.'" title="首页">首页</a>';
			}
			//显示上一页
			$prev=$url.'&page='.($page-1);
			if($page>1)
			{
				$str.='<a href="'.$prev.'" title="上一页">上一页</a>';
			}
			//显示中间
			$start=$page-3;
			if($start<=0)
			{
				$start=1;
			}
			$end=$page+4;
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
					$str.='<a href="'.$url.'&page='.$i.'" title="第'.$i.'页">'.$i.'</a>';
				}
			}
			//显示下一页
			$next=$url.'&page='.($page+1);
			if($page<$totalpage)
			{
				$str.='<a href="'.$next.'" title="下一页">下一页</a>';
			}
			if($totalpage>0)
			{
				$lastpage=$url.'&page='.$totalpage;//显示最后一页
				$str.='<a href="'.$lastpage.'" title="尾页">尾页</a>';//显示尾页
			}
		}
		return $str;
	}
	
	/**
	 * 检查是否第一次登录
	 */
	private function first_login($uid){
		$arr = explode('&',$_SERVER['REQUEST_URI']);
		if($arr[2]=='mod=info'){
			return;
		}
		$sql = "select realname,xi,classname,nick,artist,style from `somethingmusic_user` where `id`='$uid'";
		$rs = get_my_array($this->db, $sql);
		if(!$rs[0]['nick']){
			$_SESSION['libmill_msg'] = '你的个人信息尚未完善，请填上你的个人信息！';
			$_SESSION['libmill_url'] = $system[0]['yuming'].'api.php?skin='.$_COOKIE['libmill_skin'].'&action=user&mod=info&step=1';
			redirect();
		}
	}
}
?>