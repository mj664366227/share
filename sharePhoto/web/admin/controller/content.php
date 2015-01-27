<?php
/**
 * 文章管理
 */
class contentcontroller extends managecontroller{
	/**
	 * 添加分类
	 */
	public function add(){
		$name = fliter($this->post('name'));
		if($name){
			mcontent::add($name);
		}
		view::assign('category', mcontent::get_all());
	}

	//删除分类
	public function delete(){
		$id = $this->get_uint('id');
		if($id > 0){
			mcontent::delete($id);
		}
		redirect(url('content', 'add'));
	}
}
?>