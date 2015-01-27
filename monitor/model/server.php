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
		$data['ip'] = ip::ip_to_int($ip);
		$data['port'] = $port;
		$data['type'] = $os;
		$data['security_name'] = secret::dede($security_name, KEY.$ip);
		$data['pass_phrase'] = secret::dede($pass_phrase, $security_name.KEY);
		$data['auth_protocol'] = $auth_protocol;
		$data['priv_protocol'] = $priv_protocol;
		self::$db->insert('server', $data);
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