package com.exam.core.enums;

import java.util.HashMap;

/**
 * 消息接收者
 * @author lou
 */
public enum MessageReceiver {
	all(0), admin(1), user(2), company(3);

	private int value;
	private static HashMap<Integer, MessageReceiver> map = new HashMap<Integer, MessageReceiver>(MessageReceiver.values().length);

	static {
		for (MessageReceiver massageReceiver : MessageReceiver.values()) {
			map.put(massageReceiver.getValue(), massageReceiver);
		}
	}

	private MessageReceiver(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static MessageReceiver valueOf(byte value) {
		return map.get(value);
	}

}
