package com.gu.core.errorcode;

import com.gu.core.interfaces.Error;

public enum MessageError implements Error {
	/**
	 * 消息不存在(2007001)
	 */
	messageNotExists(2007001, "消息不存在");

	private int errorCode;
	private String errorMsg;

	private MessageError(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
