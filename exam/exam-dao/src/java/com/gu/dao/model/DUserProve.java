package com.gu.dao.model;

import org.msgpack.annotation.Message;

import com.gu.core.annotation.Pojo;
import com.gu.core.interfaces.DSuper;

/**
 * 用户实名认证信息
 * @author luo
 */
@Pojo
@Message
public class DUserProve extends DSuper {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 认证信息
	 */
	private String proveInfo;
	/**
	 * 身份证图片
	 */
	private String identityCard;
	/**
	 * 其他证件图片
	 */
	private String otherCard;
	/**
	 * 是否实名认证
	 */
	private int isProve;
	/**
	 * 添加时间
	 */
	private int createTime;
	/**
	 * 审核时间
	 */
	private int checkTime;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getProveInfo() {
		return proveInfo;
	}

	public void setProveInfo(String proveInfo) {
		this.proveInfo = proveInfo;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getOtherCard() {
		return otherCard;
	}

	public void setOtherCard(String otherCard) {
		this.otherCard = otherCard;
	}

	public int getIsProve() {
		return isProve;
	}

	public void setIsProve(int isProve) {
		this.isProve = isProve;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public int getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(int checkTime) {
		this.checkTime = checkTime;
	}
}