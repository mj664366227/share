/**
 * 程序入口
 * @param appDir 应用程序路径
 */
module.exports = (appDir)=> {
	global.AppDir = appDir;
	require('../share-dao/index')();
	require('../share-service/index')();
	require('../share-core/index')();
	var httpServer = require(CoreDir + '/core/server/httpServer');
	httpServer.start(Config.http.port);
};
