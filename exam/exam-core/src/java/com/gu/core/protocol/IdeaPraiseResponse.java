package com.gu.core.protocol;

import com.gu.core.interfaces.SResponse;

/**
 * 创意详细信息返回协议
 * @author luo
 */
public class IdeaPraiseResponse extends SResponse {
	/**
	 * 点赞数
	 */
	private int praise;
	
	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}
}
