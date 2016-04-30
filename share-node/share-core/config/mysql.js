// 数据库配置文件
var config = function (database) {
	return {
		host: '127.0.0.1',
		port: "3306",
		user: 'root',
		password: 'root',
		charset: "utf8",
		supportBigNumbers: true, // 数据库支持bigint或decimal类型列时，需要设此option为true
		bigNumberStrings: true, // supportBigNumbers和bigNumberStrings启用 强制bigint或decimal列以JavaScript字符串类型返回
		multipleStatement: true, // 是否许一个query中有多个MySQL语句
		connectionLimit: 10, // 连接数限制
		database: database
	}
};

module.exports = {
	share: config("share")
};