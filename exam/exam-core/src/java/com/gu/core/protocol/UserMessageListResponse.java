package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;

public class UserMessageListResponse extends SResponse {
	
	private int userMessageNum;

	private List<UserMessageResponse> list = new ArrayList<UserMessageResponse>();

	public List<UserMessageResponse> getList() {
		return list;
	}

	public void setList(List<UserMessageResponse> list) {
		this.list = list;
	}

	public int getUserMessageNum() {
		return userMessageNum;
	}

	public void setUserMessageNum(int userMessageNum) {
		this.userMessageNum = userMessageNum;
	}
}
