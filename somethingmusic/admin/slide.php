<?php
//首页幻灯片
session_start();
include "config.php";//载入系统配置文件
$action = $_GET['action'];
if(empty($action)){
	$action = 'index';
}
$slide = new slide($smarty, $conn);
$slide->$action();

class slide{
	private $smarty;
	private $conn;
	/*构造函数*/
	public function __construct($smarty, $conn){
		if(empty($_SESSION['login'])){
			header("location:index.php");
		}
		$this->smarty = $smarty;
		$this->conn = $conn;
	}
	
	/*页面显示*/
	public function index(){
		$album=showpage($this->conn,'somethingmusic_music_slide','id,url,name','3','','');
		$this->smarty->assign('pic', $album['page_arr']);
		$this->smarty->assign('page', $album['page_infor']);
		$this->smarty->display('slide.html');
	}
	
	/*提交数据*/
	public function post(){
		$title=trim(addslashes($_POST['title']));
		if(empty($title)){
			tips('标题不能为空！');
			die();
		}
		$url=upload_pic();
		$this->conn->Execute("insert into `somethingmusic_music_slide` (`url`,`name`) values ('$url','$title')")or die('error!');
		header('Location:http://lovemusic.cc/thumbs/?thumb=admin/'.$url.'&w=930&h=300&q=70');
	}
	
	/*删除*/
	public function delete(){
		$id=intval($_GET['id']);
		$this->conn->Execute("delete from somethingmusic_music_slide where `id`='$id'");
		tips('删除成功！');
	}
}
?>