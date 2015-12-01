package com.exam.core.protocol;

import com.exam.core.interfaces.Error;
import com.exam.core.interfaces.SResponse;

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