package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;
import com.gu.core.protocol.base.CompanyBaseResponse;

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
