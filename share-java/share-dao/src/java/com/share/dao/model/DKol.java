package com.share.dao.model;
import com.share.core.annotation.Pojo;
import com.share.core.interfaces.DSuper;
/**
 * KOL表
 */
@Pojo
public class DKol extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 圈子
	 */
	private String group;
	/**
	 * KOL描述
	 */
	private String description;
	/**
	 * 状态(1-审核通过，0-未审核，-1-审核未通过)
	 */
	private int status;

	/**
	 * 获取用户id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 获取圈子
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * 设置圈子
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * 获取KOL描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置KOL描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取状态(1-审核通过，0-未审核，-1-审核未通过)
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置状态(1-审核通过，0-未审核，-1-审核未通过)
	 */
	public void setStatus(int status) {
		this.status = status;
	}


}