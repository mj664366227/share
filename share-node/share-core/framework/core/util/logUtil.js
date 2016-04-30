// 日志工具
import log4js from 'log4js';
import path from 'path';
import Log4config from '../../config/log4js';
import filesystem from './filesystem';

// 判断如果是windows系统，移除写文件的代码
let log4config = Log4config(AppDir);
if (filesystem.isWindows() || filesystem.isDarwin()) {
	log4config.appenders.reverse();
	log4config.appenders.shift();
} else {
	// 判断是否有日志文件夹，如果没有就创建一个
	var logfile = log4config.appenders[1].filename;
	logfile = path.parse(logfile);
	if (!filesystem.exists(logfile.dir)) {
		filesystem.mkdir(logfile.dir);
	}
}
// 加载日志配置
log4js.configure(log4config);
module.exports = {
	// 暴露logger对象
	getLogger: function (obj) {
		let arr = [];
		if (filesystem.isWindows() || filesystem.isDarwin()) {
			arr = obj.split('\\');
		} else {
			arr = obj.split('\/');
		}
		let arrLength = arr.length;
		let category = arr[arrLength - 3] + "." + arr[arrLength - 2] + "." + arr[arrLength - 1];
		category = category.replace('.js', '');
		return log4js.getLogger(category);
	}
};