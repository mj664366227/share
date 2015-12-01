package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;

public class UserFocusResponse extends SResponse {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 用户头像
	 */
	private String avatar;
	/**
	 * 用户等级
	 */
	private int level;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
