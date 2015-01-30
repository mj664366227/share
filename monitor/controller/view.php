<?php


/**
 * 查看图表
 */
class viewcontroller extends controller {
	private $mothod = null;

	/**
	 * 构造函数
	 */
	public function __construct() {
		$menu = array (
			'index' => NAV_OVERVIEW,
			'log' => NAV_LOG,
			'setting' => NAV_SETTING
		);
		view::assign('menu', $menu);
	}

	/**
	 * 析构函数
	 */
	public function __destruct() {
		global $lang;
		view::assign('class', sharePHP::get_class());
		view::assign('action', sharePHP::get_method());
		view::display();
	}

	/**
	 * 首页
	 */
	public function index() {
		view::assign('server_list', mserver::ls());
	}

	/**
	 * 视图
	 */
	public function view() {
		$serverid = $this->get_uint('serverid');
		$content = $this->get('content'); //监控内容
		$content = $content ? $content : 'memory'; //TODO 暂时现在这样
		switch ($content) {
			case 'memory' :
				$this->view_memory($serverid);
				break;
			case 'loadavg' :
				$this->view_loadavg($serverid);
				break;
		}
		view::assign('serverid', $serverid);
		view::assign('content', $content);
	}
	
	/**
	 * 生成平均负载监控图表
	 * @param $serverid 服务器id
	 */
	private function view_loadavg($serverid) {
		
	}

	/**
	 * 生成内存监控图表
	 * @param $serverid 服务器id
	 */
	private function view_memory($serverid) {
		$st_ = $st = $this->get('st');
		$et_ = $et = $this->get('et');
		$custom1 = $this->post('custom1');
		$custom2 = $this->post('custom2');
		$is_custom = 'false';
		if ($custom1 && $custom2) {
			$st_ = $st = $custom1;
			$et_ = $et = $custom2;
			$is_custom = 'true';
			view::assign('custom1', $custom1);
			view::assign('custom2', $custom2);
		}
		$date = time::day_break();
		view::assign('day_break', $date);
		if ($st && $et) {
			$date = time::day_break($st) . '-' . time::day_break($et);
		} else {
			$st_ = $et_ = $st = $et = date('Y-m-d', $date);
			$date .= '-' . $date;
		}
		$diff = time::diff($st, $et);
		if ($diff) {
			view::assign('diff', $diff +1);
		}
		$view_data = mserver::get_view_data($serverid, $date, 'memory');
		$servers = '';
		$i = $st = $et = 0;
		foreach ($view_data as $d) {
			$data['time'] = $d['time'];
			$memory = array ();
			$memory['name'] = urlencode($d['name']);
			$view_data_arr = explode(';', $d['data']);
			foreach ($view_data_arr as $k => $view_data) {
				if (!$view_data) {
					continue;
				}
				$view = explode('|', $view_data);
				echo_($view);
				$memory_use = intval($view[1]);
				if ($memory_use <= 0) {
					continue;
				}
				$memory['data'][] = $memory_use;
				if (!$st && $k == 0) {
					$st = $view[0];
				}
				$et = $view[0];
			}
			if (strrpos($servers, $d['name']) === false) {
				$servers .= $d['name'] . ' , ';
			}

			if ($data['data'][$i]['name'] == $memory['name']) {
				unset ($memory['name']);
				$data['data'][$i] = array_merge_recursive($data['data'][$i], $memory);
			} else {
				$i += 1;
				$data['data'][$i] = $memory;
			}
		}
		if (!$diff && $st_ === $et_) {
			$st = strtotime($st_);
			$et = $st +86400;
		}
		view::assign('is_custom', $is_custom);
		view::assign('server_list', mserver::ls());
		view::assign('st', $st);
		view::assign('et', $et);
		view::assign('date', $date);
		view::assign('data', $data);
		view::assign('servers', substr($servers, 0, -2));
	}

	/**
	 * 日志
	 */
	public function log() {
		$log_files_dir = sharePHP::get_application_dir() . 'log/';
		$file = $this->get_uint('date');
		$log_files = filesystem::ls($log_files_dir, '*.log', FILE_DESC);
		view::assign('log_files', $log_files);
		$file = $file ? $file : str_replace('.log', '', $log_files[0]);
		view::assign('file', $file);
		view::assign('file_content', file_get_contents($log_files_dir . $file . '.log'));
	}

	/**
	 * 设置
	 */
	public function setting() {
		$act = $this->get('act');
		switch ($act) {
			case 'lang' :
				view::assign('lang_list', filesystem::ls(sharePHP::get_application_dir() . 'lang', '*.php'));
				view::assign('cookie_lang', cookie::get('share_lang'));
				if ($this->post('lang_submit')) {
					msystem::set_lang($this->post('lang'));
					header('location:' . url('view', 'setting', 'act=lang'));
				}
				break;
		}
		view::assign('act', $act);
	}

	public function snmp() {
		$snmp = new snmpworker(SNMP_LINUX, '192.168.1.238:161', SNMP_VERSION_3, null, '238xyhz', SNMP_AUTH_PRIV, SNMP_MD5, 'snmp@238xyhz', SNMP_DES, 'snmp@238xyhz');
		$rs = $snmp->get('.1.3.6.1.4.1.2021');
		echo_($rs);
		die;
	}
}
?>