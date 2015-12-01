package com.gu.core.general;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 虚拟cdn(基于guava实现)
 * @author ruan
 */
public final class VirtualCDNService {
	/**
	 * 最底层的缓存(设置成永不过期)
	 */
	private static Cache<String, Cache<String, Object>> cache = CacheBuilder.newBuilder().expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS).build();

	/**
	 * 写入数据
	 * @author ruan 
	 * @param key 键
	 * @param duration 持续时间
	 * @param timeUnit 时间单位
	 * @param value 要写入的值 
	 */
	public final static void put(String key, long duration, TimeUnit timeUnit, Object value) {
		Cache<String, Object> tmp = CacheBuilder.newBuilder().expireAfterWrite(duration, timeUnit).build();
		tmp.put(key, value);
		cache.put(key, tmp);
	}

	/**
	 * 获取数据
	 * @author ruan 
	 * @param key 键
	 */
	public final static Object get(String key) {
		Cache<String, Object> tmp = cache.getIfPresent(key);
		if (tmp == null) {
			return null;
		}
		return tmp.getIfPresent(key);
	}
}