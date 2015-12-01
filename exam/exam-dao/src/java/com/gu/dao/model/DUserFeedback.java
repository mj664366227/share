package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 用户意见反馈
 * @author luo
 */
@Pojo
@Message
public class DUserFeedback extends DSuper {
	/**
	 * id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 反馈内容
	 */
	private String feedback;
	/**
	 * 添加时间
	 */
	private int createTime;

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

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

}
