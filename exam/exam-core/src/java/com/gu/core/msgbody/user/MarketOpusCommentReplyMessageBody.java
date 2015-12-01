package com.gu.core.msgbody.user;

import com.gu.core.interfaces.MessageBody;

public class MarketOpusCommentReplyMessageBody extends MessageBody {
	
	private long marketOpusCommentId;

	public long getMarketOpusCommentId() {
		return marketOpusCommentId;
	}

	public void setMarketOpusCommentId(long marketOpusCommentId) {
		this.marketOpusCommentId = marketOpusCommentId;
	}
}
