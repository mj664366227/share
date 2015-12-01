package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * G点支出列表
 * @author ruan
 */
@Pojo
@Message
public class DCompanyExpense extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 费用
	 */
	private int points;
	/**
	 * 记录时间
	 */
	private int createTime;

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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
}