package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * qq唯一识别ID表
 * @author luo
 */
@Pojo
@Message
public class DQqOpen extends DSuper {

	private long id;
	/**
	 * 关联user表id
	 */
	private long userId;
	/**
	 * 第三方唯一识别ID
	 */
	private String openId;

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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
