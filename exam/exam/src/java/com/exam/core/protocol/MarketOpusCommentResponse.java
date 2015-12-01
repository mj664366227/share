package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;
import com.exam.core.protocol.base.UserBaseResponse;

/**
 * 单个创意圈_作品评论
 * @author ruan
 */
public class MarketOpusCommentResponse extends SResponse {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 用户信息
	 */
	private UserBaseResponse userBase;
	/**
	 * 创意圈_作品评论id
	 */
	private long marketOpusCommentId;
	/**
	 * 创意圈_作品评论发表时间
	 */
	private int createTime;
	/**
	 * 创意圈_作品评论内容
	 */
	private String content;
	/**
	 * 被回复用户id
	 */
	private long replyUserId;
	/**
	 * 被回复用户信息
	 */
	private UserBaseResponse replyUserBase;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getMarketOpusCommentId() {
		return marketOpusCommentId;
	}

	public void setMarketOpusCommentId(long marketOpusCommentId) {
		this.marketOpusCommentId = marketOpusCommentId;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(long replyUserId) {
		this.replyUserId = replyUserId;
	}

	public UserBaseResponse getUserBase() {
		return userBase;
	}

	public void setUserBase(UserBaseResponse userBase) {
		this.userBase = userBase;
	}

	public UserBaseResponse getReplyUserBase() {
		return replyUserBase;
	}

	public void setReplyUserBase(UserBaseResponse replyUserBase) {
		this.replyUserBase = replyUserBase;
	}
}
