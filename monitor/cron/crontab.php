<?php
//crontab
$base = dirname(__FILE__).'/';
require $base.'../config.php';
load_file(SHARE_ROOT.'class/shareMysql');
$mysql = new shareMysql(DB_HOST, DB_USER, DB_PASS, DB_PRE, DB_NAME, DB_PORT, CHARSET);
$servers = $mysql->select('server')->query();

$t = microtime(true);
$day_break = time::day_break();
foreach ($servers as $s) {
	echo "==========".$s['name']."==============\r\n";
	$security_name = secret::dede($s['security_name'], KEY.$ip, DECODE);
	$pass_phrase = secret::dede($s['pass_phrase'], $security_name.KEY, DECODE);
	$ip = ip::long_to_ip($s['ip']);
	$snmp = new snmpworker($s['type'], $ip.':'.$s['port'], $security_name, SNMP_AUTH_PRIV, $s['auth_protocol'], $pass_phrase, $s['priv_protocol'], $pass_phrase);
	$rs = $snmp->get('.1.3.6.1.4.1.2021');
	$where = array();

	//内存
	$data = array ();
	$where['time'] = $day_break;
	$where['server_id'] = $s['id'];
	$data['now'] = $_SERVER['REQUEST_TIME'];
	$mem_total = clean($rs['UCD-SNMP-MIB::memTotalReal.0']);
	$mem_free = clean($rs['UCD-SNMP-MIB::memAvailReal.0']);
	$mem_buffers = clean($rs['UCD-SNMP-MIB::memBuffer.0']);
	$mem_cache = clean($rs['UCD-SNMP-MIB::memCached.0']);
	$data['mem_used'] = $mem_total - $mem_free - $mem_buffers - $mem_cache;
	echo "mem_used : ".$data['mem_used']."\r\n";
	update_data('memory', $where, $data);

	//平均负载
	$data = array ();
	$data['now'] = $_SERVER['REQUEST_TIME'];
	$data['load1'] = clean($rs['UCD-SNMP-MIB::laLoad.1']);
	$data['load5'] = clean($rs['UCD-SNMP-MIB::laLoad.2']);
	$data['load15'] = clean($rs['UCD-SNMP-MIB::laLoad.3']);
	echo "loadavg : ".$data['load1'].'  '.$data['load5'].'  '.$data['load15'].'  '."\r\n";
	update_data('loadavg', $where, $data);
}

$data = "==========".date('Y-m-d H:i:s')."==========\r\n";
$data .= 'servers:'.(count($servers)).'   run time:'.(microtime(true) - $t)."\r\n\r\n";
$path = realpath($base.'..').'/log/';
if (!file_exists($path)) {
	mkdir($path, 0777, true);
}
file_put_contents($path.date('Ymd').'.log', $data, FILE_APPEND);
echo $data;

function clean($data) {
	return trim(preg_replace('/[^(\d+\.\d+)]/', '', $data));
}

function update_data($table, $where, $data) {
	global $mysql;
	$database = $mysql->select($table, array('data'))->where('time')->eq($where['time'])->and_where('server_id')->eq($where['server_id'])->query();
	$insert = array();
	foreach ($data as $key => $value) {
		$insert['data'] .= $value.'|';
	}
	$insert['data'] = substr($insert['data'], 0, -1).';';
	if (!$database) {
		$insert['server_id'] = $where['server_id'];
		$insert['time'] = $where['time'];
		$mysql->insert($table, $insert);
	} else {
		$insert['data'] = $database[0]['data'].$insert['data'];
		$mysql->update($table, $insert)->where('time')->eq($where['time'])->and_where('server_id')->eq($where['server_id'])->query();
	}
}
?>