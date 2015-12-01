package com.gu.core.msgbody.user;

import com.gu.core.interfaces.MessageBody;

public class UserFocusMessageBody extends MessageBody {

	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
