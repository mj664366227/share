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
	global.Config = require('./config/config');

	// 约定，每个应用程序文件夹下的controller文件夹，就是项目控制器文件夹
	global.ControllerDir = AppDir + '/controller/';

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
		if (filesystem.isWindows() || filesystem.isDarwin()) {
			logUtil.getLogger('core\\global\\exception').error(err);
		} else {
			logUtil.getLogger('core/global/exception').error(err);
		}
	});
};