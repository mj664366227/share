package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;
import com.exam.core.protocol.base.UserBaseResponse;

/**
 * 单个吐槽体
 * @author ruan
 */
public class FlowResponse extends SResponse {
	/**
	 * TODO 兼容1.0 改用userBase
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * TODO 兼容1.0 改用userBase
	 * 用户头像
	 */
	private String avatarImage;
	/**
	 * TODO 兼容1.0 改用userBase
	 * 一句话亮身份
	 */
	private String identity = "";

	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 用户信息
	 */
	private UserBaseResponse userBase;
	/**
	 * 吐槽id
	 */
	private long flowId;
	/**
	 * 吐槽发表时间
	 */
	private int createTime;
	/**
	 * 吐槽内容
	 */
	private String content;

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

	public long getFlowId() {
		return flowId;
	}

	public void setFlowId(long flowId) {
		this.flowId = flowId;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public UserBaseResponse getUserBase() {
		return userBase;
	}

	public void setUserBase(UserBaseResponse userBase) {
		this.userBase = userBase;
	}
}
