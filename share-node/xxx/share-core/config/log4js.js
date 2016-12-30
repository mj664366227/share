// log4js配置文件
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
			"[all]": 'info'
		}
	}
};