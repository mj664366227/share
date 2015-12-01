package com.exam.core.errorcode;

import com.exam.core.interfaces.Error;

public enum SystemError implements Error {
	/**
	 * sign错误(1001001)
	 */
	signError(1001001, "sign错误"),
	/**
	 * 接口超时(1001002)
	 */
	timeOut(1001002, "接口超时"),
	/**
	 * 请求参数错误(1001003)
	 */
	parameterError(1001003, "请求参数错误"),
	/**
	 * 系统未知错误(1001004)
	 */
	unknowError(1001004, "系统未知错误"),
	/**
	 * 图片没有上传
	 */
	imageNullError(1001005, "图片没有上传"),
	/**
	 * 亲,你的版本太旧了,快去更新吧！(1001006)
	 */
	versionOldError(1001006, "亲,你的版本太旧了,快去更新吧！");

	private int errorCode;
	private String errorMsg;

	private SystemError(int errorCode, String errorMsg) {
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
