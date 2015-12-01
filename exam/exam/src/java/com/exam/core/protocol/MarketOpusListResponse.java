package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;

/**
 * 创意圈_作品列表返回协议
 * @author luo
 */
public class MarketOpusListResponse extends SResponse {
	/**
	 * 创意圈_作品总数
	 */
	private int total = 0;
	/**
	 * 创意圈_作品列表
	 */
	private List<MarketOpusResponse> marketOpusResponseList = new ArrayList<MarketOpusResponse>();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<MarketOpusResponse> getMarketOpusResponseList() {
		return marketOpusResponseList;
	}

	public void addMarketOpusResponse(MarketOpusResponse marketOpusResponse) {
		this.marketOpusResponseList.add(marketOpusResponse);
	}

	public void setMarketOpusResponseList(List<MarketOpusResponse> marketOpusResponseList) {
		this.marketOpusResponseList = marketOpusResponseList;
	}
}