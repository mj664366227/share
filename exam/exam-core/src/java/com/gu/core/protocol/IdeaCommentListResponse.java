package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;

/**
 * 创意评论列表返回协议
 * @author ruan
 */
public class IdeaCommentListResponse extends SResponse {
	/**
	 * 创意评论总数
	 */
	private int total = 0;
	/**
	 * 创意评论列表
	 */
	private List<IdeaCommentResponse> ideaCommentResponseList = new ArrayList<IdeaCommentResponse>();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<IdeaCommentResponse> getIdeaCommentResponseList() {
		return ideaCommentResponseList;
	}

	public void addIdeaCommentResponse(IdeaCommentResponse ideaCommentResponse) {
		this.ideaCommentResponseList.add(ideaCommentResponse);
	}

	public void setIdeaCommentResponseList(List<IdeaCommentResponse> ideaCommentResponseList) {
		this.ideaCommentResponseList = ideaCommentResponseList;
	}
}