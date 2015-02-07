<?php
/**
 * 文件工具类
 */
class filecontroller extends toolscontroller {
	/**
	 * 文件md5
	 */
	public function md5(){
		$file = $this->file('file');
		if(intval($file['size']) <= 0){
			return;
		}
		$tmp = sharePHP::get_application_dir().'tmp/';
		$extension = substr($file['name'], strrpos($file['name'], '.') + 1);
		filesystem::mkdir($tmp);
		$filename = $tmp.'1.'.$extension;
		move_uploaded_file($file['tmp_name'], $filename); 
		$result = md5_file($filename);
		filesystem::rm($tmp);
		view::assign('result', $result);
	}
	
	/**
	 * 文件哈希
	 */
	public function sha1(){
		$file = $this->file('file');
		if(intval($file['size']) <= 0){
			return;
		}
		$tmp = sharePHP::get_application_dir().'tmp/';
		$extension = substr($file['name'], strrpos($file['name'], '.') + 1);
		filesystem::mkdir($tmp);
		$filename = $tmp.'1.'.$extension;
		move_uploaded_file($file['tmp_name'], $filename); 
		$result = sha1_file($filename);
		filesystem::rm($tmp);
		view::assign('result', $result);
	}
	
	/**
	 * md文件解析
	 */
	public function md(){
		$file = $this->get('file');
		$p = $this->get('p');
		
		// 目录
		$directory = MD_FILE_ROOT_PATH.$p.'/';
		
		// 遍历文件夹所有内容
		$path = array();
		$dir = filesystem::ls($directory);
		usort($dir, 'compare');
		foreach ($dir as $d){
			$path[] = trim($d);
		}
		
		//  解析mk文件
		$file = $directory.$file;
		if(is_file($file)){
			$md = new markdown($file);
			$html = $md->parse();
			view::assign('html', $html);
		}
		view::assign('path', $path);
		view::assign('p', $p);
		view::assign('file', str_replace($directory, '', $file));
	}
}

function compare($a, $b){
	$aa = is_int(strpos($a, '.'));	
	$bb = is_int(strpos($b, '.'));
	if($aa) {
		return 1;
	}
	if($bb) {
		return -1;
	}
	if ($a == $b) {
		return 0;
	}
	return ($a < $b) ? -1 : 1;
}
?>