package com.gu.core.enums;

import java.util.HashMap;

public enum UserAccusationType {

	Case(1), Idea(2), IdeaComment(3), Flow(4);

	private int type;
	private static HashMap<Byte, UserAccusationType> map = new HashMap<Byte, UserAccusationType>(UserAccusationType.values().length);

	static {
		for (UserAccusationType type : UserAccusationType.values()) {
			map.put(type.getType(), type);
		}
	}

	private UserAccusationType(int type) {
		this.type = type;
	}

	public byte getType() {
		return (byte) type;
	}

	public static UserAccusationType typeOf(byte type) {
		return map.get(type);
	}
}
