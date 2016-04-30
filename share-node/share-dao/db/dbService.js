// gu_admin库链接
import config from '../../share-core/config/mysql';
import mysql from '../../share-core/core/mysql/mysql';
module.exports = mysql(config.share);