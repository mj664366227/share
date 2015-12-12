// log4js配置文件
exports.config = {
	appenders : [ {
		type : 'console',
		layout : {
			type : 'pattern',
			pattern : '%d{yyyy-MM-dd hh:mm:ss} [%-4p] %c - %m',
			absolute : true,
			tokens : {
				pid : function() {
					return module.filename;
				}
			}
		}
	}, // 控制台输出
	{
		type : 'file', // 文件输出
		filename : 'log/log.log',
		maxLogSize : 10000000
	} ],
	levels : {
		dateFileLog : 'info',
		console : 'info'
	}
};