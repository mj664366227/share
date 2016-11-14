package com.share.protocol;

import com.share.core.annotation.Require;

public class ReqUserReg {
	/**
	 * 手机号
	 */
	private long mobile;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 密码
	 */
	@Require(require = false)
	private String password;

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getNickname() {
		return nickName;
	}

	public void setNickname(String nickname) {
		this.nickName = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
