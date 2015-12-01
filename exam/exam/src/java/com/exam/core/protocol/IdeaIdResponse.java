package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;

/**
 * 创意详细信息返回协议
 * @author luo
 */
public class IdeaIdResponse extends SResponse {
	/**
	 * 创意id
	 */
	private long ideaId;
	
	private int createTime;

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
}
