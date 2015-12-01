package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;

/**
 * 创意详细信息返回协议
 * @author luo
 */
public class UserSettingResponse extends SResponse {
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
