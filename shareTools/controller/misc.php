<?php
/**
 * 杂项类
 */
class misccontroller extends toolscontroller {
	/**
	 * 字节数转换
	 */
	public function bytes(){
		$bytes = $this->post_ufloat('bytes');
		if($bytes <= 0){
			return;
		}
		$result = filesystem::bytes($bytes);
		view::assign('result', $result);
		view::assign('input', $bytes);
	}
	
	/**
	 * 简易计算器
	 */
	public function calculator(){
	}
}
?>