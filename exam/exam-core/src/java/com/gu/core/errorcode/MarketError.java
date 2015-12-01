package com.gu.core.errorcode;

import com.gu.core.interfaces.Error;

public enum MarketError implements Error {
	/**
	 * 创意圈作品不存在(2006001)
	 */
	marketOpusNotExists(2006001, "创意圈作品不存在");

	private int errorCode;
	private String errorMsg;

	private MarketError(int errorCode, String errorMsg) {
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
