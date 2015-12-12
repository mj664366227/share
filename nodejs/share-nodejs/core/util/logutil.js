// 日志工具
var log4js = require('log4js');
var log4config = require('../../config/log4js');
var filesystem = require('./filesystem');
log4js.configure(log4config.config);

// 暴露logger对象
exports.getLogger = function(obj) {
	var category = obj.toString().replace(filesystem.getSystemDir(), '');
	category = category.replace(/\\+/g, '.');
	category = category.replace(/\/+/g, '.');
	category = category.replace('.js', '');
	return log4js.getLogger(category);
};