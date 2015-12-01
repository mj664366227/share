package com.gu.core.msgbody;

import com.gu.core.interfaces.MessageBody;

public class SystemMessageBody extends MessageBody {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
