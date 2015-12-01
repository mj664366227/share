package com.exam.core.msgbody;

import com.exam.core.interfaces.MessageBody;

public class SystemMessageBody extends MessageBody {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
