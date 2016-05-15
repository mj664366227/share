<?php
/**
 * 图片上传类
 */
class imagecontroller extends admincontroller {

	/**
	 * 图片上传
	 */
	public function upload() {
		view::show(false);
		$json = array ();
		$json['error'] = 0;
		$json['url'] = 'https://image.gatherup.cc/2016051515083636542_10277411847325777_1265886255.jpeg';
		echo json_encode($json);
	}
}
?>