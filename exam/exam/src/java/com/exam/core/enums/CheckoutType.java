package com.exam.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 提现类型
 * @author ruan
 */
public enum CheckoutType {
	/**
	 * 微信(1)
	 */
	wechat(1, "微信");

	private int value;
	private String description;
	private static Map<Integer, CheckoutType> map = new HashMap<>(CheckoutType.values().length);

	static {
		for (CheckoutType checkoutType : CheckoutType.values()) {
			map.put(checkoutType.getValue(), checkoutType);
		}
	}

	private CheckoutType(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public static CheckoutType valueOf(int value) {
		return map.get(value);
	}
}