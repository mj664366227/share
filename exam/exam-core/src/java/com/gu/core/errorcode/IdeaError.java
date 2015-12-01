package com.gu.core.errorcode;

import com.gu.core.interfaces.Error;

public enum IdeaError implements Error {
	/**
	 * 已点赞(2005001)
	 */
	isPraiseError(2005001, "已点赞"),
	/**
	 * 这个专案5次点赞机会用完了哦~看看其他专案吧(2005002)
	 */
	thanFiveError(2005002, "这个专案5次点赞机会用完了哦~看看其他专案吧"),
	/**
	 * 没有点赞,不能取消(2005003)
	 */
	notPraiseError(2005003, "没有点赞,不能取消"),
	/**
	 * 创意已经发表过了,只能发布一次
	 */
	ideaPubOnlyError(2005004, "创意已经发表过了,只能发布一次"),
	/**
	 * 创意不存在(2005005)
	 */
	ideaNotExists(2005005, "创意不存在"),
	/**
	 * 此创意已经收藏过(2005006)
	 */
	ideaHasCollected(2005006, "此创意已经收藏过");

	private int errorCode;
	private String errorMsg;

	private IdeaError(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
