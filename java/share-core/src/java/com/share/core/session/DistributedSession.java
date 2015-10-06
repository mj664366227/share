package com.share.core.session;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.share.core.interfaces.Session;
import com.share.core.redis.Redis;
import com.share.core.util.Secret;
import com.share.core.util.SerialUtil;
import com.share.core.util.StringUtil;

/**
 * 分布式Session<br>
 * 依赖redis实现
 */
@Component
public class DistributedSession implements Session {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(DistributedSession.class);
	/**
	 * 公共sessionKey
	 */
	private final static String distributedSessionGlobalCookieKey = "distributedSessionId";
	/**
	 * redis key
	 */
	private final static String distributedSessionRedisKey = "distributedSession:";
	/**
	 * 一个空的byte数组
	 */
	private final static byte[] emptyBytes = new byte[0];
	/**
	 * 默认1小时session失效
	 */
	private int maxAge = 3600;
	/**
	 * redis
	 */
	@Autowired
	private Redis redis;
	/**
	 * 加一个本地缓存保护redis
	 */
	private LoadingCache<byte[], byte[]> localCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<byte[], byte[]>() {
		public byte[] load(byte[] key) throws Exception {
			byte[] bytes = redis.STRINGS.get(key);
			if (bytes == null) {
				return emptyBytes;
			}
			return bytes;
		}
	});

	/**
	 * 私有构造函数
	 */
	private DistributedSession() {
	}

	/**
	 * 设置session失效时间
	 * @param maxAge 单位：秒
	 */
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	/**
	 * 生成分布式sessionKey
	 * @author ruan 
	 */
	private String genDistributedSessionKey(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		// 从浏览器获取session
		String distributedSessionKey = "";
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			cookies = new Cookie[0];
		}
		for (Cookie cookie : cookies) {
			if (!distributedSessionGlobalCookieKey.equals(StringUtil.getString(cookie.getName()))) {
				continue;
			}
			distributedSessionKey = StringUtil.getString(cookie.getValue());
		}

		// 如果丢失session，重新生成
		if (distributedSessionKey.isEmpty()) {
			distributedSessionKey = Secret.md5(String.valueOf(System.nanoTime()));

			// 写入cookie
			Cookie cookie = new Cookie(distributedSessionGlobalCookieKey, distributedSessionKey);
			response.addCookie(cookie);
		}

		// 以redis为准，如果redis获取不了数据，就证明session丢失
		String key = distributedSessionRedisKey + distributedSessionKey;
		if (!redis.KEYS.exists(key)) {
			redis.KEYS.del(key + "_" + sessionKey);
			redis.STRINGS.setex(key, maxAge, "1");
		}
		return key + "_" + sessionKey;
	}

	/**
	 * 向session写入数据
	 */
	public void addValue(HttpServletRequest request, HttpServletResponse response, String sessionKey, Object value) {
		String distributedSessionKey = genDistributedSessionKey(request, response, sessionKey);
		DistributedSessionData data = new DistributedSessionData();
		data.setData(value);
		redis.STRINGS.setex(distributedSessionKey.getBytes(), maxAge, SerialUtil.toBytes(data));
	}

	/**
	 * 从session删除数据
	 */
	public void removeValue(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		String distributedSessionKey = genDistributedSessionKey(request, response, sessionKey);
		redis.KEYS.del(distributedSessionKey);
	}

	/**
	 * getInt
	 */
	public int getInt(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getInt(getObject(request, response, sessionKey));
	}

	/**
	 * getLong
	 */
	public long getLong(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getLong(getObject(request, response, sessionKey));
	}

	/**
	 * getShort
	 */
	public short getShort(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getShort(getObject(request, response, sessionKey));
	}

	/**
	 * getByte
	 */
	public byte getByte(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getByte(getObject(request, response, sessionKey));
	}

	/**
	 * getFloat
	 */
	public float getFloat(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getFloat(getObject(request, response, sessionKey));
	}

	/**
	 * getDouble	
	 */
	public double getDouble(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getDouble(getObject(request, response, sessionKey));
	}

	/**
	 * getString	
	 */
	public String getString(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return StringUtil.getString(getObject(request, response, sessionKey));
	}

	/**
	 * getBytes	
	 */
	public byte[] getBytes(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		String distributedSessionKey = genDistributedSessionKey(request, response, sessionKey);
		return get(distributedSessionKey.getBytes());
	}

	/**
	 * getObject
	 */
	public Object getObject(HttpServletRequest request, HttpServletResponse response, String sessionKey) {
		return getT(request, response, sessionKey, Object.class);
	}

	/**
	 * getT
	 */
	@SuppressWarnings("unchecked")
	public <T> T getT(HttpServletRequest request, HttpServletResponse response, String sessionKey, Class<T> t) {
		String distributedSessionKey = genDistributedSessionKey(request, response, sessionKey);
		byte[] bytes = get(distributedSessionKey.getBytes());
		if (bytes.length <= 0) {
			return null;
		}
		return (T) SerialUtil.fromBytes(bytes, DistributedSessionData.class).getData();
	}

	/**
	 * 从guava获取数据
	 * @author ruan 
	 * @param key	
	 */
	private byte[] get(byte[] key) {
		try {
			return localCache.get(key);
		} catch (Exception e) {
			logger.error("", e);
		}
		return emptyBytes;
	}
}