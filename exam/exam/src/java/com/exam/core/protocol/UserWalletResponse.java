package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;

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
