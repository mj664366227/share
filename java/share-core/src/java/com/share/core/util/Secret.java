package com.share.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密类
 */
public final class Secret {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(Secret.class);
	/**
	 * 排序规则
	 */
	private static final Comparator<String> comp = new Comparator<String>() {
		public int compare(String o1, String o2) {
			if (o1.compareTo(o2) > 0) {
				return 1;
			} else if (o1.compareTo(o2) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	};

	/**
	 * md5
	 */
	private static MessageDigest md5;
	/**
	 * sha
	 */
	private static MessageDigest sha;

	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
			sha = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			logger.error("", e);
		}
	}

	private Secret() {
	}

	/**
	 * MD5加密
	 * 
	 * @param string
	 * @return String
	 */
	public final static String MD5(String string) {
		return byteArrayToHexString(md5.digest(string.getBytes()));
	}

	/**
	 * 哈希加密
	 * 
	 * @param string
	 * @return String
	 */
	public final static String SHA(String string) {
		return byteArrayToHexString(sha.digest(string.getBytes()));
	}

	/**
	 * base64加密
	 * @param str
	 */
	public final static String base64Encode(String str) {
		return new BASE64Encoder().encode(str.getBytes());
	}

	/**
	 * base64解密
	 * @param str
	 */
	public final static String base64Decode(String str) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(str);
			return new String(b);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 一种生成sign值的方法：<br>
	 * 每个参数的按照key正序依次连接，然后连接加密密匙key，最后整个字符串做一次md5计算<br>
	 * 例子：a=2，b=1，c=fuck<br>
	 * sign = md5(2+1+fuck+key)
	 * @param data 要加密的数据
	 * @param key 加密密匙
	 * @return
	 */
	public final static String makeSign(HashMap<String, Object> data, String key) {
		StringBuilder sign = new StringBuilder();
		ArrayList<String> keyList = new ArrayList<String>();
		for (Entry<String, Object> e : data.entrySet()) {
			keyList.add(e.getKey());
		}
		Collections.sort(keyList, comp);
		for (String k : keyList) {
			sign.append(data.get(k));
		}
		sign.append(key);
		return MD5(sign.toString());
	}

	/**
	 * byteArrayToHexString
	 * @author ruan
	 * @param bytes
	 * @return
	 */
	private final static String byteArrayToHexString(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		buf.trimToSize();
		return buf.toString();
	}
}