package com.gu.core.msgbody.company;

import com.gu.core.interfaces.MessageBody;

public class UserReplyCompanyMessageBody extends MessageBody {

	private long ideaId;

	private long ideaCompanyCommentId;
	
	private long fromId;

	public long getIdeaCompanyCommentId() {
		return ideaCompanyCommentId;
	}

	public void setIdeaCompanyCommentId(long ideaCompanyCommentId) {
		this.ideaCompanyCommentId = ideaCompanyCommentId;
	}

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	public long getFromId() {
		return fromId;
	}

	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

}
