<?php
/**
 * 服务器model
 */
class mserver extends model{
	/**
	 * 服务器列表
	 */
	public static function ls(){
		return self::$db->select('server')->order_by(array('id' => DESC))->query();
	}
	
	/**
	 * 添加服务器
	 * @param $server 服务器名称
	 * @param $ip 地址
	 * @param $port 端口
	 * @param $os 操作系统
	 * @param $security_name snmp用户名
	 * @param $pass_phrase snmp密码
	 * @param $auth_protocol snmp身份验证加密方式
	 * @param $priv_protocol 加密/解密协议
	 */
	public static function add($server, $ip, $port, $os, $security_name, $pass_phrase, $auth_protocol, $priv_protocol){
		$data['name'] = $server;
		$data['ip'] = ip::ip_to_long($ip);
		$data['port'] = $port;
		$data['type'] = $os;
		$data['security_name'] = $security_name;
		$data['pass_phrase'] = $pass_phrase;
		$data['auth_protocol'] = $auth_protocol;
		$data['priv_protocol'] = $priv_protocol;
		
		// 加密
		$data['security_name'] = self::encode_security_name($data);
		$data['pass_phrase'] = self::encode_pass_phrase($data);
		self::$db->insert('server', $data);
	}

	/**
	 * 加密snmp密码
	 * @param $snmp_data snmp数据
	 */
	public static function encode_pass_phrase($snmp_data){
		return secret::dede($snmp_data['pass_phrase'], KEY.$snmp_data['ip'].KEY);
	}
	
	/**
	 * 解密snmp密码
	 * @param $snmp_data snmp数据
	 */
	public static function decode_pass_phrase($snmp_data){
		return secret::dede($snmp_data['pass_phrase'], KEY.$snmp_data['ip'].KEY, DECODE);
	}
	
	/**
	 * 加密snmp用户名
	 * @param $snmp_data snmp数据
	 */
	public static function encode_security_name($snmp_data){
		return secret::dede($snmp_data['security_name'], $snmp_data['ip'].KEY.$snmp_data['ip']);
	}
	
	/**
	 * 解密snmp用户名
	 * @param $snmp_data snmp数据
	 */
	public static function decode_security_name($snmp_data){
		return secret::dede($snmp_data['security_name'], $snmp_data['ip'].KEY.$snmp_data['ip'], DECODE);
	}
	
	/**
	 * 获取显示图表所需的数据
	 * @param $serverid 服务器id
	 * @param $time 时间
	 * @param $action 监控的项目
	 */
	public static function get_view_data($serverid, $time, $action){
		switch ($action){
			case 'memory':
				$sql = 'select `data`,`time`,`name` from '.self::$db->table($action).' 
				left join '.self::$db->table('server').' 
				on '.self::$db->table($action).'.`server_id` = '.self::$db->table('server').'.`id` 
				where ';
				$time_array = explode('-', $time);
				if(count($time_array) > 1){
					$where = '`time`>=\''.$time_array[0].'\' and `time`<=\''.$time_array[1].'\'';
				} else {
					$where = '`time`=\''.$time_array[0].'\'';
				}
				$sql .= $where;
				$sql .= ' and `server_id`=\''.$serverid.'\' order by `server_id` ASC, `time` ASC';
				break;
		}
		return self::$db->query($sql);
	}
}
?>