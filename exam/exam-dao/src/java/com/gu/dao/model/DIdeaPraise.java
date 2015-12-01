package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;
@Pojo
@Message
public class DIdeaPraise extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 创意id
	 */
	private long ideaId;
	/**
	 * 专案id
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

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}
}
