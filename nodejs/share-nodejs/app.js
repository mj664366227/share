var http = require('http');
var mysql = require('mysql');
var log4js = require('log4js');
var base64 = require('./core/util/base64');
var databaseConfig = require('./config/database');

var log4js = require('log4js');
log4js.configure({
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
});
var logger = log4js.getLogger('core.util.base64');
logger.trace('Entering cheese testing');
logger.debug('Got cheese.');
logger.info('Cheese is Gouda.');
logger.warn('Cheese is quite smelly.');
logger.error('Cheese is too ripe!');
logger.fatal('Cheese was breeding ground for listeria.');

// http.createServer(function handler(req, res) {
// res.writeHead(200, {
// 'Content-Type' : 'text/plain'
// });
// res.end('Hello World\n');
// }).listen(1337, '127.0.0.1');
// console.log('Server running at http://127.0.0.1:1337/');
