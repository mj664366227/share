package com.gu.core.protocol.msg.company;

import com.gu.core.protocol.msg.msgbody.CompanyMessageBody;

/**
 * 用户关注企业消息
 * @author luo
 */
public class IdeaPubMessage extends CompanyMessageBody {

	private long caseId;

	private long ideaId;

	private String caseName;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
}
