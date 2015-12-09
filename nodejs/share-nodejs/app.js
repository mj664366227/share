var h = require('./core/core');
var filesystem = require('./core/util/filesystem');
var httpServer = require('./core/server/httpServer');
var demoController = require('./controller/demoController');
var config = require('./config/config');
var base64 = require('./core/util/base64');

var requestMapping = {};
requestMapping["/start"] = demoController.start;
requestMapping["/upload"] = demoController.upload;

console.log(base64.encode("你好"));
console.log(base64.decode(base64.encode("你好")));

httpServer.start(9999, requestMapping);