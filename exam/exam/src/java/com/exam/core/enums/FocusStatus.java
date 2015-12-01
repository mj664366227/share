package com.exam.core.enums;

/**
 * 关注状态
 * @author ruan
 */
public enum FocusStatus {
	/**
	 * 互不关注(0)
	 */
	NONE(0),
	/**
	 * 我关注了别人(1)
	 */
	FOCUS(1),
	/**
	 * 别人关注了我(2)
	 */
	BE_FOCUS(2),
	/**
	 * 互相关注(3)
	 */
	EACH(3);
	private int status;

	private FocusStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
}