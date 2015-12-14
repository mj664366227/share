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
		type : 'file',
		filename : 'log/log.log',
		pattern : "_yyyy-MM-dd",
		maxLogSize : 10000000
	} ],
	replaceConsole : true,
	levels : {
		"[all]" : 'info'
	}
};