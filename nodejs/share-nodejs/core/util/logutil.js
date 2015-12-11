// 日志工具
var log4js = require('log4js');
var log4config = require('../../config/log4js');
log4js.configure(log4config.config);

// 暴露logger对象
exports.log4js = log4js;