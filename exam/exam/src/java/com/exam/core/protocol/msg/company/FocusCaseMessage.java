package com.exam.core.protocol.msg.company;

import com.exam.core.protocol.msg.msgbody.CompanyMessageBody;

/**
 * 用户关注专案消息
 * @author luo
 */
public class FocusCaseMessage extends CompanyMessageBody {
	/**
	 * 专案id
	 */
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
