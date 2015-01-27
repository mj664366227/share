<?php
/**
 * 管理员model
 */
class madmin extends model{
	private static $table = 'admin';

	/**
	 * 获取一个管理员的信息(支持用户名或者是id)
	 * @param $user 用户名
	 * @return 管理员信息数组
	 */
	public static function get($user){
		$where = 'name';
		if(is_numeric($user)){
			$where = 'id';
		}
		$rs = self::$db->select(array('id', 'name', 'nick', 'pass', 'lass_login_time', 'ip', 'times', 'rank'))
		->where(array($where => $user))->get(self::$table);
		return $rs[0];
	}
	/**
	 * 登录操作
	 * @param $user 用户名
	 * @param $pass 密码
	 * @return
	 * <p>成功返回管理员信息</p>
	 * <p>错误返回状态码：-1用户不存在，-2密码不正确</p>
	 */
	public static function login($user, $pass){
		$rs = self::get($user);
		if(count($rs) <= 0){
			return -1;
		}
		$pass = md5($user.$pass.KEY);
		if($rs['pass'] !== $pass){
			return $rs;
		}
		return $rs;
	}

	/**
	 * 列出所有管理员
	 * @param $page 当前页码
	 * @param $size 分页大小(默认是配置的大小)
	 * @return 管理员列表
	 */
	public static function ls($page, $size = ADMIN_PAGE_SIZE){
		$list = self::$db->select(array('_id', 'name', 'nick', 'lass_login_time', 'ip', 'times'))
		->order_by(array('_id' => DESC))
		->limit(($page - 1) * $size, $size)
		->get(self::$table);
		$rs['list'] = $list;
		$rs['count'] = self::$db->count(self::$table);
		return $rs;
	}

	/**
	 * 添加管理员
	 * @param $name 管理员帐号
	 * @param $nick 管理员昵称
	 * @param $pass 管理员密码
	 * @param $rank 权限
	 * @return 管理员id(0为错误)
	 */
	public static function add($name, $nick, $pass, $rank){
		$pass = md5($name.$pass.KEY);
		return self::$db->insert(self::$table, 
			array('name' => $name, 'nick' => $nick, 'pass' => $pass, 
			'lass_login_time' => $_SERVER['REQUEST_TIME'], 
			'ip' => ip::ip_to_long(ip::get_user_ip()), 
			'times' => 0, 'rank' => $rank));
	}

	/**
	 * 修改管理员信息
	 * @param $id 管理员id
	 * @param $update 要修改的内容array('k'=>'v')
	 */
	public static function update($id, $update){
		return self::$db->update(self::$table, array('_id' => intval($id)), $update);
	}

	/**
	 * 删除管理员
	 * @param $id 管理员id
	 */
	public static function delete($id){
		return self::$db->delete(self::$table)->where('id')->eq(intval($id))->query();
	}

	/**
	 * 获取管理组
	 */
	public static function get_group(){
		return self::$db->select('group')->order_by(array('id'=>ASC))->query();
	}
}
?>