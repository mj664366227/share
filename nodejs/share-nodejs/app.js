var http = require('http');
var mysql = require('mysql');
var log4js = require('log4js');
var databaseConfig = require('./config/database');

// logger configure
log4js.configure({
	appenders : [ {
		type : 'console'
	}, {
		type : 'dateFile',
		filename : 'log/pattern_yyyy-MM-dd.log',
		// filename: "blah.log",
		pattern : "yyyy-MM-dd",

		maxLogSize : 1024,
		// "pattern": "-yyyy-MM-dd",
		alwaysIncludePattern : false,

		backups : 4,
		category : 'normal'
	}, ],
	replaceConsole : true
});

log4js.getLogger(databaseConfig).info("测试出版司法官if孤独");
log4js.getLogger(databaseConfig).warn("sssssssss");
// http.createServer(function handler(req, res) {
// res.writeHead(200, {
// 'Content-Type' : 'text/plain'
// });
// res.end('Hello World\n');
// }).listen(1337, '127.0.0.1');
// console.log('Server running at http://127.0.0.1:1337/');
