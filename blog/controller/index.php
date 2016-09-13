<?php
/**
 * 首页
 */
class indexcontroller extends controller {

	/**
	 * 首页
	 */
	public function index() {
		view::assign('result', 1);
	}

	public function demo() {
		view::show(false);
		$json = array ();
		$json['error'] = 0;
		$json['url'] = 'https://image.gatherup.cc/2016051515083636542_10277411847325777_1265886255.jpeg';
		echo json_encode($json);
	}
}
?>