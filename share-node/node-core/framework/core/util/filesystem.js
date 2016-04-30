import fs from 'fs';
import os from 'os';

/**
 * 判断文件夹或者文件是否存在
 * @param file 文件夹路径
 */
module.exports.exists = function (file) {
	return fs.existsSync(file);
};

/**
 * 新建文件夹
 * @param file 文件夹路径
 */
module.exports.mkdir = function (file) {
	return fs.mkdirSync(file);
};

/**
 * 获取操作系统信息
 */
module.exports.getOS = function () {
	return (os.type() + ' ' + os.release()).toLocaleLowerCase();
};

/**
 * 判断当前服务器操作系统是不是windows
 */
module.exports.isWindows = function () {
	return exports.getOS().indexOf('windows') >= 0;
};

/**
 * 判断当前服务器操作系统是不是darwin
 */
module.exports.isDarwin = function () {
	return exports.getOS().indexOf('darwin') >= 0;
};

/**
 * 列出目录所有文件
 * @paeam path 目录路径
 */
module.exports.ls = function (path) {
	return fs.readdirSync(path);
};

/**
 * 加载properties文件
 * @param filename 文件名
 */
module.exports.loadProperties = function (filename) {
	let data;
	let array;
	let properties = {};
	if (this.isWindows()) {
		data = fs.readFileSync(__dirname + '/../../../../gu-core/src/resources/' + filename);
		array = data.toString().split('\r\n');
	} else if (this.isDarwin()) {
		data = fs.readFileSync(__dirname + '/../../../../gu-core/src/resources/' + filename);
		data = data.toString('utf8', 0, data.length);
		array = data.toString().split('\n');
	} else {
		data = fs.readFileSync(__dirname + '/../../../../etc/' + filename);
		array = data.toString().split('\n');
	}
	for (let i in array) {
		let str = array[i].toString();
		if (parseInt(str.indexOf('#')) > -1) {
			continue;
		}
		let index = parseInt(str.indexOf('='));
		if (index <= -1) {
			continue;
		}
		let key = str.substring(0, index).toString().trim();
		let value = str.substring(index + 1).toString().trim();
		properties[key] = value;
	}
	return properties;
};

/**
 * 加载json文件
 * @param filename 文件名
 */
module.exports.loadJSONFile = function (filename) {
	let data;
	let array;
	if (filesystem.isWindows()) {
		data = fs.readFileSync(CoreDir + '/../gu-core/src/resources/' + filename);
		array = data.toString();
	} else if (filesystem.isDarwin()) {
		data = fs.readFileSync(CoreDir + '/../gu-core/src/resources/' + filename);
		data = data.toString('utf8', 0, data.length);
		array = data.toString();
	} else {
		data = fs.readFileSync(CoreDir + '/../etc/' + filename);
		array = data.toString();
	}
	return JSON.parse(array);
};