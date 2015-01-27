package com.share.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 序列化(用二进制实现) <br>
 * <br>
 * <strong>序列化的对象无需继承任何类，只需要有getter和setter方法就可以</strong>
 * 
 * @author ruan
 */
public final class Serialize {
	private final static Logger logger = LoggerFactory.getLogger(Serialize.class);
	private static HashMap<Class<?>, Field[]> fieldMap = new HashMap<Class<?>, Field[]>();
	private static HashMap<Class<?>, HashMap<String, Method>> setMethodMap = new HashMap<Class<?>, HashMap<String, Method>>();
	private static HashMap<Class<?>, HashMap<String, Method>> getMethodMap = new HashMap<Class<?>, HashMap<String, Method>>();
	private final static byte delimiter = '\0';

	/**
	 * 序列化
	 * 
	 * @author ruan 2013-7-29
	 * @param clazz
	 * @return
	 */
	public final static <T> byte[] serialize(T clazz) {
		byte[] b = new byte[0];
		int i = 0;
		Class<?> c = clazz.getClass();
		Field[] fields = fieldMap.get(c);
		if (fields == null) {
			fields = c.getDeclaredFields();
			fieldMap.put(c, fields);
		}
		HashMap<String, Method> methodMaps = getMethodMap.get(c);
		if (methodMaps == null) {
			methodMaps = new HashMap<String, Method>();
			getMethodMap.put(c, methodMaps);
		}
		try {
			for (Field field : fields) {
				String fieldName = field.getName();
				Method method = methodMaps.get(fieldName);
				if (method == null) {
					method = c.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
					methodMaps.put(fieldName, method);
				}
				byte[] bytes = method.invoke(clazz).toString().getBytes();
				b = Arrays.copyOf(b, b.length + bytes.length + 1);
				for (byte bb : bytes) {
					b[i] = bb;
					i += 1;
				}
				b[i] = delimiter;
				i += 1;
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error("", e);
		}
		return b;
	}

	/**
	 * 反序列化
	 * 
	 * @author ruan 2013-7-29
	 * @param bytes
	 * @param clazz
	 * @return
	 */
	public final static <T> T deserialize(byte[] bytes, Class<T> clazz) {
		ArrayList<byte[]> bytesList = new ArrayList<byte[]>();
		byte[] bb = new byte[0];
		int i = 0;
		for (byte b : bytes) {
			bb = Arrays.copyOf(bb, bb.length + 1);
			bb[i] = b;
			i += 1;
			if (b == delimiter) {
				bytesList.add(bb);
				bb = new byte[0];
				i = 0;
			}
		}
		i = 0;
		T obj = null;
		try {
			obj = clazz.newInstance();
			Field[] fields = fieldMap.get(clazz);
			if (fields == null) {
				fields = clazz.getDeclaredFields();
				fieldMap.put(clazz, fields);
			}
			HashMap<String, Method> methodMaps = setMethodMap.get(clazz);
			if (methodMaps == null) {
				methodMaps = new HashMap<String, Method>();
				setMethodMap.put(clazz, methodMaps);
			}
			for (Field field : fields) {
				String fieldName = field.getName();
				Object cla = field.getType();
				Object args = null;
				if (cla == int.class) {
					args = Integer.parseInt(new String(bytesList.get(i)).trim());
				} else if (cla == long.class) {
					args = Long.parseLong(new String(bytesList.get(i)).trim());
				} else if (cla == short.class) {
					args = Short.parseShort(new String(bytesList.get(i)).trim());
				} else if (cla == double.class) {
					args = Double.parseDouble(new String(bytesList.get(i)).trim());
				} else if (cla == float.class) {
					args = Float.parseFloat(new String(bytesList.get(i)).trim());
				} else if (cla == byte.class) {
					args = Byte.parseByte(new String(bytesList.get(i)).trim());
				} else if (cla == byte[].class) {
					args = new String(bytesList.get(i)).trim().getBytes();
				} else if (cla == boolean.class) {
					args = Boolean.parseBoolean(new String(bytesList.get(i)).trim());
				} else if (cla == String.class) {
					args = new String(bytesList.get(i)).trim();
				}
				Method method = methodMaps.get(fieldName);
				if (method == null) {
					method = clazz.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), field.getType());
					methodMaps.put(fieldName, method);
				}
				method.invoke(obj, args);
				i += 1;
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			logger.error("", e);
		}
		return obj;
	}
}
