package com.share.admin.util;

import javax.servlet.http.HttpSession;

import com.share.admin.common.SessionKey;
import com.share.core.util.StringUtil;

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
	public final static void addValue(HttpSession session, SessionKey sessionKey, Object value) {
		session.setAttribute(sessionKey.toString(), value);
	}

	/**
	 * 从session删除数据
	 * @param session
	 * @param sessionKey
	 */
	public final static void removeValue(HttpSession session, SessionKey sessionKey) {
		session.removeAttribute(sessionKey.toString());
	}

	/**
	 * getInt
	 */
	public final static int getInt(HttpSession session, SessionKey sessionKey) {
		return StringUtil.getInt(session.getAttribute(sessionKey.toString()));
	}

	/**
	 * getLong
	 */
	public final static long getLong(HttpSession session, SessionKey sessionKey) {
		return StringUtil.getLong(session.getAttribute(sessionKey.toString()));
	}

	/**
	 * getShort
	 */
	public final static short getShort(HttpSession session, SessionKey sessionKey) {
		return StringUtil.getShort(session.getAttribute(sessionKey.toString()));
	}

	/**
	 * getByte
	 */
	public final static byte getByte(HttpSession session, SessionKey sessionKey) {
		return StringUtil.getByte(session.getAttribute(sessionKey.toString()));
	}

	/**
	 * getFloat
	 */
	public final static float getFloat(HttpSession session, SessionKey sessionKey) {
		return StringUtil.getFloat(session.getAttribute(sessionKey.toString()));
	}

	/**
	 * getDouble	
	 */
	public final static double getDouble(HttpSession session, SessionKey sessionKey) {
		return StringUtil.getDouble(session.getAttribute(sessionKey.toString()));
	}

	/**
	 * getString	
	 */
	public final static String getString(HttpSession session, SessionKey sessionKey) {
		return StringUtil.getString(session.getAttribute(sessionKey.toString()));
	}
}