var core = require('./core/core');
var filesystem = require('./core/util/filesystem');
var httpServer = require('./core/server/httpServer');
var demoController = require('./controller/demoController');
var config = require('./config/config');
var database = require('./config/database');
var base64 = require('./core/util/base64');

var requestMapping = {};
requestMapping["/start"] = demoController.start;
requestMapping["/upload"] = demoController.upload;
for ( var fun in requestMapping) {
	console.log("requestMapping: " + fun);
}

core.init();

// httpServer.start(9999, requestMapping);

var mysql = require("mysql");
var pool = mysql.createPool(database.config);
pool.getConnection(function(err, connection) {
	connection.query('SELECT * FROM `user`', function(err, rows) {
		for (var i = 0; i < rows.length; i++) {
			// console.log(rows[i].nickname);
		}
	});
	connection.release();
});