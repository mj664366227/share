package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;
import com.gu.core.interfaces.Error;

/**
 * 充值返回
 * @author ruan
 */
public class ChargeResponse extends SResponse {
	/**
	 * 错误码(0证明是没错)
	 */
	private int error = 0;
	/**
	 * 二维码字符串
	 */
	private String qrcode;
	/**
	 * 订单号
	 */
	private long orderId;

	public int getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error.getErrorCode();
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
}