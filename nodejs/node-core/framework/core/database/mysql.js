// mysql类
import logger from '../util/logUtil';
let LOGGER = logger.getLogger(module.filename);
import mysql from "mysql";

/**
 * 程序字段->数据库字段(adminPhoneId->admin_phone_id)
 * @param fieldName
 */
function fieldNameToColumnName(fieldName) {
	var columnName = "";
	var l = fieldName.length;
	for (let i = 0; i < l; i++) {
		let c = fieldName.charAt(i);
		if (i > 0 && c.charCodeAt(0) >= 65 && c.charCodeAt(0) <= 90) {
			columnName = columnName + "_";
			columnName = columnName + c;
		} else {
			columnName = columnName + c;
		}
	}
	return columnName.toLowerCase();
}

/**
 * pojo类型->成表名(DAdminPhoneId->admin_phone_id)
 * @param className
 */
function classNameToTableName(className) {
	var tableName = "";
	var l = className.length;
	for (let i = 1; i < l; i++) {
		let c = className.charAt(i);
		if (i > 2 && c.charCodeAt(0) >= 65 && c.charCodeAt(0) <= 90) {
			tableName = tableName + "_";
			tableName = tableName + c;
		} else {
			tableName = tableName + c;
		}
	}
	return tableName.toLowerCase();
}

