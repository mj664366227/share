package com.gu.core.msgbody.user;

import com.gu.core.interfaces.MessageBody;

public class CaseNewMessageBody extends MessageBody {

	private long caseId;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

}
