// 数据库配置文件
var config = function (database) {
	if (parseInt(database.indexOf('gu_')) > -1) {
		database = database.replace('gu_', '');
	}
	database = 'jdbc.' + database.replace('_', '.') + '.url';
	var url = properties[database].toString();
	url = url.substring(url.indexOf('//') + 2, url.indexOf('?'));
	var arr = url.split('/');
	database = database.replace('jdbc.', '').replace('.url', '').replace('.', '_');
	return {
		host: arr[0],
		port: "3306",
		user: properties['jdbc.username'],
		password: properties['jdbc.password'],
		charset: "utf8",
		supportBigNumbers: true, // 数据库支持bigint或decimal类型列时，需要设此option为true
		bigNumberStrings: true, // supportBigNumbers和bigNumberStrings启用 强制bigint或decimal列以JavaScript字符串类型返回
		multipleStatement: true, // 是否许一个query中有多个MySQL语句
		connectionLimit: 10, // 连接数限制
		database: parseInt(database.indexOf('gu')) > -1 ? database : 'gu_' + database
	}
};

module.exports = {
	gu: config("gu"),
	guSlave: config("gu"),
	guLog: config("gu_log"),
	guAdmin: config("gu_admin"),
	guConfig: config("gu_config")
};