module.exports = function (config) {
	let pool = mysql.createPool(config);
	LOGGER.warn('connect to mysql %s, database \'%s\' success!', pool.config.connectionConfig.host, pool.config.connectionConfig.database);
	return {
		/**
		 * 查询数据
		 * @param sql sql语句
		 * @param args 参数数组
		 */
		query: function (sql, ...args) {
			return new Promise(function (resolve, reject) {
				pool.getConnection(function (err, conn) {
					if (err) {
						reject(err);
						return;
					}
					conn.query(sql, args, function (err, data) {
						if (err) {
							reject(err);
						} else {
							resolve(data);
						}
						conn.release();
					});
				});
			}).catch(function (error) {
				LOGGER.error(error);
			});
		},
		/**
		 * 更新数据
		 * @param sql
		 * @param args
		 * @returns {Promise.<Boolean>} 布尔值
		 */
		update: function (sql, ...args) {
			return new Promise(function (resolve, reject) {
				pool.getConnection(function (err, conn) {
					if (err) {
						reject(err);
						return;
					}
					conn.query(sql, args, function (err, data) {
						if (err) {
							reject(err);
						} else {
							resolve(data.affectedRows > 0);
						}
						conn.release();
					});
				});
			}).catch(function (error) {
				LOGGER.error(error);
			});
		},
		/**
		 * 保存数据
		 * @param t 对象实体
		 * @returns {Promise.<t>} 对象实体
		 */
		save: function (t) {
			// 主键是否是id
			var idIsPrimary = false;
			var args = [];
			var clazz = _.getPojoObjectClass(t);
			var value_map = _.values(t)[0];
			var sql = 'insert into `' + classNameToTableName(clazz) + '` (';
			var value_wenhao = "";
			_.forEach(value_map, function (value, k_field) {
				if (k_field == "id") {
					idIsPrimary = true;
					return;
				}
				sql = sql + '`' + fieldNameToColumnName(k_field) + '`,';
				value_wenhao = value_wenhao + "?,";
				args.push(value);
			});
			sql = sql.substr(0, sql.length - 1) + ') values (';
			sql = sql + value_wenhao.substr(0, value_wenhao.length - 1) + ")";

			return new Promise(function (resolve, reject) {
				pool.getConnection(function (err, conn) {
					if (err) {
						reject(err);
						return;
					}
					conn.query(sql, args, function (err, data) {
						if (err) {
							reject(err);
						} else {
							// 主键是id
							if (idIsPrimary) {
								var id = data.insertId;
								if (id <= 0) {
									resolve(null);
								}
								t[clazz].id = id;
								resolve(t);
							}
							resolve(t);
						}
						conn.release();
					});
				});
			}).catch(function (error) {
				LOGGER.error(error);
			});
		},
		/**
		 * 更新数据
		 * @param t 对象实体
		 * @returns {Promise.<Boolean>} 布尔值
		 */
		updateT: function (t) {
			var args = [];
			var clazz = _.getPojoObjectClass(t);
			var value_map = _.values(t)[0];
			var table = classNameToTableName(clazz);
			var columnMap = NumKey.tableColumnMap[table];
			var sql = "update `" + table + "` set ";
			_.forEach(value_map, function (value, k_field) {
				var column = fieldNameToColumnName(k_field);
				if (k_field === "id") {
					return;
				}
				//忽略的统计字段
				if (!_.isUndefined(columnMap) && !_.isUndefined(columnMap[column])) {
					return;
				}
				sql = sql + '`' + column + '`=?,';
				args.push(value);
			});
			sql = sql.substr(0, sql.length - 1) + " where `id`=?";
			args.push(t[clazz].id);

			return new Promise(function (resolve, reject) {
				pool.getConnection(function (err, conn) {
					if (err) {
						reject(err);
						return;
					}
					conn.query(sql, args, function (err, data) {
						if (err) {
							reject(err);
						} else {
							resolve(data.affectedRows > 0);
						}
						conn.release();
					});
				});
			}).catch(function (error) {
				LOGGER.error(error);
			});
		},
		/**
		 * 查询数据对象
		 * @param sql sql语句
		 * @param T 对象方法
		 * @param args 参数数组
		 * @returns {Promise.<t>} 对象实体
		 */
		queryT: function (sql, T, ...args) {
			return new Promise(function (resolve, reject) {
				pool.getConnection(function (err, conn) {
					if (err) {
						reject(err);
						return;
					}
					conn.query(sql, args, function (err, data) {
						if (err) {
							reject(err);
						} else {
							if (data.length == 0) {
								resolve(null);
							} else {
								var t = new T();
								var clazz = _.getPojoObjectClass(t);
								var value_map = _.values(t)[0];
								var dataT = data[0];
								_.forEach(value_map, function (value, k_field) {
									var column = fieldNameToColumnName(k_field);
									var columnValue = dataT[column];
									if (_.isUndefined(columnValue)) {
										reject(new Error("Invalid column : " + column + ", table : " + clazz));
									}
									if (_.isNumber(t[clazz][k_field])) {
										t[clazz][k_field] = _.toNumber(columnValue);
									} else {
										t[clazz][k_field] = columnValue;
									}
								});
								resolve(t);
							}
						}
						conn.release();
					});
				});
			}).catch(function (error) {
				LOGGER.error(error);
			});
		},
		/**
		 * 查询数据对象列表
		 * @param sql sql语句
		 * @param T 对象方法
		 * @param args 参数数组
		 * @returns {Promise.<Array<t>>} 对象实体数组
		 */
		queryTList: function (sql, T, ...args) {
			return new Promise(function (resolve, reject) {
				pool.getConnection(function (err, conn) {
					if (err) {
						reject(err);
						return;
					}
					conn.query(sql, args, function (err, data) {
						if (err) {
							reject(err);
						} else {
							var tList = [];
							_.forEach(data, function (d) {
								var t = new T();
								var clazz = _.getPojoObjectClass(t);
								var value_map = _.values(t)[0];
								_.forEach(value_map, function (value, k_field) {
									var column = fieldNameToColumnName(k_field);
									var columnValue = d[column];
									if (_.isUndefined(columnValue)) {
										reject(new Error("Invalid column : " + column + ", table : " + clazz));
									}
									if (_.isNumber(t[clazz][k_field])) {
										t[clazz][k_field] = _.toNumber(columnValue);
									} else {
										t[clazz][k_field] = columnValue;
									}
								});
								tList.push(t);
							});
							resolve(tList);
						}
						conn.release();
					});
				});
			}).catch(function (error) {
				LOGGER.error(error);
			});
		},
		/**
		 * 批量获取T
		 * @param idSet id集合(Set&lt;Number/String&gt;())
		 * @param T 对象方法
		 * @param sqlKey 数据库主键(程序字段格式)
		 * @returns {Promise.<Array<t>>} 对象实体数组
		 */
		multiGetT: function (idSet, T, sqlKey) {
			if (!_.isEqual(sqlKey, "id")) {
				sqlKey = fieldNameToColumnName(sqlKey);
			}
			var clazz = _.getPojoClass(T);
			var table = classNameToTableName(clazz);
			var sql = "select * from `" + table + "` where `" + sqlKey + "` in (" + _.join(Array.from(idSet), ",") + ")";
			return new Promise(function (resolve, reject) {
				pool.getConnection(function (err, conn) {
					if (err) {
						reject(err);
						return;
					}
					conn.query(sql, function (err, data) {
						if (err) {
							reject(err);
						} else {
							var TList = [];
							_.forEach(data, function (d) {
								var t = new T();
								var clazz = _.getPojoObjectClass(t);
								var value_map = _.values(t)[0];
								_.forEach(value_map, function (value, k_field) {
									var column = fieldNameToColumnName(k_field);
									var columnValue = d[column];
									if (_.isUndefined(columnValue)) {
										reject(new Error("Invalid column : " + column + ", table : " + clazz));
									}
									if (_.isNumber(t[clazz][k_field])) {
										t[clazz][k_field] = _.toNumber(columnValue);
									} else {
										t[clazz][k_field] = columnValue;
									}
								});
								TList.push(t);
							});
							resolve(TList);
						}
						conn.release();
					});
				});
			}).catch(function (error) {
				LOGGER.error(error);
			});
		}
	}
};