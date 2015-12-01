package com.gu.core.protocol;

import java.util.ArrayList;
import java.util.List;

import com.gu.core.interfaces.SResponse;
import com.gu.core.protocol.base.UserBaseResponse;

/**
 * 创意详细信息返回协议
 * @author luo
 */
public class MarketOpusInfoResponse extends SResponse {
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
	private long marketOpusId;
	/**
	 * 发表时间
	 */
	private long createTime;
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

	public long getMarketOpusId() {
		return marketOpusId;
	}

	public void setMarketOpusId(long marketOpusId) {
		this.marketOpusId = marketOpusId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
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
