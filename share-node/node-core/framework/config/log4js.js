// log4js配置文件

import filesystem from '../core/util/filesystem';
let rootLogger = filesystem.loadProperties('log4j.properties');
rootLogger = rootLogger['log4j.rootLogger'].toString().trim();
rootLogger = rootLogger.split(',')[0].toString().trim().toLowerCase();
module.exports = function (dir) {
	return {
		appenders: [{
			// 控制台输出
			type: 'console',
			layout: {
				type: 'pattern',
				pattern: '%d{yyyy-MM-dd hh:mm:ss} [%-4p] %c - %m'
			}
		}, {// 文件输出
			type: 'dateFile',
			filename: dir + '/log/log.log',
			pattern: ".yyyy-MM-dd"
		}],
		levels: {
			"[all]": rootLogger
		}
	}
};