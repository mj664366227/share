<?php
/**
 * 用户model
 */
class muser extends model{
	/**
	 * 添加新用户
	 * @param $email 电子邮件地址
	 * @param $nick 昵称
	 * @param $pass 密码
	 */
	public static function add($email, $nick, $pass){
		$data['email'] = $email;
		$data['nick'] = $nick;
		$data['pass'] = secret::dede($pass, KEY);
		return self::$db->insert('user', $data);
	}

	/**
	 * 根据uid查找用户
	 * @param $uid
	 */
	public static function get_user_by_uid($uid){
		if(is_array($uid)){
			return self::$db->select()->in('_id',$uid)->get('user');
		} else {
			$rs = self::$db->select()->where(array('_id'=>intval($uid)))->get('user');
			return $rs[0];
		}
	}

	/**
	 * 根据email查找用户
	 * @param $email
	 */
	public static function get_user_by_email($email){
		$rs = self::$db->select()->where(array('email'=>strval($email)))->get('user');
		return $rs[0];
	}

	/**
	 * 根据昵称查找用户
	 * @param $nick
	 */
	public static function get_user_by_nick($nick){
		$rs = self::$db->select()->where(array('nick'=>strval($nick)))->get('user');
		return $rs[0];
	}

	/**
	 * 修改用户信息
	 * @param $uid 用户id
	 * @param $data 要修改的数据
	 */
	public static function modify($uid, $data){
		if(!$data){
			return;
		}
		self::$db->update('user', array('_id'=>intval($uid)), $data);
	}
}
?>