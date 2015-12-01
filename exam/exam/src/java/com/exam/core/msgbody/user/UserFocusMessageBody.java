package com.exam.core.msgbody.user;

import com.exam.core.interfaces.MessageBody;

public class UserFocusMessageBody extends MessageBody {

	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
