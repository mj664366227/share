package com.exam.core.enums;

import java.util.HashMap;

/**
 * 性别枚举
 * @author luo
 */
public enum Sex {
	/**
	 * 男(1)
	 */
	BOY(1),
	/**
	 * 女(2)
	 */
	GRIL(2);

	private int value;
	private static HashMap<Byte, Sex> map = new HashMap<Byte, Sex>(Sex.values().length);

	static {
		for (Sex sex : Sex.values()) {
			map.put(sex.getValue(), sex);
		}
	}

	private Sex(int value) {
		this.value = value;
	}

	public byte getValue() {
		return (byte) value;
	}

	public static Sex valueOf(byte value) {
		return map.get(value);
	}
}
