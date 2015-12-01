package com.gu.core.protocol.msg.company;

import com.gu.core.protocol.msg.msgbody.CompanyMessageBody;

/**
 * 用户回复企业消息
 * @author luo
 */
public class UserReplyCompanyMessage extends CompanyMessageBody {

	private long caseId;

	private long ideaId;

	private String ideaTitle;

	private int ideaCreateTime;

	private long ideaCompanyCommentId;

	private String replyContent;

	private int replyCreateTime;

	private long fromId;

	private String fromContent;

	private int fromCreateTime;

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(long ideaId) {
		this.ideaId = ideaId;
	}

	public String getIdeaTitle() {
		return ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	public long getIdeaCompanyCommentId() {
		return ideaCompanyCommentId;
	}

	public void setIdeaCompanyCommentId(long ideaCompanyCommentId) {
		this.ideaCompanyCommentId = ideaCompanyCommentId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public long getFromId() {
		return fromId;
	}

	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

	public String getFromContent() {
		return fromContent;
	}

	public void setFromContent(String fromContent) {
		this.fromContent = fromContent;
	}

	public int getIdeaCreateTime() {
		return ideaCreateTime;
	}

	public void setIdeaCreateTime(int ideaCreateTime) {
		this.ideaCreateTime = ideaCreateTime;
	}

	public int getReplyCreateTime() {
		return replyCreateTime;
	}

	public void setReplyCreateTime(int replyCreateTime) {
		this.replyCreateTime = replyCreateTime;
	}

	public int getFromCreateTime() {
		return fromCreateTime;
	}

	public void setFromCreateTime(int fromCreateTime) {
		this.fromCreateTime = fromCreateTime;
	}
}
