package com.exam.core.protocol.msg.user;

import com.exam.core.protocol.msg.msgbody.UserMessageBody;

public class UserInvitePubIdeaMessage extends UserMessageBody {
	/**
	 * 专案id
	 */
	private long caseId;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}
}