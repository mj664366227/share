<?php
/**
 * 服务器类
 */
class servercontroller extends toolscontroller {
	/**
	 * ip地址查询
	 */
	public function ip(){
		$ip = $this->post('ip');
		if(!$ip){
			return;
		}
		
		$result = $ip_address = array();
		
		// tool.lu
		$toollu = curl::post('http://tool.lu/netcard/ajax.html', array('ip'=>$ip));
		$toollu = json_decode($toollu, true);
		$result['tool.lu'] = trim($toollu['text'][2]);
		$result['纯真数据库'] = trim($toollu['text'][1]);
		
		// 站长工具
		$chinaz = curl::post('http://ip.chinaz.com', array('ip'=>$ip));
		$chinaz = trim(preg_replace('/<.+?>/', '', $chinaz));
		$chinaz = explode('查询结果', $chinaz);
		if($chinaz) {
			unset($chinaz[0]);
			$size = intval(count($chinaz));
			$chinaz[$size] = trim(substr($chinaz[$size], 0, strpos($chinaz[$size], 'document')));
			$address = '';
			foreach($chinaz as $c){
				$c = explode('==>>', $c);
				$ip_address_ = explode(':', trim($c[0]));
				$ip_address[] = trim($ip_address_[1]);
				$tmp_arr = explode("\r\n",trim($c[2]));
				$address .= trim($tmp_arr[0]).'  ';
			}
			$result['站长工具'] = trim($address);
		}
		
		// 淘宝
		if($ip_address){
			$taobao = curl::get('http://ip.taobao.com/service/getIpInfo.php', array('ip'=>$ip_address[0]));
			$taobao = json_decode($taobao, true);
			if(intval($taobao['code']) === 0){
				$taobao = $taobao['data'];
				$result['淘宝'] = $taobao['country'].'|'.$taobao['area'].'|'.$taobao['region'].'|'.$taobao['city'].'|'.$taobao['isp'];
			}
		}
		
		// ipip.net
		if($ip_address){
			$ipip = curl::get('http://freeapi.ipip.net/'.$ip_address[0]);
			$ipip = json_decode($ipip, true);
			if(is_array($ipip)){
				$ipipstr = '';
				foreach($ipip as $str){
					$str = trim($str);
					if(empty($str)){
						continue;
					}
					$ipipstr .= $str.' ';
				}
				$result['ipip.net'] = $ipipstr;
			}
		}
		
		view::assign('input', $ip);
		view::assign('ip', $ip_address);
		view::assign('result', $result);
	}
}
?>