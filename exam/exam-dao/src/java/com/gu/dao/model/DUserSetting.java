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
public class DUserSetting extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 对所有人可见
	 */
	private int allVisit;
	/**
	 * 对好友可见
	 */
	private int friendVisit;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getAllVisit() {
		return allVisit;
	}

	public void setAllVisit(int allVisit) {
		this.allVisit = allVisit;
	}

	public int getFriendVisit() {
		return friendVisit;
	}

	public void setFriendVisit(int friendVisit) {
		this.friendVisit = friendVisit;
	}
}
