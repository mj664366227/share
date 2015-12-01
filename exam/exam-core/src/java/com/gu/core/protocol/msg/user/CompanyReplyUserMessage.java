package com.gu.core.protocol.msg.user;

import com.gu.core.protocol.msg.msgbody.UserMessageBody;

public class CompanyReplyUserMessage extends UserMessageBody {
	/**
	 * 创意id
	 */
	private long ideaId;
	/**
	 * 回复id
	 */
	private long ideaCompanyCommentId;
	/**
	 * 回复内容
	 */
	private String content;

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	public long getIdeaCompanyCommentId() {
		return ideaCompanyCommentId;
	}

	public void setIdeaCompanyCommentId(long ideaCompanyCommentId) {
		this.ideaCompanyCommentId = ideaCompanyCommentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
