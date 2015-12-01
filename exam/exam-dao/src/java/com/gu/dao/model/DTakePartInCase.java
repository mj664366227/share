package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;
@Pojo
@Message
public class DTakePartInCase extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 企业id
	 */
	private long caseId;
	/**
	 * 关注时间
	 */
	private long createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
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
}
