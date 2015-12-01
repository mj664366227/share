package com.gu.core.protocol.msg.user;

import com.gu.core.protocol.msg.msgbody.UserMessageBody;

/**
 * 创意点赞消息
 * @author luo
 */
public class IdeaPraiseUpMessage extends UserMessageBody {
	/**
	 * 创意id
	 */
	private long ideaId;

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}
}
