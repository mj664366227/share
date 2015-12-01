package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

@Pojo
@Message
public class DCaseFinishBonusCommonUserLog extends DSuper {

	/**
	 * id
	 */
	private long id;

	/**
	 * 专案id
	 */
	private long caseId;

	/**
	 * 分红百分比
	 */
	private int percent;

	/**
	 * 计划分红G点
	 */
	private int bonusPointsPlan;

	/**
	 * 实际分红G点
	 */
	private int bonusPointsReal = 0;

	/**
	 * 剩余分红G点
	 */
	private int bonusPointsSurplus;

	/**
	 * 平均分红G点
	 */
	private int bonusPointsAverage = 0;

	/**
	 * 用户总数
	 */
	private int userNum = 0;

	/**
	 * 添加时间
	 */
	private int createTime;

	/**
	 * 分红名字
	 */
	private String bonusName;

	/**
	 * 用户列表
	 */
	private String userIdList;

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

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public int getBonusPointsPlan() {
		return bonusPointsPlan;
	}

	public void setBonusPointsPlan(int bonusPointsPlan) {
		this.bonusPointsPlan = bonusPointsPlan;
	}

	public int getBonusPointsReal() {
		return bonusPointsReal;
	}

	public void setBonusPointsReal(int bonusPointsReal) {
		this.bonusPointsReal = bonusPointsReal;
	}

	public int getBonusPointsSurplus() {
		return bonusPointsSurplus;
	}

	public void setBonusPointsSurplus(int bonusPointsSurplus) {
		this.bonusPointsSurplus = bonusPointsSurplus;
	}

	public int getBonusPointsAverage() {
		return bonusPointsAverage;
	}

	public void setBonusPointsAverage(int bonusPointsAverage) {
		this.bonusPointsAverage = bonusPointsAverage;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getBonusName() {
		return bonusName;
	}

	public void setBonusName(String bonusName) {
		this.bonusName = bonusName;
	}

	public String getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(String userIdList) {
		this.userIdList = userIdList;
	}
	
}
