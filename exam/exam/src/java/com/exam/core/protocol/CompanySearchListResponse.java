package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;
import com.exam.core.protocol.base.CompanyBaseResponse;

/**
 * 企业搜索返回协议
 * @author luo
 */
public class CompanySearchListResponse extends SResponse {
	/**
	 * 结果列表
	 */
	private List<CompanyBaseResponse> list = new ArrayList<CompanyBaseResponse>();

	public List<CompanyBaseResponse> getList() {
		return list;
	}

	public void setList(List<CompanyBaseResponse> list) {
		this.list = list;
	}

}
