package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;
@Pojo
@Message
public class DFocusUser extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 被关注人id
	 */
	private long friendId;
	/**
	 * 关注人id
	 */
	private long userId;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}
}