package com.gu.core.protocol.msg.user;

import com.gu.core.protocol.msg.msgbody.UserMessageBody;

/**
 * 新增粉丝消息
 * @author luo
 */
public class UserFocusMessage extends UserMessageBody {
	/**
	 * 是否已经关注
	 */
	private int isFocus;
	/**
	 * 一句话亮身份
	 */
	private String senderIdentity;

	public int getIsFocus() {
		return isFocus;
	}

	public void setIsFocus(int isFocus) {
		this.isFocus = isFocus;
	}

	public String getSenderIdentity() {
		return senderIdentity;
	}

	public void setSenderIdentity(String senderIdentity) {
		this.senderIdentity = senderIdentity;
	}
}
