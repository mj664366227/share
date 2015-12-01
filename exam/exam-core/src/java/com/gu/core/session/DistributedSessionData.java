package com.gu.core.session;

import org.msgpack.annotation.Message;

/**
 * 内部使用的数据结构
 * @author ruan
 */
@Message
public class DistributedSessionData {
	private byte[] data;

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getData() {
		return data;
	}
}
