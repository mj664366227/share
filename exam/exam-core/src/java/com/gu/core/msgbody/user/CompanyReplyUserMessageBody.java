package com.gu.core.msgbody.user;

import com.gu.core.interfaces.MessageBody;

public class CompanyReplyUserMessageBody extends MessageBody {

	private long ideaCompanyCommentId;
	
	public long getIdeaCompanyCommentId() {
		return ideaCompanyCommentId;
	}

	public void setIdeaCompanyCommentId(long ideaCompanyCommentId) {
		this.ideaCompanyCommentId = ideaCompanyCommentId;
	}

}
