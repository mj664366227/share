// mysql类

var mysql = require("mysql");

// mysql初始化
exports.init = function (config) {
	var pool = mysql.createPool(config);
	pool.getConnection(function(err, connection) {
		connection.query('SELECT * FROM `user`', function(err, rows) {
			for (var i = 0; i < rows.length; i++) {
				// console.log(rows[i].nickname);
			}
		});
		connection.release();
	});
};