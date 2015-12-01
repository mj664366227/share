package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;
import com.gu.core.protocol.base.UserBaseResponse;

public class CompanyFansResponse extends SResponse {
	/**
	 * userBase
	 */
	private UserBaseResponse userBase = new UserBaseResponse();
	/**
	 * 我是否已经关注(1-是  0-否)
	 */
	private int isFocus;
	/**
	 * 游标
	 */
	private long lastIndex;

	public UserBaseResponse getUserBase() {
		return userBase;
	}

	public void setUserBase(UserBaseResponse userBase) {
		this.userBase = userBase;
	}

	public int getIsFocus() {
		return isFocus;
	}

	public void setIsFocus(int isFocus) {
		this.isFocus = isFocus;
	}

	public long getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(long lastIndex) {
		this.lastIndex = lastIndex;
	}
}