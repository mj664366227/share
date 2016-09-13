import logger from '../util/logUtil';
import filesystem from '../util/filesystem';
import fs from 'fs';
var LOGGER = logger.getLogger(module.filename);

/**
 * 扫描controller包，构建路由列表
 */
module.exports = function () {
	var path = Path.controller;
	var fileList = filesystem.ls(path);
	var route = {};
	var urlMap = {};
	fileList.forEach(function (item) {
		var file = path + "/" + item;
		var stats = fs.statSync(file);
		if (!stats.isDirectory()) {
			var controller = require(file);
			_.forEach(controller, function (v_map, k_funcName) {
				var funObj = v_map.func;
				if (_.isFunction(funObj)) {
					let url = v_map.url;
					let method = v_map.method.toString().toLowerCase();
					//检查url不能为空
					if (!url) {
						process.exit();
					}
					if (urlMap[url] == url) {
						process.exit();
					}
					// 如果是get方法，自动进入白名单
					if (method === 'get') {
						whiteList.push(url);
					}
					route[url] = v_map;
					urlMap[url] = url;
					LOGGER.info('request mapping url: [%s], method: [%s] on %s function %s()', url, method, file, k_funcName);
				}
			});
		}
	});
	return route;
};