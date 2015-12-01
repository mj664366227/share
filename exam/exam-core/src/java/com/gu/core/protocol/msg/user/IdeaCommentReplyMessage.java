package com.gu.core.protocol.msg.user;

import com.gu.core.protocol.msg.msgbody.UserMessageBody;

/**
 * 创意评论消息
 * @author luo
 */
public class IdeaCommentReplyMessage extends UserMessageBody {
	/**
	 * 创意id
	 */
	private long ideaId;
	/**
	 * 创意评论id
	 */
	private long ideaCommentId;
	/**
	 * 评论内容
	 */
	private String content;

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	public long getIdeaCommentId() {
		return ideaCommentId;
	}

	public void setIdeaCommentId(long ideaCommentId) {
		this.ideaCommentId = ideaCommentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
