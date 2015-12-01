package com.exam.core.msgbody.company;

import com.exam.core.interfaces.MessageBody;

public class FocusCaseMessageBody extends MessageBody {

	private long caseId;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}
}
