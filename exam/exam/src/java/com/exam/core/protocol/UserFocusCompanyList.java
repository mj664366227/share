package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;

/**
 * 我关注的企业列表
 * @author ruan
 */
public class UserFocusCompanyList extends SResponse {
	/**
	 * 企业列表
	 */
	private List<UserFocusCompany> list = new ArrayList<UserFocusCompany>();

	public List<UserFocusCompany> getList() {
		return list;
	}

	public void addList(UserFocusCompany userFocusCompany) {
		list.add(userFocusCompany);
	}
}
