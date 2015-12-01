package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;
import com.gu.core.protocol.base.UserBaseResponse;

/**
 * 单个创意评论
 * @author ruan
 */
public class IdeaCommentResponse extends SResponse {
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
	 * 创意评论id
	 */
	private long ideaCommentId;
	/**
	 * 创意评论发表时间
	 */
	private int createTime;
	/**
	 * 创意评论内容
	 */
	private String content;
	/**
	 * 回复的用户id
	 */
	private Long replyUserId;
	/**
	 * 回复的用户信息
	 */
	private UserBaseResponse replyUserBase;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getIdeaCommentId() {
		return ideaCommentId;
	}

	public void setIdeaCommentId(long ideaCommentId) {
		this.ideaCommentId = ideaCommentId;
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

	public Long getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Long replyUserId) {
		this.replyUserId = replyUserId;
	}

	public UserBaseResponse getUserBase() {
		return userBase;
	}

	public void setUserBase(UserBaseResponse userBase) {
		this.userBase = userBase;
	}

	public UserBaseResponse getReplyUserBase() {
		return replyUserBase;
	}

	public void setReplyUserBase(UserBaseResponse replyUserBase) {
		this.replyUserBase = replyUserBase;
	}
}
