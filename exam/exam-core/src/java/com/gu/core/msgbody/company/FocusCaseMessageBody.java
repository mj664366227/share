package com.gu.core.msgbody.company;

import com.gu.core.interfaces.MessageBody;

public class FocusCaseMessageBody extends MessageBody {

	private long caseId;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}
}
