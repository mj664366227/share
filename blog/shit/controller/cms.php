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
		$page = $this->get_uint('page');
		$page = $page <= 0 ? 1 : $page;
		$page_size = 20;
		if ($id <= 0) {
			$id = $category[0]['id'];
		}
		view::assign('category', $category);
		view::assign('id', $id);
		$content = mcms::get_content_by_category($id, $page, $page_size);
		view::assign('content', $content['list']);
		view::assign('page', $page);
		view::assign('max', $content['max']);
	}

	/**
	 * 发表文章
	 */
	public function pub() {
	}
}
?>