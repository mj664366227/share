package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;

public class UserTakePartInCaseListResponse extends SResponse {
	private List<UserTakePartInCase> list = new ArrayList<UserTakePartInCase>();

	public List<UserTakePartInCase> getList() {
		return list;
	}

	public void addList(UserTakePartInCase userTakePartInCase) {
		list.add(userTakePartInCase);
	}
}