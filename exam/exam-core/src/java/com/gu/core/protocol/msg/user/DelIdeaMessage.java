package com.gu.core.protocol.msg.user;

import com.gu.core.protocol.msg.msgbody.UserMessageBody;

/**
 * 删除点子消息
 * @author ruan
 */
public class DelIdeaMessage extends UserMessageBody {
	private String content = "";

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}