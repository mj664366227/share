package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 创意评论
 * @author luo
 */
@Pojo
@Message
public class DIdeaComment extends DSuper {
	/**
	 * 创意评论id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 创意id
	 */
	private long ideaId;
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

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
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