package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 用户举报
 * @author luo
 */
@Pojo
@Message
public class DUserAccusation extends DSuper {
	/**
	 * id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 类型号码
	 */
	private int type;
	/**
	 * 类型id
	 */
	private int typeId;
	/**
	 * 添加时间
	 */
	private int createTime;
	/**
	 * 举报内容
	 */
	private String content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
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

}
