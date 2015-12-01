package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;

public class UserMessageNumResponse extends SResponse {
	
	private int userMessageNum;

	public int getUserMessageNum() {
		return userMessageNum;
	}

	public void setUserMessageNum(int userMessageNum) {
		this.userMessageNum = userMessageNum;
	}
}
