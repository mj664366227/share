package com.exam.core.errorcode;

import com.exam.core.interfaces.Error;

public enum ImagesError implements Error {
	/**
	 * 文件类型错误(2002001)
	 */
	fileTypeError(2002001, "文件类型错误"),
	/**
	 * 文件上传失败(2002002)
	 */
	fileUploadError(2002002, "文件上传失败"),
	/**
	 * 文件上传为空(2002003)
	 */
	fileUploadNull(2002003, "文件上传为空");

	private int errorCode;
	private String errorMsg;

	private ImagesError(int errorCode, String errorMsg) {
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
