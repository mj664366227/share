package com.exam.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 举报类型
 * @author ruan
 */
public enum AccusationType {
	/**
	 * 举报专案
	 */
	Case(1),
	/**
	 * 举报创意
	 */
	Idea(2),
	/**
	 * 举报创意评论
	 */
	IdeaComment(3),
	/**
	 * 举报吐槽
	 */
	Flow(4),
	/**
	 * 举报个人
	 */
	User(5),
	/**
	 * 举报创意圈作品
	 */
	MarketOpus(6),
	/**
	 * 举报创意圈作品的评论
	 */
	MarketOpusComment(7),
	/**
	 * 举报企业
	 */
	Company(8);
	
	private int value;

	private static Map<Integer, AccusationType> map = new HashMap<>(AccusationType.values().length);

	static {
		for (AccusationType accusationType : AccusationType.values()) {
			map.put(accusationType.getValue(), accusationType);
		}
	}

	private AccusationType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public final static AccusationType valueOf(int value) {
		return map.get(value);
	}
}