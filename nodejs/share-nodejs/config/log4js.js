// log4js配置文件
exports = {
	appenders : [ {
		type : 'console'
	}, {
		type : 'file',
		filename : 'logs/access.log',
		maxLogSize : 1024,
		backups : 4,
		category : 'normal'
	} ],
	replaceConsole : true
}