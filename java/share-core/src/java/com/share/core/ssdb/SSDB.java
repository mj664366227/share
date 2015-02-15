package com.share.core.ssdb;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.nutz.ssdb4j.SSDBs;
import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * ssdb类
 * @author ruan
 */
public class SSDB {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(SSDB.class);
	/**
	 * ssdb对象
	 */
	private org.nutz.ssdb4j.spi.SSDB ssdb;
	/** 
	 * kv
	 */
	public Kv KV = new Kv();
	/** 
	 * hash
	 */
	public Hash HASH = new Hash();
	/** 
	 * zset
	 */
	public Zset ZSET = new Zset();
	/** 
	 * queue
	 */
	public Queue QUEUE = new Queue();
	/** 
	 * server
	 */
	public Server SERVER = new Server();
	/**
	 * ssdb地址
	 */
	@Value("${ssdb.host}")
	private String host;
	/**
	 * ssdb端口
	 */
	@Value("${ssdb.port}")
	private int port;
	/**
	 * ssdb密码
	 */
	@Value("${ssdb.password}")
	private String password;
	/**
	 * ssdb连接超时时间
	 */
	@Value("${ssdb.timeout}")
	private int timeout;
	/**
	 * 最小空闲连接数
	 */
	@Value("${ssdb.minIdle}")
	private int minIdle;
	/**
	 * 最大空闲连接数
	 */
	@Value("${ssdb.maxIdle}")
	private int maxIdle;
	/**
	 * 获取一个实例的最大等待时间(毫秒)
	 */
	@Value("${ssdb.maxWait}")
	private int maxWait;
	/**
	 * 可分配最大实例数
	 */
	@Value("${ssdb.maxTotal}")
	private int maxTotal;

	/**
	 * 构造函数
	 */
	private SSDB() {
	}

	/**
	 * 初始化	
	 */
	public void init() {
		Config config = new Config();
		config.minIdle = minIdle;
		config.maxIdle = maxIdle;
		config.maxActive = maxTotal;
		config.maxWait = maxWait;

		ssdb = SSDBs.pool(host, port, timeout, config);
		logger.info("ssdb init " + host + ":" + port);

		if (!password.isEmpty() && !SERVER.auth(password)) {
			throw new SSDBException("invalid password");
		}

		Map<String, String> infoMap = SERVER.info();
		if (infoMap == null || infoMap.isEmpty()) {
			throw new SSDBException("could not return the server info");
		}
		logger.info("ssdb server version: " + infoMap.get("version"));
	}

	/**
	 * 关闭方法
	 */
	public void close() {
		try {
			ssdb.close();
		} catch (IOException e) {
			logger.error("", e);
		}
		logger.info("ssdb closed");
	}

	public class Kv {
		private Kv() {
		}

