package com.gu.core.errorcode;

import com.gu.core.interfaces.Error;

public enum CaseError implements Error {
	/**
	 * 专案不存在(2004001)
	 */
	caseNotExists(2004001, "专案不存在"),
	/**
	 * 专案关注失败(2004002)
	 */
	caseFocusError(2004002, "专案关注失败"),
	/**
	 * 用户对专案点赞统计不存在(2004003)
	 */
	casePraiseCount(2004003, "用户对专案点赞统计不存在"),
	/**
	 * 专案已过期(2004004)
	 */
	caseOvertime(2004004, "专案已过期"),
	/**
	 * 此专案已关注(2004005)
	 */
	caseHasFocus(2004005, "此专案已关注"),
	/**
	 * 此专案未关注(2004006)
	 */
	caseHasNotFocus(2004006, "此专案未关注");

	private int errorCode;
	private String errorMsg;

	private CaseError(int errorCode, String errorMsg) {
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
