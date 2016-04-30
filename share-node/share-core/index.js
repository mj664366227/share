// 初始化程序 引入全局方法 lodash 直接可以用 _. 调用
import lodash from 'lodash';
import filesystem from './core/util/filesystem';
/**
 * 框架初始化
 */
module.exports = ()=> {
	global._ = lodash;
	global.CoreDir = __dirname;
	console.log(AppDir);

	// 注入util全局方法
	let utilPath = __dirname + '/core/util/';
	let utilFileList = filesystem.ls(utilPath);
	utilFileList.forEach(function (item) {
		global[item.substring(0, item.indexOf('.'))] = require(utilPath + item);
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