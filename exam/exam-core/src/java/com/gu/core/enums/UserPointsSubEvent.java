package com.gu.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户扣G点事件(20000开头)
 * @author ruan
 */
public enum UserPointsSubEvent {
	/**
	 * G点提现(20001)
	 */
	checkOutGPoint(20001, "G点提现");
	private int event;
	private String description;
	
	private static Map<Integer, UserPointsSubEvent> map = new HashMap<>(UserPointsSubEvent.values().length);

	static {
		for (UserPointsSubEvent userPointsSubEvent : UserPointsSubEvent.values()) {
			map.put(userPointsSubEvent.getValue(), userPointsSubEvent);
		}
	}

	public final static Map<Integer, UserPointsSubEvent> getMap() {
		return map;
	}

	private UserPointsSubEvent(int event, String description) {
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