package com.gu.core.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串工具类
 * 
 * @author ruan 2013-1-24
 * 
 */
public final class StringUtil {
	private final static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	private static Map<String, String> urlEncodeEscape = new HashMap<>();
	private final static Pattern filterEmojiPattern = Pattern.compile("");

	static {
		urlEncodeEscape.put("/", "$");
		urlEncodeEscape.put("+", "ā");
	}

	/**
	 * 私有构造函数
	 * 
	 * @author ruan 2013-1-24
	 */
	private StringUtil() {
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
	public final static String filterHTML(String str) {
		return getString(str).replaceAll("<.+?>", "").trim();
	}

	/**
	 * 过滤所有emoji标请
	 * 
	 * @param str
	 * @return
	 */
	public final static String filterEmoji(String str) {
		return filterEmojiPattern.matcher(getString(str)).replaceAll("");
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

	/**
	 * urlEncode
	 * @author ruan 
	 * @param str
	 */
	public final static String urlEncode(String str) {
		try {
			str = getString(str);
			if (str.isEmpty()) {
				return "";
			}
			for (Entry<String, String> e : urlEncodeEscape.entrySet()) {
				str = str.replace(e.getKey(), e.getValue());
			}
			return URLEncoder.encode(str, SystemUtil.getSystemCharsetString());
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	/**
	 * urlDecode
	 * @author ruan 
	 * @param str
	 */
	public final static String urlDecode(String str) {
		try {
			str = getString(str);
			if (str.isEmpty()) {
				return "";
			}
			str = URLDecoder.decode(str, SystemUtil.getSystemCharsetString());
			for (Entry<String, String> e : urlEncodeEscape.entrySet()) {
				str = str.replace(e.getValue(), e.getKey());
			}
			return str;
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}
}