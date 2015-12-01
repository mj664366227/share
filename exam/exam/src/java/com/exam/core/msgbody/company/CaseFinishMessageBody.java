package com.exam.core.msgbody.company;

import com.exam.core.interfaces.MessageBody;

/**
 * 专案结算消息
 * @author ruan
 */
public class CaseFinishMessageBody extends MessageBody {
	private long caseId;
	/**
	 * 计划分红G点
	 */
	private int pointsPlan;

	/**
	 * 实际分红G点
	 */
	private int pointsReal;

	/**
	 * 剩余分红G点
	 */
	private int pointsSurplus;

	public int getPointsPlan() {
		return pointsPlan;
	}

	public void setPointsPlan(int pointsPlan) {
		this.pointsPlan = pointsPlan;
	}

	public int getPointsReal() {
		return pointsReal;
	}

	public void setPointsReal(int pointsReal) {
		this.pointsReal = pointsReal;
	}

	public int getPointsSurplus() {
		return pointsSurplus;
	}

	public void setPointsSurplus(int pointsSurplus) {
		this.pointsSurplus = pointsSurplus;
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}
}