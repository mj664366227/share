package com.gu.core.util;

import java.util.Arrays;

/**
 * 字符串工具类
 * 
 * @author ruan 2013-1-24
 * 
 */
public final class StringUtil {
	/**
	 * 私有构造函数
	 * 
	 * @author ruan 2013-1-24
	 */
	private StringUtil() {
	}

	/**
	 * 把字符串复制到byte数组
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static byte[] copyToBytes(String str) {
		byte[] b = str.trim().getBytes();
		b = Arrays.copyOf(b, b.length + 1);
		return b;
	}

	/**
	 * 把字符串复制到byte数组
	 * 
	 * @author ruan
	 * @param str
	 * @param bytes
	 */
	public final static void copyToBytes(String str, byte[] bytes) {
		byte[] arr = str.trim().getBytes();
		if (bytes == null) {
			bytes = new byte[arr.length + 1];
		}
		for (int i = 0; i < arr.length; i++) {
			bytes[i] = arr[i];
		}
	}

	/**
	 * 复制一个新的字符串
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static String copyToString(String str) {
		return new String(str).trim();
	}

	/**
	 * getInt
	 * 
	 * @param str
	 * @return
	 */
	public final static int getInt(String str) {
		str = getString(str);
		if (!Check.isNumber(str)) {
			return 0;
		}
		if (str.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(str.split("\\.")[0]);
	}

	/**
	 * getInt
	 * 
	 * @param str
	 * @return
	 */
	public final static int getInt(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getInt(obj.toString().trim());
	}

	/**
	 * getByte
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static byte getByte(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return 0;
		}
		if (!Check.isNumber(str)) {
			return 0;
		}
		return Byte.parseByte(str.split("\\.")[0]);
	}

	/**
	 * getByte
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static byte getByte(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getByte(obj.toString().trim());
	}

	/**
	 * getShort
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static short getShort(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return 0;
		}
		if (!Check.isNumber(str)) {
			return 0;
		}
		return Short.parseShort(str.split("\\.")[0]);
	}

	/**
	 * getShort
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static short getShort(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getShort(obj.toString().trim());
	}

	/**
	 * getLong
	 * 
	 * @param str
	 * @return
	 */
	public final static long getLong(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return 0;
		}
		if (!Check.isNumber(str)) {
			return 0;
		}
		return Long.parseLong(str.split("\\.")[0]);
	}

	/**
	 * getLong
	 * 
	 * @param str 
	 * @return
	 */
	public final static long getLong(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getLong(obj.toString().trim());
	}

	/**
	 * getDouble
	 * 
	 * @param str
	 * @return
	 */
	public final static double getDouble(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return 0;
		}
		if (!Check.isNumber(str)) {
			return 0;
		}
		return Double.parseDouble(str);
	}

	/**
	 * getDouble
	 * 
	 * @param str
	 * @return
	 */
	public final static double getDouble(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getDouble(obj.toString().trim());
	}

	/**
	 * getFloat
	 * 
	 * @param str
	 * @return
	 */
	public final static float getFloat(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return 0;
		}
		if (!Check.isNumber(str)) {
			return 0;
		}
		return Float.parseFloat(str);
	}

	/**
	 * getFloat
	 * 
	 * @param str
	 * @return
	 */
	public final static float getFloat(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getFloat(obj.toString().trim());
	}

	/**
	 * getBoolean
	 * 
	 * @param str
	 * @return
	 */
	public final static boolean getBoolean(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return false;
		}
		return Boolean.valueOf(str);
	}

	/**
	 * getBoolean
	 * 
	 * @param str
	 * @return
	 */
	public final static boolean getBoolean(Object obj) {
		if (obj == null) {
			return false;
		}
		return getBoolean(obj.toString().trim());
	}

	/**
	 * getString
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static String getString(String str) {
		return str == null ? "" : str.trim();
	}

	/**
	 * getString
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static String getString(Object obj) {
		if (obj == null) {
			return "";
		}
		return getString(obj.toString());
	}

	/**
	 * 过滤所有html标签
	 * 
	 * @param str
	 * @return
	 */
	public final static String flitHTML(String str) {
		if (str == null || str.isEmpty()) {
			return "";
		}
		return str.replaceAll("<.+?>", "").trim();
	}

	/**
	 * 计算子字符串在父字符串出现的次数
	 * @author ruan
	 * @param subject
	 * @param search
	 * @return
	 */
	public final static int strCount(String subject, String search) {
		String[] arr = subject.toLowerCase().split(search.toLowerCase());
		return arr.length > 0 ? arr.length - 1 : 0;
	}
}