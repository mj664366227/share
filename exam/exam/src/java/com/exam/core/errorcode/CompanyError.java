package com.exam.core.errorcode;

import com.exam.core.interfaces.Error;

public enum CompanyError implements Error {
	/**
	 * 企业名字已被使用(2003001)
	 */
	companyOnlyError(2003001, "企业名字已被使用"),
	/**
	 * 企业不存在(2003002)
	 */
	companyNotExists(2003002, "企业不存在");

	private int errorCode;
	private String errorMsg;

	private CompanyError(int errorCode, String errorMsg) {
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
