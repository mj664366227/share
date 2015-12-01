package com.gu.service.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gu.core.redis.KeyFactory;
import com.gu.core.util.StringUtil;
import com.gu.dao.model.DFlow;
import com.gu.dao.model.DUser;
import com.gu.dao.model.DUserMessage;

/**
 * redis key
 * @author ruan
 */
@Component
public class RedisKey {
	/**
	 * key map
	 */
	private static Map<Object, String> keyMap = new HashMap<>();

	static {
		keyMap.put(DUser.class, KeyFactory.userKey);
		keyMap.put(DFlow.class, KeyFactory.flowKey);
		keyMap.put(DUserMessage.class, KeyFactory.userMessageKey);
	}

	/**
	 * 根据实体类获取key
	 * @author ruan 
	 * @param clazz 实体类
	 */
	public <T> String getKey(Class<T> clazz) {
		return StringUtil.getString(keyMap.get(clazz));
	}
}