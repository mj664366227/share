package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;

public class UserWalletResponse extends SResponse {
	/**
	 * 我的点数
	 */
	private int points;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
