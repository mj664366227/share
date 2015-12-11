var http = require('http');
var mysql = require('mysql');
var log4js = require('log4js');
var databaseConfig = require('./config/database');

console.log(log4js);
// http.createServer(function handler(req, res) {
// res.writeHead(200, {
// 'Content-Type' : 'text/plain'
// });
// res.end('Hello World\n');
// }).listen(1337, '127.0.0.1');
// console.log('Server running at http://127.0.0.1:1337/');
