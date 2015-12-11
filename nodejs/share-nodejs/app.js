var http = require('http');
var mysql = require('mysql');
var log4js = require('log4js');
var base64 = require('./core/util/base64');
var filesystem = require('./core/util/filesystem');
var logutil = require('./core/util/logutil');
var databaseConfig = require('./config/database');


var logger = logutil.log4js.getLogger('mysql');
log4js.info('哈哈哈哈哈');



// http.createServer(function handler(req, res) {
// res.writeHead(200, {
// 'Content-Type' : 'text/plain'
// });
// res.end('Hello World\n');
// }).listen(1337, '127.0.0.1');
// console.log('Server running at http://127.0.0.1:1337/');
