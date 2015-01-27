<?php
/**
 * 用户管理model
 */
class muser extends model{
	/**
	 * 获取所有用户
	 * @param $page 当前页码
	 * @param $size 分页大小(默认是配置的大小)
	 */
	public static function lists($page, $size = ADMIN_PAGE_SIZE){
		$list = self::$db->select('member')->order_by(array('id'=>DESC))
		->limit(($page - 1) * $size, $size)
		->query();
		$rs['list'] = $list;
		$rs['count'] = self::$db->get_count();
		return $rs;
	}

	/**
	 * 用户搜索
	 * @param $nick 用户昵称
	 * @param $page 当前页码
	 * @param $size 分页大小(默认是配置的大小)
	 */
	public static function search($nick, $page, $size = ADMIN_PAGE_SIZE){
		self::$db->select('member');
		if($nick){
			self::$db->where('nick')->like($nick);
		}
		$list = self::$db->order_by(array('id'=>DESC))
		->limit(($page - 1) * $size, $size)
		->query();
		$rs['list'] = $list;
		$rs['count'] = self::$db->get_count();
		return $rs;
	}
}