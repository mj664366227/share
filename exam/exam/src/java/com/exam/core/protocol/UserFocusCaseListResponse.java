package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;

public class UserFocusCaseListResponse extends SResponse {
	private List<UserFocusCaseResponse> list = new ArrayList<>();

	public List<UserFocusCaseResponse> getList() {
		return list;
	}

	public void addList(UserFocusCaseResponse userFocusCaseResponse) {
		list.add(userFocusCaseResponse);
	}
}
