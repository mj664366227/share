/**
 * 分布式session模块
 */
// redis key
const distributedSessionRedisKey = "gatherupSession:";
// 默认1小时session失效
const maxAge = 86400;

module.exports = {
	/**
	 * 添加分布式session
	 * @param req http请求对象
	 * @param res http返回对象
	 * @param key 键
	 * @param value 值
	 */
	add: async function (req, res, key, value) {
		let distributedSessionKey = await genDistributedSessionKey(req, res, key);
		redis.STRINGS.setex(distributedSessionKey, maxAge, JSON.stringify(value));
	},
	/**
	 * 获取分布式session
	 * @param req http请求对象
	 * @param res http返回对象
	 * @param key 键
	 */
	get: async function (req, res, key) {
		let distributedSessionKey = await genDistributedSessionKey(req, res, key);
		return await redis.STRINGS.get(distributedSessionKey);
	},
	/**
	 * 删除分布式session
	 * @param req http请求对象
	 * @param res http返回对象
	 * @param key 键
	 */
	remove: async function (req, res, key) {
		let distributedSessionKey = await  genDistributedSessionKey(req, res, key);
		redis.KEYS.del(distributedSessionKey);
	}
};

/**
 * 生成分布式session键
 * @param req http请求对象
 * @param res http返回对象
 * @param key session键
 */
async function genDistributedSessionKey(req, res, key) {
	var distributedSessionKey = "";
	var cookies = req.cookies;
	console.log(cookies);

	// cookie路径
	const cookiePath = "/" + properties["project.name"].substring(3);
	// 公共sessionKey
	const distributedSessionGlobalCookieKey = secret.sha1(properties["project.name"] + properties["system.key"]);

	// 如果丢失session，重新生成
	if (!cookies[distributedSessionGlobalCookieKey]) {
		distributedSessionKey = secret.md5(timeUtil.nanoTime());

		// 写入cookie
		res.cookie(distributedSessionGlobalCookieKey, distributedSessionKey, {
			maxAge: maxAge * 1000,
			path: cookiePath
		});
	} else {
		distributedSessionKey = cookies[distributedSessionGlobalCookieKey];
	}

	// 以redis为准，如果redis获取不了数据，就证明session丢失
	var redisKey = distributedSessionRedisKey + distributedSessionKey;
	var isRedisKey = await redis.KEYS.exists(redisKey);
	if (!isRedisKey) {
		redis.KEYS.expire(redisKey, maxAge);
	}
	return redisKey + '_' + key;
}