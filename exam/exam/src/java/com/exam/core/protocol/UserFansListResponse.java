package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;

public class UserFansListResponse extends SResponse {
	/**
	 * 用户列表
	 */
	private List<UserFansResponse> list = new ArrayList<UserFansResponse>();

	public List<UserFansResponse> getList() {
		return list;
	}

	public void addList(UserFansResponse userFansResponse) {
		list.add(userFansResponse);
	}
}
