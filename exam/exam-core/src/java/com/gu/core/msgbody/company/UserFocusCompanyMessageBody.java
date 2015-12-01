package com.gu.core.msgbody.company;

import com.gu.core.interfaces.MessageBody;

public class UserFocusCompanyMessageBody extends MessageBody {

	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
