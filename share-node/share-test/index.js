require('../share-core/index')();
/**
 * 程序入口
 * @param appDir 应用程序路径
 */
module.exports = function (appDir) {
	global.AppDir = appDir;
	//var httpServer = require('../share-core/core/server/httpServer');
	//httpServer.start(1234, null);
};
