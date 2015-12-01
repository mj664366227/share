package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;

/**
 * 专案列表返回协议
 * @author ruan
 */
public class CaseListResponse extends SResponse {
	private List<OneCaseResponse> list = new ArrayList<>();

	public List<OneCaseResponse> getList() {
		return list;
	}

	public void setList(List<OneCaseResponse> list) {
		this.list = list;
	}

	public void addList(OneCaseResponse oneCaseResponse) {
		list.add(oneCaseResponse);
	}
}
