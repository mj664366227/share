package com.gu.service.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.core.annotation.processor.PojoProcessor;
import com.gu.core.redis.CountKey;
import com.gu.core.redis.Redis;
import com.gu.core.util.SerialUtil;
import com.gu.core.util.StringUtil;
import com.gu.dao.db.GuSlaveDbService;

/**
 * 公共service
 * @author ruan
 */
@Component
public class CommonService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private Redis redis;
	@Autowired
	private RedisKey redisKey;
	@Autowired
	private GuSlaveDbService guSlaveDbService;
	@Autowired
	private PojoProcessor pojoProcessor;

	/**
	 * 批量获取T
	 * @param idSet id集合
	 * @param clazz 实体类
	 */
	public <T> Map<Long, T> multiGetT(Set<String> idSet, Class<T> clazz) {
		// 去掉占位符
		idSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (idSet.isEmpty()) {
			return new HashMap<>(0);
		}

		// 获取key
		String prefixKey = redisKey.getKey(clazz);
		if (prefixKey.isEmpty()) {
			logger.error("can not get key, {}", clazz);
			return new HashMap<>(0);
		}

		// 首先去缓存拿
		int size = idSet.size();
		Map<Long, T> data = new LinkedHashMap<>(size);
		List<String> clazzKeyList = new ArrayList<String>(size);
		for (String f : idSet) {
			clazzKeyList.add(prefixKey + f);
			data.put(StringUtil.getLong(f), null);
		}
		List<byte[]> byteList = redis.STRINGS.mget(clazzKeyList);

		// 判断哪些缓存是有的，哪些是缓存没有的
		if (byteList != null && !byteList.isEmpty()) {
			for (byte[] bytes : byteList) {
				T t = (T) SerialUtil.fromBytes(bytes, clazz);
				if (t == null) {
					// 有可能反序列化不成功
					continue;
				}
				Map<String, Method> methodMap = pojoProcessor.getGetMethodMapByClass(clazz);
				if (methodMap == null) {
					logger.error("class {} methodMap is null", clazz.getName());
					continue;
				}
				Method method = methodMap.get("id");
				if (method == null) {
					logger.error("method getId() is null");
					continue;
				}
				try {
					data.put(StringUtil.getLong(method.invoke(t)), t);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}

		// 找出缓存没有的
		Set<Long> tmpUserIdSet = new HashSet<>(size);
		for (Entry<Long, T> e : data.entrySet()) {
			if (e.getValue() == null) {
				tmpUserIdSet.add(e.getKey());
			}
		}
		if (tmpUserIdSet.isEmpty()) {
			return data;
		}

		// 缓存丢失的，去数据库查找
		List<T> tList = guSlaveDbService.multiGetT(tmpUserIdSet, clazz);
		if (tList == null || tList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}
		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(tList.size());
		for (T t : tList) {
			// 如果数据库查到就回写到缓存
			Map<String, Method> methodMap = pojoProcessor.getGetMethodMapByClass(clazz);
			if (methodMap == null) {
				logger.error("class {} methodMap is null", clazz.getName());
				continue;
			}
			Method method = methodMap.get("id");
			if (method == null) {
				logger.error("method getId() is null");
				continue;
			}
			try {
				long id = StringUtil.getLong(method.invoke(t));
				data.put(id, t);
				keysValuesMap.put((prefixKey + id).getBytes(), SerialUtil.toBytes(t));
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		redis.STRINGS.msetex(keysValuesMap, (int) TimeUnit.DAYS.toSeconds(30));
		return data;
	}

	/**
	 * 批量获取统计字段的值
	 * @param idSet id集合
	 * @param countKey key枚举
	 * @return Map<Long,Integer> id => 统计字段的值
	 */
	public Map<Long, Integer> multiGetCountColumn(Set<String> idSet, CountKey countKey) {
		// 去掉占位符
		idSet.remove("-1");
		// id集合空,直接返回空的HashMap
		if (idSet.isEmpty()) {
			return new HashMap<Long, Integer>(0);
		}

		// 获取key
		String prefixKey = countKey.getPrefixKey();
		// 首先去缓存拿
		int size = idSet.size();
		Map<Long, Integer> data = new LinkedHashMap<>(size);
		String[] keyArr = new String[size];
		int i = 0;
		for (String id : idSet) {
			keyArr[i] = prefixKey + id;
			data.put(StringUtil.getLong(id), null);
			i += 1;
		}

		List<String> countList = redis.STRINGS.mget(keyArr);
		Set<Long> tmpIdSet = new HashSet<>(size);// 找出缓存没有的
		// 判断哪些缓存是有的，哪些是缓存没有的
		if (countList != null && !countList.isEmpty()) {
			i = 0;
			for (String id : idSet) {
				String count = countList.get(i);
				i += 1;
				if (count == null) {
					tmpIdSet.add(StringUtil.getLong(id));
					continue;
				}
				data.put(StringUtil.getLong(id), StringUtil.getInt(count));
			}
		}

		if (tmpIdSet.isEmpty()) {
			return data;
		}
		// 缓存丢失的，去数据库查找
		List<Map<String, Object>> tList = guSlaveDbService.multiGetCountColumn(tmpIdSet, countKey);
		if (tList == null || tList.isEmpty()) {
			// 如果数据库都没有，那就直接返回
			return data;
		}

		HashMap<byte[], byte[]> keysValuesMap = new HashMap<byte[], byte[]>(tList.size());
		for (Map<String, Object> m : tList) {
			data.put(StringUtil.getLong(m.get("id")), StringUtil.getInt(m.get(countKey.getColumn())));
			// 如果数据库查到就回写到缓存
			keysValuesMap.put((prefixKey + String.valueOf(m.get("id"))).getBytes(), String.valueOf(m.get(countKey.getColumn())).getBytes());
		}
		if (countKey.getSeconds() > 0) {
			redis.STRINGS.msetex(keysValuesMap, countKey.getSeconds());
		} else {
			redis.STRINGS.mset(keysValuesMap);
		}
		return data;
	}

	/**
	 * 保存到Redis缓存
	 * @param countKey key枚举
	 * @param id 要更新数据的ID
	 * @param count 要缓存的值
	 */
	public void setCountColumnRedis(CountKey countKey, long id, int count) {
		if (countKey.getSeconds() > 0) {
			redis.STRINGS.setex(countKey.getKey(id), countKey.getSeconds(), String.valueOf(count));
		} else {
			redis.STRINGS.set(countKey.getKey(id), String.valueOf(count));
		}
	}

	/**
	 * 获取统计字段的值
	 * @param countKey countKey key枚举
	 * @param id 要更新数据的ID
	 * @return
	 */
	public int getCountColumn(CountKey countKey, long id) {
		String countStr = redis.STRINGS.get(countKey.getKey(id));
		if (countStr != null) {
			return StringUtil.getInt(countStr);
		}
		//没有缓存,穿透数据库
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT `");
		sql.append(countKey.getColumn());
		sql.append("` FROM `");
		sql.append(countKey.getTable());
		sql.append("` WHERE `id`=");
		sql.append(id);
		int count = guSlaveDbService.queryInt(sql.toString());
		setCountColumnRedis(countKey, id, count);
		return count;
	}

	/**
	 * 更新统计字段的值,mysql,redis
	 * @param countKey key枚举
	 * @param id 要更新数据的ID
	 * @param number 增加后减少的值
	 * @return
	 */
	public boolean updateCountColumn(CountKey countKey, long id, long number) {
		if (number == 0) {
			return false;
		}
		getCountColumn(countKey, id);
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE `");
		sql.append(countKey.getTable());
		sql.append("` SET `");
		sql.append(countKey.getColumn());
		sql.append("`=`");
		sql.append(countKey.getColumn());
		sql.append("`");
		if (number > 0) {
			sql.append("+");
		}
		sql.append(String.valueOf(number));
		sql.append(" WHERE `id`=");
		sql.append(id);
		boolean b = guSlaveDbService.update(sql.toString());
		if (b) {
			redis.STRINGS.incrBy(countKey.getKey(id), number);
		}
		return b;
	}
}