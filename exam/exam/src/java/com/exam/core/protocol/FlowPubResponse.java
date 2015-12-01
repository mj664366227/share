package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;

/**
 * 发布吐槽返回协议
 * @author ruan
 */
public class FlowPubResponse extends SResponse {
	/**
	 * 吐槽id
	 */
	private long flowId;
	/**
	 * 吐槽发表时间
	 */
	private int time;

	public long getFlowId() {
		return flowId;
	}

	public void setFlowId(long flowId) {
		this.flowId = flowId;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
