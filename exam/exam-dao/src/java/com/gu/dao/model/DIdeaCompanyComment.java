package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 企业回复创意(企业评论创意)
 * @author luo
 */
@Pojo
@Message
public class DIdeaCompanyComment extends DSuper {
	/**
	 * 创意评论id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 创意id
	 */
	private long ideaId;
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 发表时间
	 */
	private int createTime;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 动作(1-我回复了xx 2-xx回复了我)
	 */
	private int action;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
}