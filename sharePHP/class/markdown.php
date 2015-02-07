<?php
/**
 * md文件解析类
 */
class markdown{
	private $content;
	/**
	 * 构造函数
	 * @param $file 文件路径
	 */
	public function __construct($file){
		$this->content = file_get_contents(MD_FILE_ROOT_PATH.'README.md');
	}
	
	/**
	 * 解析
	 */
	public function parse(){
		echo_($this->content);
	}
}
?>