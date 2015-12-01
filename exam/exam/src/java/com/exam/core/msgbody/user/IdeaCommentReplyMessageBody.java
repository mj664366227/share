package com.exam.core.msgbody.user;

import com.exam.core.interfaces.MessageBody;

public class IdeaCommentReplyMessageBody extends MessageBody {
	
	private long ideaCommentId;

	public long getIdeaCommentId() {
		return ideaCommentId;
	}

	public void setIdeaCommentId(long ideaCommentId) {
		this.ideaCommentId = ideaCommentId;
	}
}
