package com.gu.dao.model;

import java.math.BigDecimal;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

@Pojo
@Message
public class DCaseFinishBonusPrizeUserLog extends DSuper {

	/**
	 * id
	 */
	private long id;
	
	/**
	 * 专案id
	 */
	private long caseId;
	
	/**
	 * 专案分配百分比
	 */
	private int percent;
	
	/**
	 * 分配的G点总数
	 */
	private int bonusPointsNum;
	
	/**
	 * 创意id
	 */
	private long ideaId;
	
	/**
	 * 用户id
	 */
	private long userId;
	
	/**
	 * 专案总赞数
	 */
	private int casePraise;
	
	/**
	 * 创意获赞数
	 */
	private int ideaPraise;
	
	/**
	 * 创意分配百分比(专案总赞数/创意获赞数)
	 */
	private BigDecimal ideaPercent;
	
	/**
	 * 创意获分G点
	 */
	private int bonusPoints;

	/**
	 * 添加时间
	 */
	private int createTime;

	/**
	 * 分红名字
	 */
	private String bonusName;

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

	public int getBonusPointsNum() {
		return bonusPointsNum;
	}

	public void setBonusPointsNum(int bonusPointsNum) {
		this.bonusPointsNum = bonusPointsNum;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	public int getCasePraise() {
		return casePraise;
	}

	public void setCasePraise(int casePraise) {
		this.casePraise = casePraise;
	}

	public int getIdeaPraise() {
		return ideaPraise;
	}

	public void setIdeaPraise(int ideaPraise) {
		this.ideaPraise = ideaPraise;
	}

	public BigDecimal getIdeaPercent() {
		return ideaPercent;
	}

	public void setIdeaPercent(BigDecimal ideaPercent) {
		this.ideaPercent = ideaPercent;
	}

	public int getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
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
	
}
