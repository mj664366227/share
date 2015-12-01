package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;

public class RegResponse extends SResponse {
	/**
	 * 用户id
	 */
	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
