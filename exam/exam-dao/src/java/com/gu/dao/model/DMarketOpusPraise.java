package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 创意圈_作品点赞
 * @author luo
 */
@Pojo
@Message
public class DMarketOpusPraise extends DSuper {
	/**
	 * 自增id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 创意圈_作品id
	 */
	private long marketOpusId;
	/**
	 * 关注时间
	 */
	private long createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getMarketOpusId() {
		return marketOpusId;
	}

	public void setMarketOpusId(long marketOpusId) {
		this.marketOpusId = marketOpusId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
