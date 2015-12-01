package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 创意圈_作品评论
 * @author luo
 */
@Pojo
@Message
public class DMarketOpusComment extends DSuper {
	/**
	 * 创意评论id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 创意圈_作品id
	 */
	private long marketOpusId;
	/**
	 * 发表时间
	 */
	private int createTime;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 回复的用户id
	 */
	private long replyUserId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

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
}