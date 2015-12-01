package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

@Pojo
@Message
public class DCaseFinishBonusLog extends DSuper {
	
	/**
	 * id
	 */
	private long id;

	/**
	 * 专案id
	 */
	private long caseId;
	
	/**
	 * 企业id
	 */
	private long companyId;

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

	/**
	 * 结算时间
	 */
	private int finishTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

}
