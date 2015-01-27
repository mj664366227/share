<?php
/**
 * 字符工具类
 */
class stringcontroller extends toolscontroller {
	/**
	 * md5加密
	 */
	public function md5(){
		$md5 = $this->post('md5');
		if(!$md5) {
			return;
		}

		view::assign('input', $md5);
		view::assign('result', md5($md5));
	}

	/**
	 * 哈希加密
	 */
	public function sha1(){
		$sha1 = $this->post('sha1');
		if(!$sha1) {
			return;
		}

		view::assign('input', $sha1);
		view::assign('result', sha1($sha1));
	}

	/**
	 * crypt加密
	 */
	public function crypt(){
		$crypt = $this->post('crypt');
		if(!$crypt) {
			return;
		}

		view::assign('input', $crypt);
		view::assign('result', crypt($crypt));
	}

	/**
	 * json工具
	 */
	public function json(){
		$input = $this->post('json');
		if(!$input) {
			return;
		}
		$result = json_decode($input, true);
		view::assign('input', $input);
		view::assign('result', is_array($result) ? $result : '这不是json！');
	}

	/**
	 * url编码解码
	 */
	public function url(){
		$url = $this->post('url');
		if(!$url) {
			return;
		}

		$result['urlencode'] = urlencode($url);
		$result['urldecode'] = urldecode($url);

		view::assign('input', $url);
		view::assign('result', $result);
	}

	/**
	 * html字符转义
	 */
	public function html(){
		$html = $this->post('html');
		if(!$html) {
			return;
		}

		view::assign('input', $html);
		view::assign('result', htmlspecialchars($html));
	}

	/**
	 * ASCII编码
	 */
	public function ascii(){
		$str = $this->post('str');
		if(!$str) {
			return;
		}

		if(is_numeric($str)) {
			$result['ord'] = intval($str);
			$result['chr'] = chr($str);
		} else {
			$result['ord'] = ord($str);
			$result['chr'] = $str;
		}
		
		view::assign('input', $str);
		view::assign('result', $result);
	}
}
?>