<?php
/**
 * 作品管理
 */
class workscontroller extends managecontroller{
	/**
	 * 添加分类
	 */
	public function add(){
		$name = fliter($this->post('name'));
		if($name){
			mworks::add($name);
		}
		view::assign('category', mworks::get_all());
	}

	//删除分类
	public function delete(){
		$id = $this->get_uint('id');
		if($id > 0){
			mworks::delete($id);
		}
		redirect(url('works', 'add'));
	}
}
?>