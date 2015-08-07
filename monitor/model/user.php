<?php
/**
 * 用户model
 */
class muser extends model{
	/**
	 * 添加用户
	 * @param $name
	 * @param $pass
	 */
	public static function add($name, $pass){
		$data['name'] = $name;
		$data['pass'] = md5(sha1($name.KEY.$pass));
		return self::$db->insert('user', $data);
	}
	
	/**
	 * 获取用户
	 * @param $name
	 */
	public static function get($name){
		$rs = self::$db->select('user')->where('name')->eq($name)->query();
		if(!$rs){
			return null;
		}
		return $rs[0];
	}
	
	/**
	 * 用户列表
	 */
	public static function lists(){
		return self::$db->select('user')->order_by(array('id' => DESC))->query();
	}
}
?>