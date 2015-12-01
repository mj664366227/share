package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;
import com.gu.core.protocol.base.UserBaseResponse;

/**
 * 创意简单详细信息返回协议
 * @author luo
 */
public class IdeaResponse extends SResponse {
	/**
	 * TODO 兼容1.0 改用userBase
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * TODO 兼容1.0 改用userBase
	 * 头像
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
	 * 创意id
	 */
	private long ideaId;
	/**
	 * 发表时间
	 */
	private int createTime;
	/**
	 * 创意标题
	 */
	private String title;
	/**
	 * 详细点子(web工程用，不赋值，转json的时候就不会有字段的)
	 */
	private String content;
	/**
	 * 点赞数
	 */
	private int praise;
	/**
	 * 当前用户是否已经点赞 1=已点 0=没点
	 */
	private int isPraise = 0;
	/**
	 * 评论数
	 */
	private int ideaCommentCount = 0;
	/**
	 * 创意图片列表
	 */
	private List<String> imageList = new ArrayList<String>();

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

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}

	public int getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(int isPraise) {
		this.isPraise = isPraise;
	}

	public int getIdeaCommentCount() {
		return ideaCommentCount;
	}

	public void setIdeaCommentCount(int ideaCommentCount) {
		this.ideaCommentCount = ideaCommentCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	public UserBaseResponse getUserBase() {
		return userBase;
	}

	public void setUserBase(UserBaseResponse userBase) {
		this.userBase = userBase;
	}
}
