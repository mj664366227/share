package com.gu.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户加积分事件(50000开头)
 * @author ruan
 */
public enum UserScoreAddEvent {
	/**
	 * 发布吐槽(50001)
	 */
	flowPub(50001, "发布吐槽"),
	/**
	 * 发表创意(50002)
	 */
	ideaPub(50002, "发表创意"),
	/**
	 * 单日在线满20分钟(50003)
	 */
	online20min(50003, "单日在线满20分钟"),
	/**
	 * 登录(50004)
	 */
	Login(50004, "登录"),
	/**
	 * 首次完善个人资料(50005)
	 */
	firstFillUpInfo(50005, "首次完善个人资料"),
	/**
	 * 点子前3(50006)
	 */
	ideaTop3(50006, "点子前3");

	private int event;
	private String description;
	
	private static Map<Integer, UserScoreAddEvent> map = new HashMap<>(UserScoreAddEvent.values().length);

	static {
		for (UserScoreAddEvent userScoreAddEvent : UserScoreAddEvent.values()) {
			map.put(userScoreAddEvent.getValue(), userScoreAddEvent);
		}
	}

	public final static Map<Integer, UserScoreAddEvent> getMap() {
		return map;
	}

	private UserScoreAddEvent(int event, String description) {
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