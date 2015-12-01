package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

@Pojo
@Message
public class DCaseFinishBonusUserLog extends DSuper {

	/**
	 * id
	 */
	private long id;
	
	/**
	 * 专案id
	 */
	private long caseId;
	
	/**
	 * 用户id
	 */
	private long userId;
	
	/**
	 * 获分G点
	 */
	private int bonusPoints;

	/**
	 * 添加时间
	 */
	private int createTime;

	/**
	 * 分红备注
	 */
	private String bonusRemark;

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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public String getBonusRemark() {
		return bonusRemark;
	}

	public void setBonusRemark(String bonusRemark) {
		this.bonusRemark = bonusRemark;
	}
	
}
