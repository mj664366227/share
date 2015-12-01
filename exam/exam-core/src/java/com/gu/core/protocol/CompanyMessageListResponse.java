package com.gu.core.protocol;

import java.util.List;

import com.gu.core.interfaces.SResponse;

public class CompanyMessageListResponse extends SResponse {
	
	private int total;

	private List<CompanyMessageResponse> list;

	public List<CompanyMessageResponse> getList() {
		return list;
	}

	public void setList(List<CompanyMessageResponse> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
