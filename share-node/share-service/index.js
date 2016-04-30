// service工程入口
module.exports = ()=> {
	global.ServiceDir = __dirname;

	// 注入Service全局方法
	global.requireService = function (fileName) {
		return require(ServiceDir + '/' + fileName);
	};
};