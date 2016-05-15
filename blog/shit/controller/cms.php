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
			$id = intval($category[0]['id']);
		}
		$content = mcms::get_content_by_category($id, $page, $page_size);
		$max = intval($content['max']);
		view::assign('id', $id);
		view::assign('category', $category);
		view::assign('content', $content['list']);
		view::assign('page', $page);
		view::assign('max', $max);
	}

	/**
	 * 发表文章
	 */
	public function pub() {
		$c = $this->post_uint('c');
		$content = $this->post('content');
		$title = $this->post('title');
		if ($c && $title && $content) {
			$is = false;
			foreach (mcms::get_category() as $cat) {
				if (intval($cat['id']) === $c) {
					$is = true;
					break;
				}
			}
			if ($is === false) {
				return;
			}
			mcms::add_content($c, $title, $content);
		}
		view::assign('category', mcms::get_category());
	}
}
?>