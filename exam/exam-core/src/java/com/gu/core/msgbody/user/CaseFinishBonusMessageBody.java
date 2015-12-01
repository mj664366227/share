package com.gu.core.msgbody.user;

import com.gu.core.interfaces.MessageBody;

/**
 * 专案结束分红消息
 * @author ruan
 */
public class CaseFinishBonusMessageBody extends MessageBody {
	private long caseId;
	
	private int points;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}
}