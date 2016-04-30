// 初始化程序 引入全局方法 lodash 直接可以用 _. 调用
import path from './framework/config/path';
import filesystem from './framework/core/util/filesystem';
import Redis from './framework/core/database/redis';
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

// redis
let redisConfig = {};
redisConfig.host = properties['redis.host'];
redisConfig.port = properties['redis.port'];
global.redis = Redis(redisConfig);

// 加载url为全局变量
global.URLCommand = require('./framework/common/URLCommand');

// 加载所有错误码
global.errorCode = require('./framework/common/errorCode');

// 短信模板
global.smsTemplate = require('./framework/common/smsTemplate');

// 全局redis key
global.KeyFactory = require('./framework/common/KeyFactory');

// 全局redis NumKey
global.NumKey = require('./framework/common/NumKey');

// 全局redis/mysql  Key和dbService配置
global.Key = require('./framework/common/Key');

let tableColumnMap = {};
_.forEach(NumKey, function (v, k) {
	if (_.isUndefined(tableColumnMap[v.table])) {
		tableColumnMap[v.table] = {};
	}
	tableColumnMap[v.table][v.column] = v.column;
});
NumKey.tableColumnMap = tableColumnMap;

// 定义本项目的session键
global.sessionKey = 'GatherUp-CC';

// 加载项目名到properties
let arr = [];
if (filesystem.isWindows() || filesystem.isDarwin()) {
	arr = AppDir.split('\\');
} else {
	arr = AppDir.split('\/');
}
global.properties["project.name"] = arr[arr.length - 1];

// 项目controller,model,dao,service路径配置
global.Path = path();

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

// 注入base全局方法
global.requireHttpBase = function (fileName) {
	return require(Path.protocol.base + '/' + fileName);
};

// 注入msg全局方法
global.requireMsg = function (fileName) {
	return require(Path.protocol.msg + '/' + fileName);
};

// 注入req全局方法
global.requireHttpReq = function (fileName) {
	return require(Path.protocol.httpReq + '/' + fileName);
};

// 注入res全局方法
global.requireHttpRes = function (fileName) {
	return require(Path.protocol.httpRes + '/' + fileName);
};

// 获取pojo类型名
_.getPojoClass = function (T) {
	var clazz = _.keys(new T());
	if (clazz.length != 1) {
		throw new Error("T is not a pojo class: " + T);
	}
	if (!/^[D][A-Z]/.test(clazz[0])) {
		throw new Error("T is not a pojo class: " + T);
	}
	return clazz[0];
};

// 获取pojo实体对象类型名
_.getPojoObjectClass = function (t) {
	var clazz = _.keys(t);
	if (clazz.length != 1) {
		throw new Error("t is not a pojo Object: " + t);
	}
	if (!/^[D][A-Z]/.test(clazz[0])) {
		throw new Error("t is not a pojo Object: " + t);
	}
	return clazz[0];
};

// 加载省份配置
global.provinceConfig = {};
let provinceConfigJSON = filesystem.loadJSONFile('province.json');
for (let i in provinceConfigJSON) {
	let json = provinceConfigJSON[i];
	global.provinceConfig[parseInt(json.ProID)] = json.name.toString();
}

// 加载城市配置
global.cityConfig = {};
let cityConfigJSON = filesystem.loadJSONFile('city.json');
for (let i in cityConfigJSON) {
	let json = cityConfigJSON[i];
	let proID = parseInt(json.ProID);
	let city = global.cityConfig[proID];
	if (!city) {
		city = {};
		global.cityConfig[proID] = city;
	}
	city[parseInt(json.CityID)] = json.name.toString();
}

// 加载国际区号
let areaCodeJSON = filesystem.loadJSONFile('areacode.json');
let tmpArray = [];
for (let i in areaCodeJSON) {
	tmpArray.push({'code': i, 'area': areaCodeJSON[i]});
}
global.areaCode = {};
global.areaCode['0086'] = '中国';
global.areaCode['00886'] = '台湾';
global.areaCode['00852'] = '香港';
global.areaCode['00853'] = '澳门';
tmpArray = _.sortBy(tmpArray, function (o) {
	return pinyinUtil.toPinyin(o.area);
});
for (let j in tmpArray) {
	global.areaCode[tmpArray[j].code] = tmpArray[j].area;
}