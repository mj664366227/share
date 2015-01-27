<?php
/**
 * 服务器管理model
 */
class mserver extends model{
	/**
	 * 添加图片服务器
	 * @param $name 服务器名称
	 * @param $url 服务器地址
	 */
	public static function add($name, $url){
		self::$db->insert('server', array('name' => $name, 'url' => $url.'?'));
	}


	/**
	 * 服务器列表
	 * @param $page 当前页码
	 * @param $size 分页大小(默认是配置的大小)
	 */
	public static function lists($page, $size = ADMIN_PAGE_SIZE){
		$list = self::$db->select(array('_id', 'name', 'url'))
		->order_by(array('_id' => DESC))
		->limit(($page - 1) * $size, $size)
		->get('server');
		$rs['list'] = $list;
		$rs['count'] = self::$db->count('server');
		return $rs;
	}
}