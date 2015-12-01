package com.gu.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户加G点事件(10000开头)
 * @author ruan
 */
public enum UserPointsAddEvent {
	/**
	 * 专案结算加钱(10001)
	 */
	caseFinish(10001, "专案结算加钱"),
	/**
	 * 预留手机号送G(10002)
	 */
	storeMobile(10002, "预留手机号送G点");
	private int event;
	private String description;
	

	private static Map<Integer, UserPointsAddEvent> map = new HashMap<>(UserPointsAddEvent.values().length);

	static {
		for (UserPointsAddEvent userPointsAddEvent : UserPointsAddEvent.values()) {
			map.put(userPointsAddEvent.getValue(), userPointsAddEvent);
		}
	}

	public final static Map<Integer, UserPointsAddEvent> getMap() {
		return map;
	}

	private UserPointsAddEvent(int event, String description) {
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