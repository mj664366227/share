package com.gu.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 充值渠道
 * @author ruan
 */
public enum ChargeChannel {
	/**
	 * 微信扫码支付(1)
	 */
	wechatQRCode(1, "微信扫码支付");

	private int value;
	private String description;
	private static Map<Integer, ChargeChannel> map = new HashMap<>(ChargeChannel.values().length);

	static {
		for (ChargeChannel chargeChannel : ChargeChannel.values()) {
			map.put(chargeChannel.getValue(), chargeChannel);
		}
	}

	private ChargeChannel(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public static ChargeChannel valueOf(int value) {
		return map.get(value);
	}
}