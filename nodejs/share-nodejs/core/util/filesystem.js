// 获取系统绝对路径
exports.getSystemDir = function() {
	var systemDir = __dirname.replace("core", "").replace("util", "").trim();
	return systemDir.substr(0, systemDir.length - 1);
};