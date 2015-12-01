package com.gu.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业加G点事件(30000开头)
 * @author ruan
 */
public enum CompanyPointsAddEvent {
	/**
	 * 后台赠送(30001)
	 */
	adminSend(30001, "后台赠送"),
	/**
	 * 专案结算剩余G点(30002)
	 */
	caseFinish(30002, "专案结算剩余G点"),
	/**
	 * 充值(30003)
	 */
	recharge(30003, "充值");

	private int event;
	private String description;

	private static Map<Integer, CompanyPointsAddEvent> map = new HashMap<>(CompanyPointsAddEvent.values().length);

	static {
		for (CompanyPointsAddEvent companyPointsAddEvent : CompanyPointsAddEvent.values()) {
			map.put(companyPointsAddEvent.getValue(), companyPointsAddEvent);
		}
	}

	public final static Map<Integer, CompanyPointsAddEvent> getMap() {
		return map;
	}

	private CompanyPointsAddEvent(int event, String description) {
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