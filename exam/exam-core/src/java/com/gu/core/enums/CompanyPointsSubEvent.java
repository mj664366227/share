package com.gu.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业扣G点事件(40000开头)
 * @author ruan
 */
public enum CompanyPointsSubEvent {
	/**
	 * 发布专案(40001)
	 */
	pubCase(40001, "发布专案");
	private int event;

	private String description;
	
	private static Map<Integer, CompanyPointsSubEvent> map = new HashMap<>(CompanyPointsSubEvent.values().length);

	static {
		for (CompanyPointsSubEvent companyPointsSubEvent : CompanyPointsSubEvent.values()) {
			map.put(companyPointsSubEvent.getValue(), companyPointsSubEvent);
		}
	}

	public final static Map<Integer, CompanyPointsSubEvent> getMap() {
		return map;
	}

	private CompanyPointsSubEvent(int event, String description) {
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