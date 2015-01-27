<?php
/**
 * 系统管理
 */
class systemcontroller extends managecontroller{
	/**
	 * 缓存管理
	 */
	public function clearcache(){
	}

	/**
	 * 文件浏览
	 */
	public function browser(){
		$path = str_replace('.', '/', $this->get('path'));
		$dir = realpath(dirname(__FILE__).'/../../').'/'.$path;
		$dir_list = filesystem::ls($dir);
		$directory = $file = array();
		foreach($dir_list as $d){
			if(strrpos($d, '.') === false){
				$directory[] = $d;
			} else {
				$file[] = $d;
			}
		}
		view::assign('path', str_replace('/', '.', $path).'.');
		view::assign('list', array_merge($directory, $file));

	}

	//读取文件
	public function readfile(){
		$path = str_replace('.', '/', $this->get('path'));
		$filename = $this->get('filename');
		$file = realpath(dirname(__FILE__).'/../..').$path.$filename;
		view::assign('filename', $filename);
		view::assign('path', str_replace('/', '.', $path).'.');
		$filetype = explode('.', $filename);
		if($filetype[1] == 'jpg' || $filetype[1] == 'jpeg' || $filetype[1] == 'png' || $filetype[1] == 'gif' || $filetype[1] == 'bmp'){
			view::assign('picture', BASE_URL.'..'.$path.$filename);
		} elseif ($filetype[1] == 'swf'){
			view::assign('swf',  BASE_URL.'..'.$path.$filename);
		} elseif ($filetype[1] == 'pdf'){
			view::assign('pdf',  BASE_URL.'..'.$path.$filename);
		} else {
			$file_get_contents = file_get_contents($file);
			if(!check::is_utf8($file_get_contents)){
				$file_get_contents = iconv('GBK', CHARSET_HTML, $file_get_contents);
			}
			view::assign('content', $file_get_contents);
		}
	}

	//下载文件
	public function download(){
		$path = str_replace('.', '/', $this->get('path'));
		$filename = $this->get('filename');
		$file = realpath(dirname(__FILE__).'/../..').$path.$filename;
		if(file_exists($file)){
			header('Content-Description: File Transfer');
			header('Content-Type: application/octet-stream');
			header('Content-Disposition: attachment; filename='.$filename);
			header('Content-Transfer-Encoding: binary');
			header('Expires:0');
			header('Cache-Control: must-revalidate');
			header('Pragma:public');
			header('Content-Length: '.filesize($file));
			ob_clean();
			flush();
			readfile($file);
			exit;
		}
	}

	/**
	 * 实用工具
	 */
	public function tools(){
		$md5 = $this->post('md5');
		$sha1 = $this->post('sha1');
		$crypt = $this->post('crypt');
		$md5_file = $this->post('md5_file');
		$sha1_file = $this->post('sha1_file');
		$str2json = $this->post('str2json');
		$urlencode = $this->post('urlencode');
		$urldecode = $this->post('urldecode');
		$htmlspecialchars = $this->post('htmlspecialchars');
		$htmlspecialchars_decode = $this->post('htmlspecialchars_decode');
		$ord = $this->post('ord');
		$chr = $this->post_uint('chr');
		if($md5){
			$result = md5($md5);
		}
		if($sha1){
			$result = sha1($sha1);
		}
		if($crypt){
			$result = crypt($crypt);
		}
		if($md5_file){
			$result = md5_file($md5_file);
		}
		if($sha1_file){
			$result = sha1_file($sha1_file);
		}
		if($str2json){
			$result = json_decode(unfilter($str2json), true);
			if($result){
				$result = '<pre>'.print_r($result, true).'</pre>';
			}
		}
		if($urlencode){
			$result = urlencode($urlencode);
		}
		if($urldecode){
			$result = urldecode($urldecode);
		}
		if($htmlspecialchars){
			$result = htmlspecialchars($htmlspecialchars);
		}
		if($htmlspecialchars_decode){
			$result = htmlspecialchars_decode($htmlspecialchars_decode);
		}
		if($ord){
			$result = ord($ord);
		}
		if($chr){
			$result = chr($chr);
		}
		view::assign('result', $result);
	}

	/**
	 * 系统设置
	 */
	public function setting(){
		$title = $this->post('title');
		$description = $this->post('description');
		$keywords = $this->post('keywords');
		$server = $this->post_uint('server');
		$statistics = $this->post('statistics');
		if($title && $description && $keywords && $server && $statistics){
			msystem::save_setting($title, $description, $keywords, $server, $statistics);
		}
		$servers = mserver::lists(1,99999999);
		view::assign('servers', $servers['list']);
		view::assign('setting', msystem::get_setting());
	}

	/**
	 * 服务器管理
	 */
	public function servers(){
		$name = $this->post('name');
		$url = $this->post('url');
		if($name && $url){
			if(!string::last_is($url, '/')){
				$url .= '/';
			}
			mserver::add($name, $url);
		}
		$page = $this->get('page');
		$page = $page ? $page : 1;
		$rs = mserver::lists($page);
		page::init($page, ADMIN_PAGE_SIZE, $rs['count']);
		page::search();
		page::total();
		view::assign('page', page::show());
		view::assign('servers', $rs['list']);
	}

	/**
	 * 查看数据库_top
	 */
	public function db(){
		header('location:'.url().'rockmongo/index.php?action=login.index&username='.DB_USER.'&password='.DB_PASS.'&host=0&db='.DB_NAME);
	}
}
?>