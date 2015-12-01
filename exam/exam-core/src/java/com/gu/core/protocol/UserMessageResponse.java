package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;
import com.gu.core.protocol.msg.msgbody.UserMessageBody;

/**
 * 用户消息基体
 * @author luo
 */
public class UserMessageResponse extends SResponse {
	/**
	 * 用户消息id
	 */
	private long userMessageId;
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
	private long userId;
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
	private UserMessageBody body;

	public UserMessageBody getBody() {
		return body;
	}

	public void setBody(UserMessageBody body) {
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

	public long getUserMessageId() {
		return userMessageId;
	}

	public void setUserMessageId(long userMessageId) {
		this.userMessageId = userMessageId;
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
