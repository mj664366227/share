package com.exam.core.msgbody.company;

import com.exam.core.interfaces.MessageBody;

public class UserFocusCompanyMessageBody extends MessageBody {

	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
