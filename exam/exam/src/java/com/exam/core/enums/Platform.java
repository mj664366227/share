package com.exam.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户端平台
 * @author ruan
 */
public enum Platform {
	/**
	 * 安卓
	 */
	Android(1),
	/**
	 * IOS
	 */
	IOS(2),
	/**
	 * IPAD
	 */
	IPAD(3),
	/**
	 * WEB
	 */
	WEB(4);

	private int value;

	private static Map<Integer, Platform> map = new HashMap<>();

	static {
		for (Platform platform : Platform.values()) {
			map.put(platform.getValue(), platform);
		}
	}

	private Platform(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public final static Platform valueOf(int value) {
		return map.get(value);
	}

	public final static Map<Integer, Platform> getPlatformMap() {
		return map;
	}
}