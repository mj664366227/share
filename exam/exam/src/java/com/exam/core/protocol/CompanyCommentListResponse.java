package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;

/**
 * 企业回复创意列表
 * @author ruan
 */
public class CompanyCommentListResponse extends SResponse {
	/**
	 * 总数
	 */
	private int total;
	/**
	 * 列表
	 */
	private List<CompanyCommentResponse> list = new ArrayList<CompanyCommentResponse>();

	public List<CompanyCommentResponse> getList() {
		return list;
	}

	public void addList(CompanyCommentResponse companyCommentResponse) {
		this.list.add(companyCommentResponse);
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}