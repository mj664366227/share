package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 企业消息
 * @author luo
 */
@Pojo
@Message
public class DCompanyMessage extends DSuper {
	/**
	 * id
	 */
	private long id;
	/**
	 * 消息类型
	 */
	private int type;
	/**
	 * 消息号码(10001)
	 */
	private int sign;
	/**
	 * 企业id(接受者)
	 */
	private long companyId;
	/**
	 * 发送者id(userId/companyId)
	 */
	private long senderId;
	/**
	 * 添加时间
	 */
	private int createTime;
	/**
	 * 消息内容
	 */
	private byte[] data;
	/**
	 * 消息内容(JSON)
	 */
	private String body;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
