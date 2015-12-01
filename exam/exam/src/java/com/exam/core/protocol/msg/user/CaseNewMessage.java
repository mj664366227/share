package com.exam.core.protocol.msg.user;

import com.exam.core.protocol.msg.msgbody.UserMessageBody;

public class CaseNewMessage extends UserMessageBody {
	private long caseId;

	private String caseName;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

}
