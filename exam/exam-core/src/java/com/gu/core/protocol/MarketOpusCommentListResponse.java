package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;

/**
 * 创意圈_作品评论列表返回协议
 * @author ruan
 */
public class MarketOpusCommentListResponse extends SResponse {
	/**
	 * 创意圈_作品评论总数
	 */
	private int total = 0;
	/**
	 * 创意圈_作品评论列表
	 */
	private List<MarketOpusCommentResponse> marketOpusCommentResponseList = new ArrayList<MarketOpusCommentResponse>();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<MarketOpusCommentResponse> getMarketOpusCommentResponseList() {
		return marketOpusCommentResponseList;
	}

	public void addMarketOpusCommentResponse(MarketOpusCommentResponse marketOpusCommentResponse) {
		this.marketOpusCommentResponseList.add(marketOpusCommentResponse);
	}

	public void setMarketOpusCommentResponseList(List<MarketOpusCommentResponse> marketOpusCommentResponseList) {
		this.marketOpusCommentResponseList = marketOpusCommentResponseList;
	}
}