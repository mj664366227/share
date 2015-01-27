<?php
/**
 * 时间工具类
 */
class timecontroller extends toolscontroller {
	/**
	 * 时间戳转换
	 */
	public function timestamp(){
		$timestamp = $this->post_int('timestamp');
		$date = $this->post('date');
		if(!$timestamp && !$date) {
			return;
		}

		if($timestamp){
			view::assign('timestamp_input', $timestamp);
			view::assign('timestamp_result', date('Y-m-d H:i:s', $timestamp));
		}
		if($date){
			view::assign('date_input', $date);
			view::assign('date_result', strtotime($date));
		}
	}
}
?>