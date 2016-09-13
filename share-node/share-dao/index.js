// dao工程入口
module.exports = ()=> {
	global.DaoDir = __dirname;

	// 初始化数据库连接
	global.dbService = require('./db/dbService');

	// 注入Dao全局方法
	global.requireDao = function (fileName) {
		return require(DaoDir + '/dao/' + fileName);
	};

	// 注入T全局方法
	global.requireT = function (fileName) {
		return require(DaoDir + '/model/' + fileName);
	};
};