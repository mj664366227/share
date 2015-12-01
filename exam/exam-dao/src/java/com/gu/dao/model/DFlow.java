package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 吐槽
 * @author ruan
 */
@Pojo
@Message
public class DFlow extends DSuper {
	/**
	 * 吐槽id
	 */
	private long id;
	/**
	 * 专案id
	 */
	private long caseId;
	/**
	 * 吐槽人用户id
	 */
	private long userId;
	/**
	 * 发表时间
	 */
	private int createTime;
	/**
	 * 吐槽内容
	 */
	private String content;

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

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}