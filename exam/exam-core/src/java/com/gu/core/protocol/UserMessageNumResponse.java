package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;

public class UserMessageNumResponse extends SResponse {
	
	private int userMessageNum;

	public int getUserMessageNum() {
		return userMessageNum;
	}

	public void setUserMessageNum(int userMessageNum) {
		this.userMessageNum = userMessageNum;
	}
}
