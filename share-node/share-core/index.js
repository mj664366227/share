// 初始化程序 引入全局方法 lodash 直接可以用 _. 调用
import filesystem from './core/util/filesystem';
import lodash from 'lodash';
import fs from 'fs';

global._ = lodash;
global.CoreDir = __dirname;

// 初始化dao工程
require("../node-dao/index");

// 初始化protocol工程
require("../node-protocol/index");

// 初始化service工程
require("../node-service/index");

// 加载properties文件
global.filesystem = require("./framework/core/util/filesystem");
global.properties = filesystem.loadProperties('config.properties');

// 注入util全局方法
global.base64 = require("./framework/core/util/base64");
global.httpClient = require("./framework/core/util/httpClient");
global.random = require("./framework/core/util/random");
global.secret = require("./framework/core/util/secret");
global.timeUtil = require("./framework/core/util/timeUtil");
global.logUtil = require("./framework/core/util/logUtil");
global.JSONObject = require("./framework/core/util/JSONObject");
global.pinyinUtil = require("./framework/core/util/pinyinUtil");
global.qiniuUtil = require("./framework/core/util/qiniuUtil");
global.gatherupUtil = require("./framework/core/util/gatherupUtil");

// 数据库链接
global.guMasterDbService = require('../node-dao/db/guMasterDbService');
global.guSlaveDbService = require('../node-dao/db/guSlaveDbService');
global.guConfigDbService = require('../node-dao/db/guConfigDbService');
global.guAdminDbService = require('../node-dao/db/guAdminDbService');
global.guLogDbService = require('../node-dao/db/guLogDbService');


// 注入Service全局方法
global.requireService = function (fileName) {
	return require(Path.service + '/' + fileName);
};

// 注入Dao全局方法
global.requireDao = function (fileName) {
	return require(Path.dao + '/' + fileName);
};

// 注入T全局方法
global.requireT = function (fileName) {
	return require(Path.model + '/' + fileName);
};