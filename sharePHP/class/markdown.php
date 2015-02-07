<?php
/**
 * md文件解析类
 */
class markdown{
	/**
	 * 输出的html代码
	 */
	private $html = '<div style="width:100%" id="md_file_content"><style>h1,h2,h3,h4,h5,h6{margin:20px}</style>';
	/**
	 * 文件句柄
	 */
	private $handle = null;
	
	/**
	 * 构造函数
	 * @param $file 文件路径
	 */
	public function __construct($file){
		if(!file_exists($file)){
			echo_('file '.$file.' not exists...', true);
		}
		$this->handle = fopen($file, 'r');
		if(!$this->handle) {
			echo_('can not read file '.$file, true);
		}
	}
	
	/**
	 * 解析
	 */
	public function parse(){
		while (($buffer = fgets($this->handle)) !== false) {
			$buffer = trim($buffer);
			
			// 解析标题
			$this->parse_title($buffer);
			
			// 解析分割线
			$this->parse_hr($buffer);
		}
		fclose($this->handle);
		$this->html .= '</div>';
		return $this->html;
	}
	
	/**
	 * 解析标题
	 * @param $buffer 文件流
	 */
	private function parse_title($buffer){
		$h = intval(substr_count($buffer, "#"));
		if($h <= 0 || $h > 6) {
			return;
		}
		$method = 'parse_h'.$h;
		$buffer = trim(str_replace("#", "", $buffer));
		$this->html .= '<h'.$h.'>'.$buffer.'</h'.$h.'>';
	}
	
	/**
	 * 解析分割线
	 * @param $buffer 文件流
	 */
	private function parse_hr($buffer){
		echo_($buffer);
	}
}
?>