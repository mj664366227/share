// gu_config库链接
import config from '../../node-core/framework/config/mysql';
import mysql from '../../node-core/framework/core/database/mysql';
module.exports = mysql(config.guConfig);