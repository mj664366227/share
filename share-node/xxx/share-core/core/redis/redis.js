// redis类
import logger from '../util/logUtil';
var LOGGER = logger.getLogger(module.filename);
import redis from 'redis';

/**
 * key不是字符串抛出错误
 * @param key
 */
function RedisKeyIsString(key) {
	if (!_.isString(key)) {
		throw new Error("redis key is not a String : " + key);
	}
	if (key.indexOf("undefined") >= 0 || key.indexOf("NaN") >= 0 || key.indexOf("null") >= 0) {
		throw new Error("redis key is a Error String : " + key);
	}
}

module.exports = function (config) {
	var client = redis.createClient(config.port, config.host);
	client.on("error", function (e) {
		LOGGER.error("redis error : ", e);
	});
	LOGGER.warn('connect to redis %s success!', config.host);
	return {
		KEYS: {
			/**
			 * exists
			 * @param key
			 */
			exists: function (key) {
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.exists(key, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * 设置键过期
			 * @param key
			 * @param seconds
			 */
			expire: function (key, seconds) {
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.expire(key, seconds, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * 删除键
			 * @param key ...
			 */
			del: function (key) {
				client.del(key);
			}
		},
		STRINGS: {
			/**
			 * get
			 * @param key
			 */
			get: function (key) {
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.get(key, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * incrby
			 * @param key
			 * @param number
			 */
			incrby: function (key, number) {
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.incrby(key, number, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * incrbyfloat
			 * @param key
			 * @param number
			 */
			incrbyfloat: function (key, number) {
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.incrbyfloat(key, number, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * set
			 * @param key
			 * @param value
			 */
			set: function (key, value) {
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.set(key, value, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * setex
			 * @param key
			 * @param seconds
			 * @param value
			 */
			setex: function (key, seconds, value) {
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.setex(key, seconds, value, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * mget
			 * @param key
			 */
			mget: function (...key) {
				if (key.length == 1) {
					key = key[0];
				}
				return new Promise(function (resolve, reject) {
					key.forEach(function (k) {
						RedisKeyIsString(k);
					});
					client.mget(key, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * msetex
			 * @param keysvalues(Map&lt;String,String&gt;())
			 * @param seconds
			 */
			msetex: function (keysvalues, seconds) {
				keysvalues.forEach(function (value, key) {
					RedisKeyIsString(key);
					client.setex(key, seconds, value, function (err, reply) {
						if (err) {
							LOGGER.error(err);
						}
					});
				});
			}
		},
		SORTSET: {
			/**
			 * zscore
			 * @param key
			 * @param number
			 */
			zscore: function (key, number) {
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.zscore(key, number, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * zcard
			 * @param key
			 */
			zcard: function (key) {
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.zcard(key, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * zadd
			 * @param key
			 * @param args = score,member,... / [score,member,...]
			 */
			zadd: function (key, ...args) {
				if (args.length == 1) {
					args = args[0];
				}
				if (_.indexOf(args, undefined) >= 0 || _.indexOf(args, NaN) >= 0 || _.indexOf(args, null) >= 0) {
					throw new Error("redis args is a Error, key :" + key + ", args :" + args);
				}
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.zadd(key, args, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * zrem
			 * @param key
			 * @param args = member,... / [member,...]
			 */
			zrem: function (key, ...args) {
				if (args.length == 1) {
					args = args[0];
				}
				if (_.indexOf(args, undefined) >= 0 || _.indexOf(args, NaN) >= 0 || _.indexOf(args, null) >= 0) {
					throw new Error("redis args is a Error, key :" + key + ", args :" + args);
				}
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.zrem(key, args, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * zrevrange 按score值递减(从大到小)来排列
			 * @param key
			 * @param start 开始索引
			 * @param stop 结束索引
			 */
			zrevrange: function (key, start, stop) {
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.zrevrange(key, start, stop, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			},
			/**
			 * zrange 按score值递减(从小到大)来排列
			 * @param key
			 * @param start 开始索引
			 * @param stop 结束索引
			 */
			zrange: function (key, start, stop) {
				return new Promise(function (resolve, reject) {
					RedisKeyIsString(key);
					client.zrange(key, start, stop, function (err, reply) {
						if (err) {
							LOGGER.error(err);
							reject(err);
							return;
						}
						resolve(reply);
					});
				}).catch(function (error) {
					LOGGER.error(error);
				});
			}
		}
	};
};