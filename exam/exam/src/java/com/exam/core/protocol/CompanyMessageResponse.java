package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;
import com.exam.core.protocol.msg.msgbody.CompanyMessageBody;

/**
 * 企业消息基体
 * @author luo
 */
public class CompanyMessageResponse extends SResponse {
	/**
	 * 企业消息id
	 */
	private long companyMessageId;
	/**
	 * 消息类型
	 */
	private int type;
	/**
	 * 消息小类
	 */
	private int sign;
	/**
	 * 接收者id
	 */
	private long companyId;
	/**
	 * 发送者id
	 */
	private long senderId;
	/**
	 * 发送者名称
	 */
	private String senderName;
	/**
	 * 发送者头像
	 */
	private String senderImage;
	/**
	 * 添加时间
	 */
	private int createTime;
	/**
	 * 消息内容
	 */
	private CompanyMessageBody body;

	public CompanyMessageBody getBody() {
		return body;
	}

	public void setBody(CompanyMessageBody body) {
		this.body = body;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderImage() {
		return senderImage;
	}

	public void setSenderImage(String senderImage) {
		this.senderImage = senderImage;
	}

	public long getCompanyMessageId() {
		return companyMessageId;
	}

	public void setCompanyMessageId(long companyMessageId) {
		this.companyMessageId = companyMessageId;
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

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
}
