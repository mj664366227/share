package com.exam.core.protocol.msg.company;

import com.exam.core.protocol.msg.msgbody.CompanyMessageBody;

/**
 * 专案结算
 * @author luo
 */
public class CaseFinishMessage extends CompanyMessageBody {
	private long caseId;
	private String caseName;
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
}
