package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 企业关注用户
 * @author ruan
 */
@Pojo
@Message
public class DCompanyFocus extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 企业id
	 */
	private long companyId;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 关注时间
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
}
