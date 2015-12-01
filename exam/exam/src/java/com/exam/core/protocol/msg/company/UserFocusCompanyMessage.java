package com.exam.core.protocol.msg.company;

import com.exam.core.protocol.msg.msgbody.CompanyMessageBody;

/**
 * 用户关注企业消息
 * @author luo
 */
public class UserFocusCompanyMessage extends CompanyMessageBody {
	/**
	 * 用户id
	 */
	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
