package com.exam.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.exam.core.interfaces.SResponse;
import com.exam.core.protocol.base.UserBaseResponse;

/**
 * 创意详细信息返回协议
 * @author luo
 */
public class IdeaInfoResponse extends SResponse {
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
	 * 专案id
	 */
	private long caseId;
	/**
	 * 发表时间
	 */
	private int createTime;
	/**
	 * 创意标题
	 */
	private String title;
	/**
	 * 创意内容
	 */
	private String content;
	/**
	 * 点赞数
	 */
	private int praise;
	/**
	 * 创意图片列表
	 */
	private List<String> imageList = new ArrayList<String>();
	/**
	 * TODO 兼容1.0
	 * 专案图片列表
	 */
	private List<String> imagesList = imageList;

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

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void addImageList(String image) {
		imageList.add(image);
	}

	public UserBaseResponse getUserBase() {
		return userBase;
	}

	public void setUserBase(UserBaseResponse userBase) {
		this.userBase = userBase;
	}
}
