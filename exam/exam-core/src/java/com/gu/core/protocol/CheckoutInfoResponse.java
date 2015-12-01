package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;

/**
 * 用户提现信息
 * @author ruan
 */
public class CheckoutInfoResponse extends SResponse {
	/**
	 * 用户剩余的G点数
	 */
	private int userPoints;
	/**
	 * 系统允许提现的最少G点数
	 */
	private int checkoutLimitPoints;
	/**
	 * 是否已经设置了支付密码(1-是 0-否)
	 */
	private int isSetPaymentPassword;

	public int getUserPoints() {
		return userPoints;
	}

	public void setUserPoints(int userPoints) {
		this.userPoints = userPoints;
	}

	public int getCheckoutLimitPoints() {
		return checkoutLimitPoints;
	}

	public void setCheckoutLimitPoints(int checkoutLimitPoints) {
		this.checkoutLimitPoints = checkoutLimitPoints;
	}

	public int getIsSetPaymentPassword() {
		return isSetPaymentPassword;
	}

	public void setIsSetPaymentPassword(int isSetPaymentPassword) {
		this.isSetPaymentPassword = isSetPaymentPassword;
	}
}