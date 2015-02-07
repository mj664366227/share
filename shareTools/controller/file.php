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
		
	}
}
?>