package com.share.core.session;

/**
 * 内部使用的数据结构
 * @author ruan
 */
public class DistributedSessionData {
	private Object data;

	public void setData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}
}
