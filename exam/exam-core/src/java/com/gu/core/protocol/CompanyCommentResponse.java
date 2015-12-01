package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;
import com.gu.core.protocol.base.UserBaseResponse;

/**
 * 企业回复创意单条数据
 * @author ruan
 */
public class CompanyCommentResponse extends SResponse {
	/**
	 * TODO 兼容1.0 改用userBase
	 * 用户名
	 */
	private String username = "";

	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 用户信息
	 */
	private UserBaseResponse userBase;
	/**
	 * 内容
	 */
	private String content = "";
	/**
	 * 发表时间
	 */
	private int createTime;
	/**
	 * 动作(1-我回复了xx 2-xx回复了我)
	 */
	private int action;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public UserBaseResponse getUserBase() {
		return userBase;
	}

	public void setUserBase(UserBaseResponse userBase) {
		this.userBase = userBase;
	}
}