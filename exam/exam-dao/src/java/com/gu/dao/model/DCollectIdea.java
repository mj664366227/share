package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 企业收藏创意
 * @author ruan
 */
@Pojo
@Message
public class DCollectIdea extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 创意id
	 */
	private long ideaId;
	/**
	 * 收藏时间
	 */
	private int createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}
}
