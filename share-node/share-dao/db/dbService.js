// gu_admin库链接
import config from '../../share-core/framework/config/mysql';
import mysql from '../../share-core/framework/core/database/mysql';
module.exports = mysql(config.guAdmin);