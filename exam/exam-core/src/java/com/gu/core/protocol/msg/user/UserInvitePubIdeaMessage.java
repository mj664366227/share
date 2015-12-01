package com.gu.core.protocol.msg.user;

import com.gu.core.protocol.msg.msgbody.UserMessageBody;

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