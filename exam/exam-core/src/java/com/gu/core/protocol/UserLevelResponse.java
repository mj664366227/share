package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;

public class UserLevelResponse extends SResponse {
	/**
	 * 用户等级
	 */
	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
