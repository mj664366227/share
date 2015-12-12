var http = require('http');
var mysql = require('mysql');
var base64 = require('./core/util/base64');
var filesystem = require('./core/util/filesystem');
var logutil = require('./core/util/logutil');
var logger = logutil.getLogger(module.filename);
var databaseConfig = require('./config/database');

while (true) {
	logger.info('空间的花费感到很孤独可恢复供货价倒挂');
}

// http.createServer(function handler(req, res) {
// res.writeHead(200, {
// 'Content-Type' : 'text/plain'
// });
// res.end('Hello World\n');
// }).listen(1337, '127.0.0.1');
// console.log('Server running at http://127.0.0.1:1337/');
