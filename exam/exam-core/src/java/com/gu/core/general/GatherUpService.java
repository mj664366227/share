package com.gu.core.general;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gu.core.common.URLCommand;
import com.gu.core.enums.CompanyPointsAddEvent;
import com.gu.core.enums.CompanyPointsSubEvent;
import com.gu.core.enums.MessageSign;
import com.gu.core.enums.UserPointsAddEvent;
import com.gu.core.enums.UserPointsSubEvent;
import com.gu.core.enums.UserScoreAddEvent;
import com.gu.core.enums.UserScoreSubEvent;
import com.gu.core.interfaces.Error;
import com.gu.core.redis.CountKey;
import com.gu.core.redis.KeyFactory;
import com.gu.core.util.StringUtil;
import com.gu.core.util.SystemUtil;
import com.gu.core.util.Time;

/**
 * gu项目启动检查类
 * @author ruan
 */
@Component
public class GatherUpService {
	private final static Logger logger = LoggerFactory.getLogger(GatherUpService.class);

	@PostConstruct
	public void init() {
		long t = System.nanoTime();

		// 检查url
		checkURL();

		// 检查错误码
		checkRedisKey();

		// 检查redis key
		checkErrorCode();

		// 检查消息类型
		checkMessageSign();

		// 检查事件类型
		checkEvent();

		logger.warn("gather up check exec: {}", Time.showTime(System.nanoTime() - t));
	}

	/**
	 * 检查事件类型
	 */
	private void checkEvent() {
		// 企业加G点
		Map<Integer, Object> map = new HashMap<>();
		for (CompanyPointsAddEvent companyPointsAddEvent : CompanyPointsAddEvent.values()) {
			if (map.containsKey(companyPointsAddEvent.getValue())) {
				logger.error("duplicate company points add event! duplicate company points add event! duplicate company points add event!");
				logger.error("{}  {}", companyPointsAddEvent.getValue(), CompanyPointsAddEvent.class + "." + companyPointsAddEvent);
				logger.error("{}  {}", companyPointsAddEvent.getValue(), CompanyPointsAddEvent.class + "." + map.get(companyPointsAddEvent.getValue()));
				System.exit(0);
			}
			map.put(companyPointsAddEvent.getValue(), companyPointsAddEvent);
		}
		map.clear();

		// 企业扣G点
		for (CompanyPointsSubEvent companyPointsSubEvent : CompanyPointsSubEvent.values()) {
			if (map.containsKey(companyPointsSubEvent.getValue())) {
				logger.error("duplicate company points sub event! duplicate company points sub event! duplicate company points sub event!");
				logger.error("{}  {}", companyPointsSubEvent.getValue(), CompanyPointsSubEvent.class + "." + companyPointsSubEvent);
				logger.error("{}  {}", companyPointsSubEvent.getValue(), CompanyPointsSubEvent.class + "." + map.get(companyPointsSubEvent.getValue()));
				System.exit(0);
			}
			map.put(companyPointsSubEvent.getValue(), companyPointsSubEvent);
		}
		map.clear();

		// 用户加G点
		for (UserPointsAddEvent userPointsAddEvent : UserPointsAddEvent.values()) {
			if (map.containsKey(userPointsAddEvent.getValue())) {
				logger.error("duplicate user points add event! duplicate user points add event! duplicate user points add event!");
				logger.error("{}  {}", userPointsAddEvent.getValue(), UserPointsAddEvent.class + "." + userPointsAddEvent);
				logger.error("{}  {}", userPointsAddEvent.getValue(), UserPointsAddEvent.class + "." + map.get(userPointsAddEvent.getValue()));
				System.exit(0);
			}
			map.put(userPointsAddEvent.getValue(), userPointsAddEvent);
		}
		map.clear();

		// 用户扣G点
		for (UserPointsSubEvent userPointsSubEvent : UserPointsSubEvent.values()) {
			if (map.containsKey(userPointsSubEvent.getValue())) {
				logger.error("duplicate user points sub event! duplicate user points sub event! duplicate user points sub event!");
				logger.error("{}  {}", userPointsSubEvent.getValue(), UserPointsSubEvent.class + "." + userPointsSubEvent);
				logger.error("{}  {}", userPointsSubEvent.getValue(), UserPointsSubEvent.class + "." + map.get(userPointsSubEvent.getValue()));
				System.exit(0);
			}
			map.put(userPointsSubEvent.getValue(), userPointsSubEvent);
		}
		map.clear();

		// 用户加积分
		for (UserScoreAddEvent userScoreAddEvent : UserScoreAddEvent.values()) {
			if (map.containsKey(userScoreAddEvent.getValue())) {
				logger.error("duplicate user score add event! duplicate user score add event! duplicate user score add event!");
				logger.error("{}  {}", userScoreAddEvent.getValue(), UserScoreAddEvent.class + "." + userScoreAddEvent);
				logger.error("{}  {}", userScoreAddEvent.getValue(), UserScoreAddEvent.class + "." + map.get(userScoreAddEvent.getValue()));
				System.exit(0);
			}
			map.put(userScoreAddEvent.getValue(), userScoreAddEvent);
		}
		map.clear();

		// 用户扣积分
		for (UserScoreSubEvent userScoreSubEvent : UserScoreSubEvent.values()) {
			if (map.containsKey(userScoreSubEvent.getValue())) {
				logger.error("duplicate user score sub event! duplicate user score sub event! duplicate user score sub event!");
				logger.error("{}  {}", userScoreSubEvent.getValue(), UserScoreSubEvent.class + "." + userScoreSubEvent);
				logger.error("{}  {}", userScoreSubEvent.getValue(), UserScoreSubEvent.class + "." + map.get(userScoreSubEvent.getValue()));
				System.exit(0);
			}
			map.put(userScoreSubEvent.getValue(), userScoreSubEvent);
		}
		map = null;
	}