		/**
		 * 设置一个kv值
		 * @param key
		 * @param value
		 */
		public boolean set(Object key, Object value) {
			try {
				Response response = ssdb.set(key, value);
				return response.asInt() == 1;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 设置一组kv值
		 * @author ruan
		 * @param kv
		 * @return 成功插入的kv对的数量
		 */
		public int set(Map<Object, Object> kv) {
			int size = kv.size();
			if (kv == null || size <= 0) {
				return 0;
			}
			int i = 0;
			Object[] pairs = new Object[size * 2];
			for (Entry<Object, Object> e : kv.entrySet()) {
				pairs[i++] = e.getKey();
				pairs[i++] = e.getValue();
			}
			try {
				Response response = ssdb.multi_set(pairs);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 设置一个key-val对,并设置过期时间,单位为秒
		 * @author ruan
		 * @param key
		 * @param value
		 * @param ttl
		 * @return
		 */
		public boolean setx(Object key, Object value, int ttl) {
			try {
				Response response = ssdb.setx(key, value, ttl);
				return response.asInt() == 1;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 设置 key(只针对 KV 类型)的存活时间
		 * @author ruan
		 * @param key
		 * @param ttl
		 * @return
		 */
		public boolean expire(Object key, int ttl) {
			try {
				Response response = ssdb.expire(key, ttl);
				return response.asInt() == 1;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 返回 key(只针对 KV 类型) 的存活时间
		 * @author ruan
		 * @param key
		 * @return
		 */
		public int ttl(Object key) {
			try {
				Response response = ssdb.ttl(key);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 如果key不存在,就执行set操作
		 * @author ruan
		 * @param key
		 * @param value
		 * @return
		 */
		public boolean setnx(Object key, Object value) {
			try {
				Response response = ssdb.setnx(key, value);
				return response.asInt() == 1;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 根据key获取一个值
		 * @author ruan
		 * @param key
		 * @return
		 */
		public String get(Object key) {
			try {
				Response response = ssdb.get(key);
				return response.asString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 根据key获取值
		 * @author ruan
		 * @param keys
		 * @return
		 */
		public String get(Object... keys) {
			try {
				Response response = ssdb.multi_get(keys);
				return response.asString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 更新 key 对应的 value, 并返回旧的 value
		 * @author ruan
		 * @param key
		 * @param value
		 * @return
		 */
		public String getset(Object key, Object value) {
			try {
				Response response = ssdb.getset(key, value);
				return response.asString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 针对kv，删除一个key
		 * @author ruan
		 * @param key
		 * @return
		 */
		public void del(Object key) {
			try {
				ssdb.del(key);
			} catch (SSDBException e) {
				logger.error("", e);
			}
		}

		/**
		 * 针对kv，删除一堆key
		 * @author ruan
		 * @param keys
		 * @return
		 */
		public void del(Object... keys) {
			try {
				ssdb.multi_del(keys);
			} catch (SSDBException e) {
				logger.error("", e);
			}
		}

		/**
		 * 自增
		 * @author ruan
		 * @param key
		 * @param value
		 */
		public long incr(Object key, int value) {
			try {
				Response response = ssdb.incr(key, value);
				return response.asLong();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 判断指定的 key 是否存在
		 * @author ruan
		 * @param key
		 * @return
		 */
		public boolean exists(Object key) {
			try {
				Response response = ssdb.exists(key);
				return response.asInt() == 1;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 获取字符串内指定位置的位值(BIT)
		 * @author ruan
		 * @param key
		 * @param offset 位偏移
		 * @return
		 */
		public int getbit(Object key, int offset) {
			try {
				Response response = ssdb.getbit(key, offset);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 设置字符串内指定位置的位值(BIT), 字符串的长度会自动扩展
		 * @author ruan
		 * @param key
		 * @param offset 位偏移
		 * @param on 0 或 1
		 * @return 返回原来的位值. 如果 val 不是 0 或者 1, 返回 false	
		 */
		public int setbit(Object key, int offset, byte on) {
			try {
				Response response = ssdb.setbit(key, offset, on);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 计算字符串的子串所包含的位值为 1 的个数. 若 start 是负数, 则从字符串末尾算起. 若 size 是负数, 则表示从字符串末尾算起, 忽略掉那么多字节
		 * @author ruan
		 * @param key
		 * @param start 子串的字节偏移
		 * @param size 子串的长度(字节数), 默认为到字符串最后一个字节
		 * @return 返回位值为 1 的个数. 出错返回 false
		 */
		public int countbit(Object key, int start, int size) {
			try {
				Response response = ssdb.countbit(key, start, size);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 获取字符串的子串. 若 start 是负数, 则从字符串末尾算起. 若 size 是负数, 则表示从字符串末尾算起, 忽略掉那么多字节
		 * @author ruan
		 * @param key
		 * @param start 子串的字节偏移
		 * @param size 子串的长度(字节数), 默认为到字符串最后一个字节
		 * @return
		 */
		public String substr(Object key, int start, int size) {
			try {
				Response response = ssdb.substr(key, start, size);
				return response.asString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 计算字符串的长度(字节数)
		 * @author ruan
		 * @param key
		 * @return 返回字符串的长度, key 不存在则返回 0
		 */
		public int strlen(Object key) {
			try {
				Response response = ssdb.strlen(key);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 列出处于区间 (key_start, key_end] 的 key 列表
		 * @author ruan
		 * @param start 返回的起始 key(不包含), 空字符串表示 -inf
		 * @param end 返回的结束 key(包含), 空字符串表示 +inf
		 * @param limit 最多返回这么多个元素
		 * @return 如果出错则返回 false, 否则返回包含 key 的数组
		 */
		public List<String> keys(Object start, Object end, int limit) {
			try {
				Response response = ssdb.keys(start, end, limit);
				return response.listString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 列出处于区间 (key_start, key_end] 的 key-value 列表
		 * @author ruan
		 * @param start 返回的起始 key(不包含), 空字符串表示 -inf
		 * @param end 返回的结束 key(包含), 空字符串表示 +inf
		 * @param limit 最多返回这么多个元素
		 * @return 如果出错则返回 false, 否则返回包含 key-value 的数关联组
		 */
		public Map<String, String> scan(Object start, Object end, int limit) {
			try {
				Response response = ssdb.scan(start, end, limit);
				return response.mapString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 列出处于区间 (key_start, key_end] 的 key-value 列表，反向顺序
		 * @author ruan
		 * @param start 返回的起始 key(不包含), 空字符串表示 -inf
		 * @param end 返回的结束 key(包含), 空字符串表示 +inf
		 * @param limit 最多返回这么多个元素
		 * @return 如果出错则返回 false, 否则返回包含 key-value 的数关联组
		 */
		public Map<String, String> rscan(Object start, Object end, int limit) {
			try {
				Response response = ssdb.rscan(start, end, limit);
				return response.mapString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}
	}

	public class Hash {
		private Hash() {
		}

		/**
		 * 设置 hashmap 中指定 key 对应的值内容
		 * @author ruan
		 * @param key
		 * @param field
		 * @param value
		 * @return
		 */
		public boolean hset(Object key, Object field, Object value) {
			try {
				Response response = ssdb.hset(key, field, value);
				return response.asInt() == 1;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 设置 hashmap 中指定 key 对应的值内容
		 * @param key
		 * @param kv
		 * @return 成功插入的kv对数量
		 */
		public int hset(Object key, Map<Object, Object> kv) {
			int size = kv.size();
			if (kv == null || size <= 0) {
				return 0;
			}
			int i = 0;
			Object[] pairs = new Object[size * 2];
			for (Entry<Object, Object> e : kv.entrySet()) {
				pairs[i++] = e.getKey();
				pairs[i++] = e.getValue();
			}
			try {
				Response response = ssdb.multi_hset(key, pairs);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 获取 hashmap 中指定 key 的值内容
		 * @author ruan
		 * @param key
		 * @param field
		 * @return
		 */
		public String hget(Object key, Object field) {
			try {
				Response response = ssdb.hget(key, field);
				return response.asString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 获取 hashmap 中指定 key 的值内容
		 * @author ruan
		 * @param key
		 * @param fields
		 * @return 如果出错则返回 false, 否则返回包含 key-value 的关联数组, 如果某个 key 不存在, 则它不会出现在返回数组中
		 */
		public Map<String, String> hget(Object key, Object... fields) {
			try {
				Response response = ssdb.multi_hget(key, fields);
				return response.mapString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 删除 hashmap 中的指定 key
		 * @author ruan
		 * @param key
		 * @param field
		 * @return 删除成功返回true，删除失败(一般来说是key不存在)返回false
		 */
		public boolean hdel(Object key, Object field) {
			try {
				Response response = ssdb.hdel(key, field);
				return response.asInt() == 1;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 删除 hashmap 中的指定 key
		 * @author ruan
		 * @param key
		 * @param fields
		 * @return 出错则返回 false, 其它值表示正常
		 */
		public boolean hdel(Object key, Object... fields) {
			try {
				Response response = ssdb.multi_del(fields);
				return response.asInt() > 0;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 使 hashmap 中的 key 对应的值增加 num. 参数 num 可以为负数. 如果原来的值不是整数(字符串形式的整数), 它会被先转换成整数
		 * @author ruan
		 * @param key
		 * @param field
		 * @param value
		 * @return 如果出错则返回 false, 否则返回新的值
		 */
		public int hincr(Object key, Object field, int value) {
			try {
				Response response = ssdb.hincr(key, field, value);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 判断指定的 key 是否存在于 hashmap 中
		 * @author ruan
		 * @param key
		 * @param field
		 * @return
		 */
		public boolean hexists(Object key, Object field) {
			try {
				Response response = ssdb.hexists(key, field);
				return response.asInt() == 1;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 返回 hashmap 中的元素个数
		 * @author ruan
		 * @param key
		 * @return 出错则返回 false, 否则返回元素的个数, 0 表示不存在 hashmap(空)
		 */
		public long hsize(Object key) {
			try {
				Response response = ssdb.hsize(key);
				return response.asLong();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 列出名字处于区间 (name_start, name_end] 的 hashmap 名字
		 * @param start
		 * @param end
		 * @param limit
		 * @return
		 */
		public List<String> hlist(Object start, Object end, int limit) {
			try {
				Response response = ssdb.hlist(start, end, limit);
				return response.listString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 反序列出名字处于区间 (name_start, name_end] 的 hashmap 名字
		 * @param start
		 * @param end
		 * @param limit
		 * @return
		 */
		public List<String> hrlist(Object start, Object end, int limit) {
			try {
				Response response = ssdb.hrlist(start, end, limit);
				return response.listString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 列出 hashmap 中处于区间 (key_start, key_end] 的 key 列表
		 * @param key
		 * @param start
		 * @param end
		 * @param limit
		 * @return
		 */
		public List<String> hkeys(Object key, Object start, Object end, int limit) {
			try {
				Response response = ssdb.hkeys(key, start, end, limit);
				return response.listString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 返回整个 hashmap
		 * @param key
		 * @return
		 */
		public Map<String, String> hgetall(Object key) {
			try {
				Response response = ssdb.hgetall(key);
				return response.mapString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 列出 hashmap 中处于区间 (key_start, key_end] 的 key-value 列表
		 * @param key
		 * @param start
		 * @param end
		 * @param limit
		 * @return
		 */
		public Map<String, String> hscan(Object key, Object start, Object end, int limit) {
			try {
				Response response = ssdb.hscan(key, start, end, limit);
				return response.mapString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 反序列出 hashmap 中处于区间 (key_start, key_end] 的 key-value 列表
		 * @param key
		 * @param start
		 * @param end
		 * @param limit
		 * @return
		 */
		public Map<String, String> hrscan(Object key, Object start, Object end, int limit) {
			try {
				Response response = ssdb.hrscan(key, start, end, limit);
				return response.mapString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 删除 hashmap 中的所有 key
		 * @param key
		 * @return
		 */
		public boolean hclear(Object key) {
			try {
				Response response = ssdb.hclear(key);
				return response.asInt() == 1;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}
	}

	public class Zset {
		private Zset() {
		}

		/**
		 * 设置 zset 中指定 member 对应的权重值
		 * @author ruan
		 * @param key
		 * @param member
		 * @param score
		 * @return 
		 */
		public boolean zset(Object key, Object member, long score) {
			try {
				ssdb.zset(key, member, score);
				return true;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 设置 zset 中指定 member 对应的权重值
		 * @author ruan
		 * @param key
		 * @param memberScore member => score map
		 * @return 成功插入的数据条数
		 */
		public int zset(Object key, Map<Object, Object> memberScore) {
			int size = memberScore.size();
			if (memberScore == null || size <= 0) {
				return 0;
			}
			int i = 0;
			Object[] pairs = new Object[size * 2];
			for (Entry<Object, Object> e : memberScore.entrySet()) {
				pairs[i++] = e.getKey();
				pairs[i++] = e.getValue();
			}
			try {
				Response response = ssdb.multi_zset(key, pairs);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 获取 zset 中指定 member 的权重值
		 * @author ruan
		 * @param key
		 * @param member
		 * @return
		 */
		public double zget(Object key, Object member) {
			try {
				Response response = ssdb.zget(key, member);
				if (response.datas.isEmpty()) {
					return 0;
				}
				return Double.parseDouble(new String(response.datas.get(0)).trim());
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 获取 zset 中指定 member 的权重值
		 * @author ruan
		 * @param key
		 * @param members
		 * @return
		 */
		public Map<String, String> zget(Object key, Object... members) {
			try {
				Response response = ssdb.multi_zget(key, members);
				return response.mapString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 删除 zset 中的指定 member
		 * @author ruan
		 * @param key
		 * @param member
		 * @return 存在返回true，不存在返回false
		 */
		public boolean zdel(Object key, Object member) {
			try {
				Response response = ssdb.zdel(key, member);
				return response.asInt() >= 1;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 删除 zset 中的指定 member
		 * @author ruan
		 * @param key
		 * @param members
		 * @return 成功删除的数据条数
		 */
		public int zdel(Object key, Object... members) {
			try {
				Response response = ssdb.multi_zdel(key, members);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 使 zset 中的 member 对应的值增加 num. 参数 num 可以为负数. 如果原来的值不是整数(字符串形式的整数), 它会被先转换成整数
		 * @author ruan
		 * @param key
		 * @param member
		 * @param value
		 * @return 返回新的值
		 */
		public double zincr(Object key, Object member, int value) {
			try {
				Response response = ssdb.zincr(key, member, value);
				return response.asDouble();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 返回 zset 中的元素个数
		 * @author ruan
		 * @param key
		 * @return
		 */
		public int zsize(Object key) {
			try {
				Response response = ssdb.zsize(key);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 列出名字处于区间 (name_start, name_end] 的 zset
		 * @author ruan
		 * @param start 返回的起始名字(不包含)
		 * @param end 返回的结束名字(包含)
		 * @param limit 最多返回这么多个元素
		 * @return 返回包含名字的数组
		 */
		public List<String> zlist(Object start, Object end, int limit) {
			try {
				Response response = ssdb.zlist(start, end, limit);
				return response.listString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 列出名字处于区间 (name_start, name_end] 的 zset(反序)
		 * @author ruan
		 * @param start 返回的起始名字(不包含)
		 * @param end 返回的结束名字(包含)
		 * @param limit 最多返回这么多个元素
		 * @return 返回包含名字的数组
		 */
		public List<String> zrlist(Object start, Object end, int limit) {
			try {
				Response response = ssdb.zrlist(start, end, limit);
				return response.listString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 判断指定的 member是否存在于 zset 中
		 * @author ruan
		 * @param key
		 * @param member
		 * @return
		 */
		public boolean zexists(Object key, Object member) {
			try {
				Response response = ssdb.zexists(key, member);
				return response.asInt() == 1;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 列出 zset 中处于区间 (key_start+score_start, score_end] 的 key 列表
		 * @author ruan
		 * @param key
		 * @param memberStart score_start 对应的 member
		 * @param scoreStart 最小权重值(不包含)
		 * @param scoreEnd 最大权重值(包含)
		 * @param limit 最多返回这么多个元素
		 * @return
		 */
		public List<String> zkeys(Object key, Object memberStart, Object scoreStart, Object scoreEnd, int limit) {
			try {
				Response response = ssdb.zkeys(key, memberStart, scoreStart, scoreEnd, limit);
				return response.listString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 列出 zset 中处于区间 (key_start+score_start, score_end] 的 member-score 对
		 * @author ruan
		 * @param key
		 * @param memberStart score_start 对应的 member
		 * @param scoreStart 最小权重值(不包含)
		 * @param scoreEnd 最大权重值(包含)
		 * @param limit 最多返回这么多个元素
		 * @return
		 */
		public Map<String, String> zscan(Object key, Object memberStart, Object scoreStart, Object scoreEnd, int limit) {
			try {
				Response response = ssdb.zscan(key, memberStart, scoreStart, scoreEnd, limit);
				return response.mapString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 反向列出 zset 中处于区间 (key_start+score_start, score_end] 的 member-score 对
		 * @author ruan
		 * @param key
		 * @param memberStart score_start 对应的 member
		 * @param scoreStart 最小权重值(不包含)
		 * @param scoreEnd 最大权重值(包含)
		 * @param limit 最多返回这么多个元素
		 * @return
		 */
		public Map<String, String> zrscan(Object key, Object memberStart, Object scoreStart, Object scoreEnd, int limit) {
			try {
				Response response = ssdb.zrscan(key, memberStart, scoreStart, scoreEnd, limit);
				return response.mapString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 根据下标索引区间 [offset, offset + limit) 获取 key-score 对, 下标从 0 开始(注意! 本方法在 offset 越来越大时, 会越慢!)
		 * @author ruan
		 * @param key
		 * @param offset
		 * @param limit
		 * @return 返回一个member => score 的map
		 */
		public Map<String, String> zrange(Object key, int offset, int limit) {
			try {
				Response response = ssdb.zrange(key, offset, limit);
				return response.mapString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 根据下标索引区间 [offset, offset + limit) 获取 key-score 对, 下标从 0 开始，倒序(注意! 本方法在 offset 越来越大时, 会越慢!)
		 * @author ruan
		 * @param key
		 * @param offset
		 * @param limit
		 * @return 返回一个member => score 的map
		 */
		public Map<String, String> zrrange(Object key, int offset, int limit) {
			try {
				Response response = ssdb.zrrange(key, offset, limit);
				return response.mapString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 返回指定 key 在 zset 中的排序位置(排名), 排名从 0 开始
		 * @author ruan
		 * @param key
		 * @param member
		 * @return
		 */
		public int zrank(Object key, Object member) {
			try {
				Response response = ssdb.zrank(key, member);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 返回指定 key 在 zset 中的排序位置(排名), 排名从 0 开始，反序
		 * @author ruan
		 * @param key
		 * @param member
		 * @return
		 */
		public int zrrank(Object key, Object member) {
			try {
				Response response = ssdb.zrrank(key, member);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 删除 zset 中的所有 member
		 * @author ruan
		 * @param key
		 * @return 删除key的zset的数据
		 */
		public int zclear(Object key) {
			try {
				Response response = ssdb.zclear(key);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 返回处于区间 [start,end] member 数量
		 * @author ruan
		 * @param key
		 * @param start 最小权重值(包含)
		 * @param end 最大权重值(包含)
		 * @return 符合条件的 key 的数量
		 */
		public int zcount(Object key, int start, int end) {
			try {
				Response response = ssdb.zcount(key, start, end);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 返回处于区间 [start,end] 的 score 的和
		 * @author ruan
		 * @param key
		 * @param start 最小权重值(包含)
		 * @param end 最大权重值(包含)
		 */
		public int zsum(Object key, int start, int end) {
			try {
				Response response = ssdb.zsum(key, start, end);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 返回处于区间 [start,end] 的 score 的平均值
		 * @author ruan
		 * @param key
		 * @param start 最小权重值(包含)
		 * @param end 最大权重值(包含)
		 */
		public int zavg(Object key, int start, int end) {
			try {
				Response response = ssdb.zavg(key, start, end);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 删除排名处于区间 [start,end] 的元素
		 * @author ruan
		 * @param key
		 * @param start
		 * @param end
		 * @return 返回被删除的元素个数
		 */
		public int zremrangebyrank(Object key, Object start, Object end) {
			try {
				Response response = ssdb.zremrangebyrank(key, start, end);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 删除score处于区间 [start,end] 的元素
		 * @author ruan
		 * @param key
		 * @param start
		 * @param end
		 * @return 返回被删除的元素个数
		 */
		public int zremrangebyscore(Object key, Object start, Object end) {
			try {
				Response response = ssdb.zremrangebyscore(key, start, end);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}
	}

	public class Queue {
		private Queue() {
		}

		/**
		 * 返回队列的长度
		 * @author ruan
		 * @param key
		 */
		public int qsize(Object key) {
			try {
				Response response = ssdb.qsize(key);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 往队列的尾部添加一个元素
		 * @author ruan
		 * @param key
		 * @param value
		 * @return 队列的长度
		 */
		public int qpush_back(Object key, Object value) {
			try {
				Response response = ssdb.qpush_back(key, value);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 往队列的首部添加一个元素
		 * @author ruan
		 * @param key
		 * @param value
		 * @return
		 */
		public int qpush(Object key, Object value) {
			try {
				Response response = ssdb.qpush_front(key, value);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 从队列首部弹出第一个元素
		 * @author ruan
		 * @param key
		 * @return
		 */
		public String qpop(Object key) {
			try {
				Response response = ssdb.qpop(key);
				if (response.datas.isEmpty()) {
					return null;
				}
				return response.asString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 从队列尾部弹出最后一个元素
		 * @author ruan
		 * @param key
		 * @return
		 */
		public String qpop_back(Object key) {
			try {
				Response response = ssdb.qpop_back(key);
				if (response.datas.isEmpty()) {
					return null;
				}
				return response.asString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 返回指定位置的元素. 0 表示第一个元素, 1 是第二个 ... -1 是最后一个
		 * @author ruan
		 * @param key
		 * @param index
		 * @return
		 */
		public String qget(Object key, int index) {
			try {
				Response response = ssdb.qget(key, index);
				if (response.datas.isEmpty()) {
					return null;
				}
				return response.asString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 列出名字处于区间 (start, end] 的 队列
		 * @author ruan
		 * @param start
		 * @param end
		 * @param limit
		 * @return
		 */
		public List<String> qlist(Object start, Object end, int limit) {
			try {
				Response response = ssdb.qlist(start, end, limit);
				return response.listString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 反序列出名字处于区间 (start, end] 的 队列
		 * @author ruan
		 * @param start
		 * @param end
		 * @param limit
		 * @return
		 */
		public List<String> qrlist(Object start, Object end, int limit) {
			try {
				Response response = ssdb.qrlist(start, end, limit);
				return response.listString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 清空一个队列
		 * @author ruan
		 * @param key
		 * @return 返回该队列的长度，如不存在返回0
		 */
		public int qclear(Object key) {
			try {
				Response response = ssdb.qclear(key);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 返回队列的第一个元素
		 * @author ruan
		 * @param key
		 * @return 如果队列为空返回null
		 */
		public String qfront(Object key) {
			try {
				Response response = ssdb.qfront(key);
				if (response.datas.isEmpty()) {
					return null;
				}
				return response.asString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 返回队列的最后一个元素
		 * @author ruan
		 * @param key
		 * @return 如果队列为空返回null
		 */
		public String qback(Object key) {
			try {
				Response response = ssdb.qback(key);
				if (response.datas.isEmpty()) {
					return null;
				}
				return response.asString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 返回下标处于区域 [offset, offset + limit] 的元素
		 * @author ruan
		 * @param key
		 * @param offset
		 * @param limit
		 * @return
		 */
		public List<String> qrange(Object key, int offset, int limit) {
			try {
				Response response = ssdb.qrange(key, offset, limit);
				return response.listString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 返回下标处于区域 [begin, end] 的元素. begin 和 end 可以是负数
		 * @author ruan
		 * @param key
		 * @param start
		 * @param end
		 * @return
		 */
		public List<String> qslice(Object key, int start, int end) {
			try {
				Response response = ssdb.qslice(key, start, end);
				return response.listString();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 从队列尾部删除多个元素
		 * @author ruan
		 * @param key
		 * @param size
		 * @return 返回被删除的元素数量
		 */
		public int qtrim_back(Object key, int size) {
			try {
				Response response = ssdb.qtrim_back(key, size);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}

		/**
		 * 从队列头部删除多个元素
		 * @author ruan
		 * @param key
		 * @param size
		 * @return 返回被删除的元素数量
		 */
		public int qtrim_front(Object key, int size) {
			try {
				Response response = ssdb.qtrim_front(key, size);
				return response.asInt();
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return 0;
		}
	}

	public class Server {
		private Server() {
		}

		/**
		* 返回ssdb服务器信息
		*/
		public Map<String, String> info() {
			try {
				Response response = ssdb.info();
				List<String> list = response.listString();
				list.remove(0);
				Map<String, String> infoMap = new HashMap<String, String>();
				String key = null, value = null;
				for (int i = 0, max = list.size(); i < max; i++) {
					if (i % 2 == 0) {
						key = list.get(i).trim();
					} else {
						value = list.get(i).trim();
					}
					if (key == null || value == null) {
						continue;
					}
					infoMap.put(key, value);
					key = value = null;
				}
				if (infoMap.isEmpty()) {
					throw new SSDBException(response.asString());
				}
				return infoMap;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 针对ssdb数据进行compact操作<br>
		 * 请不要随意调用，以免阻塞ssdb对外服务性能<br>
		 * 调用此方法是为了减少在高峰期用户大量写的请求时，触发leveldb compact条件<br>
		 * 在compact过程中，ssdb对外提供服务性能降低很厉害<br>
		 * 建议由客户端选择低峰期定时触发compact<br>
		 */
		public void compact() {
			try {
				ssdb.compact();
			} catch (SSDBException e) {
				logger.error("", e);
			}
		}

		/**
		 * 验证密码(明文传输)
		 * @author ruan
		 * @param passwd 密码
		 */
		public boolean auth(String passwd) {
			try {
				Response response = ssdb.auth(passwd);
				boolean b = "1".equals(response.asString());
				if (!b) {
					logger.error(response.asString());
				}
				return b;
			} catch (SSDBException e) {
				logger.error("", e);
			}
			return false;
		}

		/**
		 * 清除整个数据库的数据
		 * @author ruan
		 * @param type
		 */
		public void flushdb() {
			try {
				ssdb.flushdb("");
			} catch (SSDBException e) {
				logger.error("", e);
			}
		}

		/**
		 * 清除指定数据库的数据
		 * @author ruan
		 * @param type 可选(kv | hash | zset)
		 */
		public void flushdb(SSDBType type) {
			if (!type.equals(SSDBType.KV) || !type.equals(SSDBType.HASH) || !type.equals(SSDBType.ZSET)) {
				logger.error("error type: {}", type);
				return;
			}
			try {
				ssdb.flushdb(type.toString().toLowerCase());
			} catch (SSDBException e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * ssdb数据结构
	 * @author ruan
	 *
	 */
	public enum SSDBType {
		/**
		 * kv
		 */
		KV,
		/**
		 * 哈希
		 */
		HASH,
		/**
		 * 有序集
		 */
		ZSET,
		/**
		 * 队列
		 */
		QUEUE;

		public String toString() {
			return name();
		}
	}
}