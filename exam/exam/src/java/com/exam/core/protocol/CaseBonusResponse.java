package com.exam.core.protocol;

import com.exam.core.interfaces.SResponse;
import com.exam.core.protocol.base.UserBaseResponse;

/**
 * 专案奖金分配单体
 * @author ruan
 */
public class CaseBonusResponse extends SResponse {
	/**
	 * TODO 兼容1.0 改用userBase
	 * 用户头像
	 */
	private String avatarImage;
	/**
	 * TODO 兼容1.0 改用userBase
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * TODO 兼容1.0 改用userBase
	 * 用户等级
	 */
	private int level;

	/**
	 * 用户Id
	 */
	private long userId;
	/**
	 * 用户信息
	 */
	private UserBaseResponse userBase;
	/**
	 * 专案结算时间
	 */
	private long createTime;
	/**
	 * 分配的点数
	 */
	private int points;
	/**
	 * 分配原因备注(第几名/其他创意/吐槽/点赞)
	 */
	private String remark;

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(String avatarImage) {
		this.avatarImage = avatarImage;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public UserBaseResponse getUserBase() {
		return userBase;
	}

	public void setUserBase(UserBaseResponse userBase) {
		this.userBase = userBase;
	}
}
