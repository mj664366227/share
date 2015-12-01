package com.exam.core.protocol.msg.user;

import com.exam.core.protocol.msg.msgbody.UserMessageBody;

/**
 * 专案结束分红消息
 * @author luo
 */
public class CaseFinishBonusMessage extends UserMessageBody {
	private long caseId;

	private String caseName;

	private int points;

	private String content = "";

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCaseId() {
		return caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}