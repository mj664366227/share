package com.exam.core.msgbody.user;

import com.exam.core.interfaces.MessageBody;

public class MarketOpusCommentPubMessageBody extends MessageBody {
	
	private long marketOpusCommentId;

	public long getMarketOpusCommentId() {
		return marketOpusCommentId;
	}

	public void setMarketOpusCommentId(long marketOpusCommentId) {
		this.marketOpusCommentId = marketOpusCommentId;
	}
}
