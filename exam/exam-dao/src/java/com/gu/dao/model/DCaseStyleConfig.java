package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

@Pojo
@Message
public class DCaseStyleConfig extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 类型名称
	 */
	private String name;
	/**
	 * 对应G点
	 */
	private int points;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}