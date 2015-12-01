package com.gu.core.protocol.base;

import com.gu.core.interfaces.SResponse;

/**
 * 用户信息通用返回协议
 * @author luo
 */
public class UserBaseResponse extends SResponse {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 用户头像
	 */
	private String avatarImage;
	/**
	 * 一句话亮身份
	 */
	private String identity;
	/**
	 * 等级
	 */
	private int level;
	/**
	 * 性别(男1女2)
	 */
	private int sex = 2;
	/**
	 * 是否实名认证
	 */
	private int isProve;

	public UserBaseResponse(long userId, String nickname, String avatarImage, String identity, int level, int sex, int isProve) {
		this.userId = userId;
		this.nickname = nickname;
		this.avatarImage = avatarImage;
		this.identity = identity;
		this.level = level;
		this.sex = sex;
		this.isProve = isProve;
	}

	/**
	 * TODO 兼容1.0
	 */
	public UserBaseResponse() {

	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(String avatarImage) {
		this.avatarImage = avatarImage;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getIsProve() {
		return isProve;
	}

	public void setIsProve(int isProve) {
		this.isProve = isProve;
	}
}
