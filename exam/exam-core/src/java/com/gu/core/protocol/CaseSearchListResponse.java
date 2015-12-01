package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;

/**
 * 专案搜索返回协议
 * @author luo
 */
public class CaseSearchListResponse extends SResponse {
	/**
	 * 总数
	 */
	private int total = 0;
	/**
	 * 结果列表
	 */
	private List<CaseSearchResponse> list = new ArrayList<CaseSearchResponse>();

	public List<CaseSearchResponse> getList() {
		return list;
	}

	public void setList(List<CaseSearchResponse> list) {
		this.list = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
