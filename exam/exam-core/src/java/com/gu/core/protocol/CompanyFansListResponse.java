package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;

/**
 * 企业粉丝列表协议
 * @author ruan
 */
public class CompanyFansListResponse extends SResponse {
	/**
	 * list
	 */
	private List<CompanyFansResponse> list = new ArrayList<>();

	public List<CompanyFansResponse> getList() {
		return list;
	}

	public void addList(CompanyFansResponse companyFansResponse) {
		list.add(companyFansResponse);
	}
}