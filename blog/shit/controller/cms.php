<?php
/**
 * 内容类
 */
class cmscontroller extends admincontroller {

	/**
	 * 文章分类
	 */
	public function category() {
		$name = $this->post('name');
		if ($name) {
			mcms::add_category($name);
		}
		$category = mcms::get_category();
		$id = $this->get_uint('id');
		if ($id <= 0) {
			$id = $category[0]['id'];
		}
		view::assign('category', $category);
		view::assign('id', $id);
	}

	/**
	 * 发表文章
	 */
	public function pub() {
	}
}
?>