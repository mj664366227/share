const LOGGER = logUtil.getLogger(module.filename);
module.exports = {
	/**
	 * multiGetT
	 * @param idSet id集合(Set&lt;String/Numbe&gt;())
	 * @param T 对象方法
	 * @returns {Map.<Number,t>} Map&lt;Number,T&gt;()
	 */
	multiGetT: async function (idSet, T) {
		idSet.delete("-1");
		idSet.delete(-1);
		if (idSet.size == 0) {
			return new Map();
		}
		var clazz = _.getPojoClass(T);
		if (!Key[clazz]) {
			throw new Error("commonService.multiGetT Key is not find, clazz : " + clazz);
		}
		// 用哪个数据库的dbService
		var dbService = Key[clazz].dbService;
		// 获取redisKey
		var redisKey = Key[clazz].redisKey;
		// 获取sqlKey
		var sqlKey = Key[clazz].sqlKey;

		// 首先去缓存拿
		var data = new Map();
		var redisKeyList = [];
		idSet.forEach(function (id) {
			redisKeyList.push(redisKey + id);
			data.set(_.toNumber(id), null);
		});
		var byteList = await redis.STRINGS.mget(redisKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		if (byteList.length > 0) {
			byteList.forEach(function (byte) {
				if (byte == null) {
					return;
				}
				var t = new T();
				var b = JSON.parse(byte);
				_.forEach(b, function (v, k) {
					if (_.isUndefined(t[clazz][k])) {
						// 缓存有,程序没有的字段,忽略
						LOGGER.error(clazz + "." + k + "  is undefined");
						return;
					}
					t[clazz][k] = b[k];
				});
				data.set(t[clazz][sqlKey], t);
			});
		}

		// 找出缓存没有的
		var tmpUserIdSet = new Set();
		data.forEach(function (v, k) {
			if (v == null) {
				tmpUserIdSet.add(k);
			}
		});
		if (tmpUserIdSet.size == 0) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		var tList = await dbService.multiGetT(tmpUserIdSet, T, sqlKey);
		if (tList.length == 0) {
			// 如果数据库都没有，那就直接返回
			return data;
		}

		// 如果数据库查到就回写到缓存
		var keysValuesMap = new Map();
		tList.forEach(function (t) {
			if (_.isString(t[clazz][sqlKey]) && t[clazz][sqlKey].trim() == "") {
				LOGGER.error(clazz + "." + sqlKey + "  is ''");
			}
			if (t[clazz][sqlKey] == 0 || t[clazz][sqlKey] == null || t[clazz][sqlKey] == undefined) {
				LOGGER.error(clazz + "." + sqlKey + "  is undefined/null/0");
				return;
			}
			keysValuesMap.set(redisKey + t[clazz][sqlKey], JSON.stringify(t[clazz]));
			data.set(t[clazz][sqlKey], t);
		});
		redis.STRINGS.msetex(keysValuesMap, timeUtil.day2Second(30));
		return data;
	},
	/**
	 * addSortSetAnchor
	 * @param key
	 */
	addSortSetAnchor: function (key) {
		redis.SORTSET.zadd(key, -1, "-1");
	},
	/**
	 * getNumColumn
	 * @param numKey
	 * @param id
	 */
	getNumColumn: async function (numKey, id) {
		if (!_.isNumber(id) || !id) {
			throw new Error("id is undefined or NaN");
		}
		var countStr = await redis.STRINGS.get(numKey.getKey(id));
		if (countStr != null) {
			return _.toNumber(countStr);
		}
		var count = 0;
		var sql = "SELECT `" + numKey.column + "` FROM `" + numKey.table + "` WHERE `" + numKey.pk + "`=?";
		var list = await guSlaveDbService.query(sql, id);
		if (_.size(list) > 0) {
			count = list[0][numKey.column];
		}
		this.setNumColumn(numKey, id, count);
		return count;
	},
	/**
	 * setNumColumn
	 * @param numKey
	 * @param id
	 * @param num
	 */
	setNumColumn: function (numKey, id, num) {
		if (!_.isNumber(id) || !id) {
			throw new Error("id is undefined or NaN");
		}
		if (numKey.seconds > 0) {
			redis.STRINGS.setex(numKey.getKey(id), numKey.seconds, num);
		} else {
			redis.STRINGS.set(numKey.getKey(id), num);
		}
	},
	/**
	 * updateNumColumn
	 * @param numKey
	 * @param id
	 * @param num
	 */
	updateNumColumn: async function (numKey, id, num) {
		if (!_.isNumber(id) || !id) {
			throw new Error("id is undefined or NaN");
		}
		if (num == 0) {
			return -1;
		}
		await this.getNumColumn(numKey, id);
		var sql = "UPDATE `" + numKey.table + "` SET `" + numKey.column + "`=`" + numKey.column + "`+? WHERE `" + numKey.pk + "`=?";
		var boolean = await guSlaveDbService.update(sql, num, id);
		if (boolean) {
			return redis.STRINGS.incrbyfloat(numKey.getKey(id), num);
		}
		return -1;
	},
	/**
	 * 批量获取统计字段的值(不同字段不同id同table)
	 * @param idSet id集合
	 * @param numKeys key枚举
	 */
	multiGetNumColumn: async function (idSet, ...numKeys) {
		idSet.delete("-1");
		if (idSet.size == 0) {
			return new Map();
		}

		// 组成key
		var keyList = [];
		var data = new map();
		idSet.forEach(function (id) {
			numKeys.forEach(function (numKey) {
				let numKeyMap = new Map();
				keyList.push(numKey.getKey(id));
				numKeyMap.set(numKey, null);
				data.set(_.toNumber(id), numKeyMap);
			});
		});

		var jsonStringList = await redis.STRINGS.mget(keyList);
		// 判断哪些缓存是有的，哪些是缓存没有的
		if (jsonStringList.length > 0) {
			var i = 0;
			data.forEach(function (v_map, k_id) {
				v_map.forEach(function (v, k) {
					let jsonString = jsonStringList[i];
					i = i + 1;
				});
			});
		}
	}
};
