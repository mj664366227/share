package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;

/**
 * 创意圈_作品详细信息返回协议
 * @author luo
 */
public class MarketOpusIdResponse extends SResponse {
	/**
	 * 创意圈_作品id
	 */
	private long marketOpusId;
	
	private int createTime;

	public long getMarketOpusId() {
		return marketOpusId;
	}

	public void setMarketOpusId(long marketOpusId) {
		this.marketOpusId = marketOpusId;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

}
