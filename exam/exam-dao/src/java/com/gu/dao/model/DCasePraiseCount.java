package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;
@Pojo
@Message
public class DCasePraiseCount extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 点赞数量
	 */
	private int count;
	/**
	 * 最后点赞时间
	 */
	private long createTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
}
