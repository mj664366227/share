// log4js配置文件
exports.config = {
	appenders : [ {
		// 控制台输出
		type : 'console',
		layout : {
			type : 'pattern',
			pattern : '%d{yyyy-MM-dd hh:mm:ss} [%-4p] %c - %m'
		}
	}, {// 文件输出
		type : 'dateFile',
		filename : 'log/log.log',
		maxLogSize : 10000000
	} ],
	levels : {
		dateFile : 'info',
		console : 'info'
	}
};