package com.gu.core.enums;

import java.util.HashMap;

/**
 * 第三方平台类型
 * @author ruan
 */
public enum OpenPlatform {
	/**
	 * QQ(1)
	 */
	QQ(1,"QQ"), 
	/**
	 * 朋友圈(2)
	 */
	PengYouQuan(2,"朋友圈"), 
	/**
	 * 微信好友(3)
	 */
	WeChatFriend(3,"微信好友"), 
	/**
	 * 微博(4)
	 */
	Weibo(4,"微博"), 
	/**
	 * QQ空间(5)
	 */
	QZone(5,"QQ空间");

	private int value;
	private String description;
	
	private static HashMap<Integer, OpenPlatform> map = new HashMap<Integer, OpenPlatform>(OpenPlatform.values().length);

	static {
		for (OpenPlatform sex : OpenPlatform.values()) {
			map.put(sex.getValue(), sex);
		}
	}

	private OpenPlatform(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public final static OpenPlatform valueOf(int value) {
		return map.get(value);
	}

	public final static HashMap<Integer, OpenPlatform> getOpenPlatformMap() {
		return map;
	}
}
