package com.exam.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户减积分事件(60000开头)
 * @author ruan
 */
public enum UserScoreSubEvent {
	/**
	 * 商城积分兑换(60001)
	 */
	shopExchange(60001, "商城积分兑换");
	private int event;
	private String description;

	private static Map<Integer, UserScoreSubEvent> map = new HashMap<>(UserScoreSubEvent.values().length);

	static {
		for (UserScoreSubEvent userScoreSubEvent : UserScoreSubEvent.values()) {
			map.put(userScoreSubEvent.getValue(), userScoreSubEvent);
		}
	}

	public final static Map<Integer, UserScoreSubEvent> getMap() {
		return map;
	}

	private UserScoreSubEvent(int event, String description) {
		this.event = event;
		this.description = description;
	}

	/**
	 * 获取值
	 */
	public int getValue() {
		return event;
	}

	/**
	 * 获取描述
	 */
	public String getDescription() {
		return description;
	}
}