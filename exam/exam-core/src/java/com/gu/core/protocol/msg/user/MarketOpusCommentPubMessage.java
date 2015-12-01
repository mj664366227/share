package com.gu.core.protocol.msg.user;

import com.gu.core.protocol.msg.msgbody.UserMessageBody;

/**
 * 作品评论消息
 * @author luo
 */
public class MarketOpusCommentPubMessage extends UserMessageBody {
	/**
	 * 作品id
	 */
	private long marketOpusId;
	/**
	 * 作品评论id
	 */
	private long marketOpusCommentId;
	/**
	 * 评论内容
	 */
	private String content;

	public long getMarketOpusId() {
		return marketOpusId;
	}

	public void setMarketOpusId(long marketOpusId) {
		this.marketOpusId = marketOpusId;
	}

	public long getMarketOpusCommentId() {
		return marketOpusCommentId;
	}

	public void setMarketOpusCommentId(long marketOpusCommentId) {
		this.marketOpusCommentId = marketOpusCommentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