	/**
	 * 检查消息类型
	 */
	private void checkMessageSign() {
		Map<Integer, MessageSign> map = new HashMap<>();
		for (MessageSign messageSign : MessageSign.values()) {
			if (map.containsKey(messageSign.getSign())) {
				logger.error("duplicate message sign! duplicate message sign! duplicate message sign!");
				logger.error("{}  {}", messageSign.getSign(), MessageSign.class + "." + messageSign);
				logger.error("{}  {}", messageSign.getSign(), MessageSign.class + "." + map.get(messageSign.getSign()));
				System.exit(0);
			}
			map.put(messageSign.getSign(), messageSign);
		}
		map = null;
	}

	/**
	 * 检查redis key
	 */
	private void checkRedisKey() {
		try {
			Map<String, Object> map = new HashMap<>();
			for (Method method : KeyFactory.class.getDeclaredMethods()) {
				Class<?>[] parameterTypes = method.getParameterTypes();
				Object[] objs = new Object[parameterTypes.length];
				for (int i = 0; i < parameterTypes.length; i++) {
					if (byte.class.equals(parameterTypes[i])) {
						objs[i] = Byte.MAX_VALUE;
					} else if (short.class.equals(parameterTypes[i])) {
						objs[i] = Short.MAX_VALUE;
					} else if (int.class.equals(parameterTypes[i])) {
						objs[i] = Integer.MAX_VALUE;
					} else if (long.class.equals(parameterTypes[i])) {
						objs[i] = Long.MAX_VALUE;
					} else if (float.class.equals(parameterTypes[i])) {
						objs[i] = Float.MAX_VALUE;
					} else if (double.class.equals(parameterTypes[i])) {
						objs[i] = Double.MAX_VALUE;
					} else if (char.class.equals(parameterTypes[i])) {
						objs[i] = 129;
					} else if (boolean.class.equals(parameterTypes[i])) {
						objs[i] = true;
					} else if (String.class.equals(parameterTypes[i])) {
						objs[i] = "test";
					} else {
						objs[i] = "test";
					}
				}
				String value = StringUtil.getString(method.invoke(KeyFactory.class, objs));
				if (map.containsKey(value)) {
					logger.error("duplicate redis key! duplicate redis key! duplicate redis key!");
					logger.error("{}  {}", value, method);
					logger.error("{}  {}", value, StringUtil.getString(map.get(value)));
					System.exit(0);
				}
				map.put(value, method);
			}

			for (CountKey countKey : CountKey.values()) {
				String value = countKey.getKey(Long.MAX_VALUE);
				if (map.containsKey(value)) {
					logger.error("duplicate redis key! duplicate redis key! duplicate redis key!");
					logger.error("{}  {}", value, CountKey.class + "." + countKey);
					logger.error("{}  {}", value, StringUtil.getString(map.get(value)));
					System.exit(0);
				}
				map.put(value, countKey);
			}

			map = null;
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	/**
	 * 检查错误码
	 */
	private void checkErrorCode() {
		Map<Integer, String> map = new HashMap<>();
		for (Class<?> clazz : SystemUtil.getClasses("com.gu.core.errorcode")) {
			for (Object o : clazz.getEnumConstants()) {
				Error error = (Error) o;
				if (map.containsKey(error.getErrorCode())) {
					logger.error("duplicate error code! duplicate error code! duplicate error code!");
					logger.error("{}.{}  {}", clazz.getSimpleName(), error.toString(), error.getErrorCode());
					logger.error("{}  {}", StringUtil.getString(map.get(error.getErrorCode())), error.getErrorCode());
					System.exit(0);
				}
				map.put(error.getErrorCode(), clazz.getSimpleName() + "." + error.toString());
			}
		}
		map = null;
	}

	/**
	 * 检查url
	 */
	private void checkURL() {
		try {
			Map<String, String> map = new HashMap<>();
			for (Field field : URLCommand.class.getDeclaredFields()) {
				field.setAccessible(true);
				String fieldName = StringUtil.getString(field.getName());
				String url = StringUtil.getString(field.get(URLCommand.class));
				if (map.containsKey(url)) {
					logger.error("duplicate url! duplicate url! duplicate url!");
					logger.error("{}  {}", fieldName, url);
					logger.error("{}  {}", StringUtil.getString(map.get(url)), url);
					System.exit(0);
				}
				map.put(url, fieldName);
			}
			map = null;
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}
}