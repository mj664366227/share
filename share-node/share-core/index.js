// 初始化程序 引入全局方法 lodash 直接可以用 _. 调用
import lodash from 'lodash';
import filesystem from './core/util/filesystem';
/**
 * 框架初始化
 */
module.exports = ()=> {
	global._ = lodash;
	global.Promise = require('bluebird');
	global.CoreDir = __dirname;

	// 加载配置文件(自动区分开发环境和生产环境)
	global.config = require('./config/config');

	// 自动注入util全局方法
	let utilPath = __dirname + '/core/util/';
	let utilFileList = filesystem.ls(utilPath);
	global.logUtil = require(utilPath + 'logUtil.js');
	utilFileList.forEach(function (item) {
		item = item.substring(0, item.indexOf('.'));
		if (item !== 'logUtil') {
			global[item.substring(0, item.indexOf('.'))] = require(utilPath + item + '.js');
		}
	});

	// 全局错误监听
	process.on('uncaughtException', function (err) {
		logUtil.getLogger(module.filename).error(err);
	});

	// 注入Service全局方法
	global.requireService = function (fileName) {
		return require(Path.service + '/' + fileName);
	};

	// 注入Dao全局方法
	global.requireDao = function (fileName) {
		return require(Path.dao + '/' + fileName);
	};

	// 注入T全局方法
	global.requireT = function (fileName) {
		return require(Path.model + '/' + fileName);
	};
};