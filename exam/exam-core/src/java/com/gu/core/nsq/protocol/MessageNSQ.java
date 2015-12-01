package com.gu.core.nsq.protocol;

import org.msgpack.annotation.Message;

import com.gu.core.interfaces.MessageBody;
import com.gu.core.util.JSONObject;
import com.gu.core.util.SerialUtil;

@Message
public class MessageNSQ {
	private int massageReceiver;

	private int messageSign;

	private long receiverId;

	private long senderId;

	private int createTime;

	private byte[] data = new byte[0];

	private String body;

	public int getMassageReceiver() {
		return massageReceiver;
	}

	public void setMassageReceiver(int massageReceiver) {
		this.massageReceiver = massageReceiver;
	}

	public int getMessageSign() {
		return messageSign;
	}

	public void setMessageSign(int messageSign) {
		this.messageSign = messageSign;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
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

	public void setData(MessageBody messageBody) {
		this.data = SerialUtil.toBytes(messageBody);
	}

	public String getBody() {
		return body;
	}

	public void setBody(MessageBody messageBody) {
		this.body = JSONObject.encode(messageBody);
	}
}
