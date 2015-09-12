package com.gu.core.util;

import javax.servlet.http.HttpSession;

/**
 * Session工具
 */
public final class SessionUtil {
	/**
	 * 私有构造函数
	 */
	private SessionUtil() {
	}

	/**
	 * 向session写入数据
	 * @param session
	 * @param sessionKey
	 * @param value
	 */
	public final static void addValue(HttpSession session, String sessionKey, Object value) {
		session.setAttribute(sessionKey, value);
	}

	/**
	 * 从session删除数据
	 * @param session
	 * @param sessionKey
	 */
	public final static void removeValue(HttpSession session, String sessionKey) {
		session.removeAttribute(sessionKey);
	}

	/**
	 * getInt
	 */
	public final static int getInt(HttpSession session, String sessionKey) {
		return StringUtil.getInt(session.getAttribute(sessionKey));
	}

	/**
	 * getLong
	 */
	public final static long getLong(HttpSession session, String sessionKey) {
		return StringUtil.getLong(session.getAttribute(sessionKey));
	}

	/**
	 * getShort
	 */
	public final static short getShort(HttpSession session, String sessionKey) {
		return StringUtil.getShort(session.getAttribute(sessionKey));
	}

	/**
	 * getByte
	 */
	public final static byte getByte(HttpSession session, String sessionKey) {
		return StringUtil.getByte(session.getAttribute(sessionKey));
	}

	/**
	 * getFloat
	 */
	public final static float getFloat(HttpSession session, String sessionKey) {
		return StringUtil.getFloat(session.getAttribute(sessionKey));
	}

	/**
	 * getDouble	
	 */
	public final static double getDouble(HttpSession session, String sessionKey) {
		return StringUtil.getDouble(session.getAttribute(sessionKey));
	}

	/**
	 * getString	
	 */
	public final static String getString(HttpSession session, String sessionKey) {
		return StringUtil.getString(session.getAttribute(sessionKey));
	}

	/**
	 * getBytes	
	 */
	public final static byte[] getBytes(HttpSession session, String sessionKey) {
		return (byte[]) session.getAttribute(sessionKey);
	}

	/**
	 * getObject
	 */
	public final static Object getObject(HttpSession session, String sessionKey) {
		return session.getAttribute(sessionKey);
	}

	/**
	 * getT
	 */
	@SuppressWarnings("unchecked")
	public final static <T> T getT(HttpSession session, String sessionKey, Class<T> t) {
		return (T) session.getAttribute(sessionKey);
	}
}