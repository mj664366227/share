// gu_admin库链接
let config = require(CoreDir + '/config/mysql');
let mysql = require(CoreDir + '/core/mysql/mysql');
module.exports = mysql(config.share);