package com.gu.core.enums;

/**
 * 长度限制
 * @author ruan
 */
public enum LengthLimit {
	/**
	 * 昵称
	 */
	nickname(15),
	/**
	 * 验证一句话亮身份
	 */
	identity(16),
	/**
	 * 吐槽
	 */
	flow(140),
	/**
	 * 创意标题
	 */
	ideaTitle(50),
	/**
	 * 创意描述
	 */
	ideaDescription(800),
	/**
	 * 创意评论
	 */
	ideaComment(140),
	/**
	 * 企业简称
	 */
	companyName(30),
	/**
	 * 企业全名
	 */
	companyFullName(60),
	/**
	 * 企业描述
	 */
	companyDescription(200),
	/**
	 * 专案名称
	 */
	caseName(60),
	/**
	 * 专案描述
	 */
	caseDescription(500),
	/**
	 * 缓存丢失之后回写的条数(1000)
	 */
	reloadFromRedisLen(1000);
	private int value;

	private LengthLimit(int value) {
		this.value = value;
	}

	public int getLength() {
		return value;
	}
}
