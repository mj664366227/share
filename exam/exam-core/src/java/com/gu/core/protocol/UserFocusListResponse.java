package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;

public class UserFocusListResponse extends SResponse {
	/**
	 * 用户列表
	 */
	private List<UserFocusResponse> list = new ArrayList<UserFocusResponse>();

	public List<UserFocusResponse> getList() {
		return list;
	}

	public void addList(UserFocusResponse userFocusResponse) {
		list.add(userFocusResponse);
	}
}
