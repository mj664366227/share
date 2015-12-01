package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 预留手机送G点
 * @author ruan
 */
@Pojo
@Message
public class DStoreMobile extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 赠送G点
	 */
	private int points;
	/**
	 * 记录时间
	 */
	private int createTime;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
