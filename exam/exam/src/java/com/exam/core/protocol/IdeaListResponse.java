package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;

/**
 * 创意列表返回协议
 * @author ruan
 */
public class IdeaListResponse extends SResponse {
	/**
	 * 创意总数
	 */
	private int total = 0;
	/**
	 * 创意列表
	 */
	private List<IdeaResponse> ideaResponseList = new ArrayList<IdeaResponse>();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<IdeaResponse> getIdeaResponseList() {
		return ideaResponseList;
	}

	public void addIdeaResponse(IdeaResponse ideaResponse) {
		this.ideaResponseList.add(ideaResponse);
	}

	public void setIdeaResponseList(List<IdeaResponse> ideaResponseList) {
		this.ideaResponseList = ideaResponseList;
	}
}